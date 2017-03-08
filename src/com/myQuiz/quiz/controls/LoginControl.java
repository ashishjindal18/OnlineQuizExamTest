package com.myQuiz.quiz.controls;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.myQuiz.quiz.DatabaseConnection;

@WebServlet("/checkLogin")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginControl() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Connection con = DatabaseConnection.createConnection();
		ResultSet set = null;
		String id = "";
		int i = 0;

		try {
			Statement st = con.createStatement();
			String sql = "Select * from  users where username='" + username + "' and password='" + password + "' ";
			System.out.println(sql);
			set = st.executeQuery(sql);
			while (set.next()) {
				i = 1;
				id = set.getString("id");
			}
			if (i != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("user", username);
				session.setAttribute("id", id);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsps/home.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("errorMessage", "Invalid username or password");
				RequestDispatcher rd = request.getRequestDispatcher("/WebContent/jsps/login.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException sqe) {
			System.out.println("Error : While Fetching records from database");
		}
		try {
			con.close();
		} catch (SQLException se) {
			System.out.println("Error : While Closing Connection");
		}
	}
}
