package com.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	String userName;
	String password;
	String path_url;
	PreparedStatement pst;
	Connection con;
	String name;
	String email;
	String userpass;
    int phone;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 name=request.getParameter("name");
		 phone=Integer.parseInt(request.getParameter("phone"));
		 userpass=request.getParameter("password");
		 email=request.getParameter("email");
		 System.out.println(name+"on line...56");
			try {
				pst=con.prepareStatement("insert into foodTable values(?,?,?,?)");
			    pst.setString(1,name);
			    pst.setString(2,email);
			    pst.setString(3,userpass);
			    pst.setInt(4,phone);
				pst.executeUpdate();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
	}

	@Override
	public void init() throws ServletException {
		userName="system";
		password="drowssap";
		path_url="jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 con = DriverManager.getConnection(path_url, userName, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(con);
		System.out.println("initialization code here");
		System.out.println("it gets called always first....only once");
	}
}
