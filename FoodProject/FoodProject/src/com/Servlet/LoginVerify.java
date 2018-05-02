package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginVerify
 */
@WebServlet("/LoginVerify")
public class LoginVerify extends HttpServlet {
	static String userName;
	static String password;
	static String path_url;
	static HttpServletRequest request;
	static HttpServletResponse response;

	static Connection con;
	String uname;
	String pass;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginVerify() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userName="system";
		password="drowssap";
		path_url="jdbc:oracle:thin:@localhost:1521:XE";    
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(path_url, userName, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Connection Done");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.request=request;
	}

	private static boolean validate(String nm, String pw) {
		// TODO Auto-generated method stub
		


		boolean status=false;
		try {
			
			PreparedStatement ps=con.prepareStatement(  
					"select * from foodTable where name=?,password=?");  
			ps.setString(1,nm);  
			ps.setString(2,password);  

			ResultSet rs=ps.executeQuery();  
			status=rs.next(); 
			String name1=rs.getString("name");

			if(nm.equals(name1))
			{
				RequestDispatcher rd=request.getRequestDispatcher("welcome.html");  
				rd.forward(request,response);  
				 return status;
			}
			else
			{	PrintWriter out = response.getWriter();  
				out.print("Sorry username or password error");  
				RequestDispatcher rd=request.getRequestDispatcher("login.html");  
				rd.include(request,response); 
			}

		}catch (Exception e) {
			e.printStackTrace();

		}
			return status;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		PrintWriter out = response.getWriter();  

		String n=request.getParameter("login");  
		String p=request.getParameter("pwd");  
		if(LoginVerify.validate(n,p)){
			// response.sendRedirect("Welcome");
			{
				RequestDispatcher rd=request.getRequestDispatcher("welcome.html");  
				rd.forward(request,response);  
			}  
		}
	  
		out.close();  
}

}
