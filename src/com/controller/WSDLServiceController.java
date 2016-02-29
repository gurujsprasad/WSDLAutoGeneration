package com.controller;



import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.configuration.MysqlDB;
import com.enums.Operations;
import com.mysql.jdbc.PreparedStatement;

import eu.impact_project.iif.ws.generic.SoapInput;
import eu.impact_project.iif.ws.generic.SoapOperation;
import eu.impact_project.iif.ws.generic.SoapOutput;
import eu.impact_project.iif.ws.generic.SoapService;



/**
 * Servlet implementation class TestingSoap
 */
@WebServlet("/WSDLServiceController")
public class WSDLServiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 MysqlDB db = new MysqlDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WSDLServiceController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getServletContext().setAttribute("allOps",null);
		getServletContext().setAttribute("service", null);
		//getServletContext().setAttribute("wsdl", null);
		getServletContext().setAttribute("method", null);
		getServletContext().setAttribute("allInputs", null);
		
	    request.getRequestDispatcher("/WEB-INF/jsp/WSDLService.jsp").forward(request, response);
		//SoapOperation op = service.getOperation("myOperation");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operation = request.getParameter("operation");
		
		if (operation.equals(Operations.GetMethods.toString()))
		{
			String wsdl = request.getParameter("wsdl");
			SoapService service = new SoapService(wsdl);	
			List<SoapOperation> allOps = service.getOperations();
			getServletContext().setAttribute("allOps", allOps);
			getServletContext().setAttribute("service", service);
			getServletContext().setAttribute("wsdl", wsdl);
			request.getRequestDispatcher("/WEB-INF/jsp/WSDLService.jsp").forward(request, response);
			
		}
		
		if (operation.equals(Operations.GetParameters.toString()))
		{
			String method = request.getParameter("methods");
			SoapService service = (SoapService) getServletContext().getAttribute("service");
			SoapOperation op = service.getOperation(method);
			List<SoapInput> allInputs = op.getInputs();
			getServletContext().setAttribute("service", service);
			getServletContext().setAttribute("allInputs", allInputs);
			getServletContext().setAttribute("method", method);
			
			request.getRequestDispatcher("/WEB-INF/jsp/WSDLService.jsp").forward(request, response);
		}
		
		if (operation.equals(Operations.SetParameters.toString()))
		{
			String method = (String) getServletContext().getAttribute("method");
			int timesToRun = Integer.parseInt(request.getParameter("timesToRun"));
			//System.out.println(request.getParameterValues("inputs[]"));
			String inputs []=request.getParameterValues("inputs[]");
			SoapService service = (SoapService) getServletContext().getAttribute("service");
			SoapOperation op = service.getOperation(method);
			
			List<SoapOutput> outs = null;
			List<SoapInput> allInputs = op.getInputs();
			long timeTaken = 0;
			String message = "";
			if(inputs != null)
			{
				for(int i =0; i < inputs.length; i++)
				{
					allInputs.get(i).setValue(inputs[i]);
					// executing the operation
				}	
			}
			
			long start = System.currentTimeMillis();
			
			for (int j = 1 ; j <= timesToRun; j++)
			{
				outs = op.execute(null,null);
			}
			
			long end = System.currentTimeMillis();
			
			timeTaken = end - start;
			
			try
	        {
	            Class.forName( "com.mysql.jdbc.Driver" );
	        }
	        catch( ClassNotFoundException e )
	        {
	            throw new ServletException( e );
	        }
			Connection c = db.dbConnection();
			
			int id=0;
			Boolean exist=Boolean.FALSE;
			
			String sql = "select * from web_services where service_url=?";
			try {
				PreparedStatement pstmt = (PreparedStatement) c.prepareStatement(sql);
				Statement stmt = c.createStatement();
				pstmt.setString(1, getServletContext().getAttribute("wsdl").toString());
				 ResultSet rs=pstmt.executeQuery();
				// System.out.println(exist);
				 if(rs.next())
				 {
					 //System.out.println("exists");
					 id=rs.getInt("id");
					 exist=Boolean.TRUE;
				 }
				 //System.out.println(exist);
				 if(rs!=null)
				 rs.close();
				 if(pstmt!=null)
						pstmt.close();
				 if(stmt!=null)
				stmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
				
				 
				 if(!exist)
				 {
					 sql = "INSERT INTO web_services (service_url) VALUES(?)";
					 try {
						 PreparedStatement pstmt=(PreparedStatement) c.prepareStatement(sql);
							Statement stmt = c.createStatement();
							pstmt.setString(1, getServletContext().getAttribute("wsdl").toString());
							pstmt.executeUpdate();
							//System.out.println("inserted");
							 if(stmt!=null)
									stmt.close();
							 if(pstmt!=null)
									pstmt.close();
							
							 sql = "select * from web_services where service_url=?";
							 pstmt = (PreparedStatement) c.prepareStatement(sql);
							 pstmt.setString(1, getServletContext().getAttribute("wsdl").toString());
							 ResultSet rs=pstmt.executeQuery();
							
							 if(rs.next())
							 {
								 //System.out.println("inserted exists");
								 id=rs.getInt("id");
								 exist=Boolean.TRUE;
							 }
							 if(rs!=null)
							 rs.close();
							 if(pstmt!=null)
									pstmt.close();
							 if(stmt!=null)
							stmt.close();
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
				 }
				 sql="INSERT INTO web_services_time_consumed(service_id,repetition,time_taken,time_per_request)VALUES('"+id+"','"+request.getParameter("timesToRun")+"','"+timeTaken+"',"+timeTaken/timesToRun+")";
				 
				 try {
						Statement stmt = c.createStatement();
						stmt.executeUpdate(sql);
						//System.out.println("Details Added");
						if(stmt!=null)
						stmt.close();
						 
						if(c!=null)
							c.close();
					
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			request.setAttribute("outs", outs);
			message = "Time taken to execute this method "+timesToRun+" times is, "+timeTaken+" milli seconds";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/WEB-INF/jsp/WSDLService.jsp").forward(request, response);
		}
	}

}