package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.configuration.MysqlDB;
import com.model.RunParameterModel;
import com.model.WebServiceModel;
import com.mysql.jdbc.PreparedStatement;

import eu.impact_project.iif.ws.generic.SoapOperation;
import eu.impact_project.iif.ws.generic.SoapService;

/**
 * Servlet implementation class ViewPreRunController
 */
@WebServlet("/ViewPreRun")
public class ViewPreRunController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MysqlDB db = new MysqlDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPreRunController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int runID = Integer.parseInt(request.getParameter("id"));
		String sql = "SELECT a.*,b.service_url,b.name FROM cs594.save_webservice_run a, web_services b where a.id = "+runID+" and b.id = a.wsdl";
		String getRunParameterSql = "SELECT * FROM cs594.service_run_parameters where service_run_id="+runID;
		try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
		Connection c = db.dbConnection();
		List<RunParameterModel> parameters= new ArrayList<RunParameterModel>();
		WebServiceModel model = new WebServiceModel();
		try {
			PreparedStatement pstmt = (PreparedStatement) c.prepareStatement(getRunParameterSql);
			 ResultSet rs=pstmt.executeQuery();

			 while(rs.next())
			 {
				 RunParameterModel parameter= new RunParameterModel();
				 parameter.setId(rs.getInt("id"));
				 parameter.setParameterName(rs.getString("parameter_name"));
				 parameter.setParameterValue(rs.getString("parameter_value"));
				 parameter.setServiceRunID(rs.getInt("service_run_id"));
				 parameters.add(parameter);
				 
			 }
			 if(rs!=null)
				 rs.close();
				 if(pstmt!=null)
						pstmt.close();
			 
				 pstmt = (PreparedStatement) c.prepareStatement(sql);
				  rs=pstmt.executeQuery();
			 
			 if(rs.next())
			 {
				 	//models.add(new WebServiceModel(rs.getInt("id"), rs.getString("name"), rs.getString("method"), rs.getDouble("time_taken"), rs.getDouble("times_run")));
				 
				 	model.setWsdl(rs.getString("service_url"));
				 	model.setWsdlName(rs.getString("name"));
				 	model.setTimesRun(rs.getInt("times_run"));
				 	model.setMethod(rs.getString("method"));
				 	model.setParametersValues(parameters);
			 }
			
			 if(rs!=null)
			 rs.close();
			 if(pstmt!=null)
					pstmt.close();
			
			 if(c!=null)
				 c.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		request.setAttribute("displayModel", model);
		SoapService service = new SoapService(model.getWsdl());	
		getServletContext().setAttribute("service", service);
		getServletContext().setAttribute("wsdl",model.getWsdl());
		getServletContext().setAttribute("wsdlName",model.getWsdlName());
		getServletContext().setAttribute("method",model.getMethod());
		
		
		
		request.getRequestDispatcher("/WEB-INF/jsp/ViewAndRun.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
