package com.myQuiz.quiz.controls;

import java.io.*;
import java.sql.*;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.myQuiz.quiz.DatabaseConnection;

@MultipartConfig
@WebServlet("/checkRegister")
public class RegistrationControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationControl() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Collection<Part> parts = request.getParts();
		String resp = "WEB-INF/jsps/regSuccess.jsp";
		if (parts.size() != 3) {
			System.out.println("all details are not entered");
		}

		Part filePart = request.getPart("photo");
		InputStream imageInputStream = filePart.getInputStream();
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		String city = request.getParameter("City");

		Connection con = DatabaseConnection.createConnection();

		try {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO users(username,password,email,mobile,address,photo) values(?,?,?,?,?,?)");

			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, mobile);
			ps.setString(5, city);
			ps.setBinaryStream(6, imageInputStream, imageInputStream.available());
			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("image inserted successfully");
			} else {
				System.out.println("problem in image insertion");
			}
		} catch (SQLException sqe) {
			resp = "/WEB-INF/jsps/register.jsp";
			System.out.println("Error : While Inserting record in database");
			request.setAttribute("err", sqe.getMessage());
		}
		try {
			con.close();
		} catch (SQLException se) {
			System.out.println("Error : While Closing Connection");

		}
		request.setAttribute("newUser", username);
		RequestDispatcher dispatcher = request.getRequestDispatcher(resp);
		dispatcher.forward(request, response);
	}
}
