package com.myQuiz.quiz.controls;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myQuiz.quiz.DatabaseConnection;

@WebServlet("/dowImage")
public class DisplayImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		String sql = "select photo from users where id = ?";
		Object[] photo = getImageBytes(sql, id);
		if (photo[0] != null) {
			response.setContentType("image/jpeg");
			BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream((byte[]) photo[0]));
			ServletOutputStream outStream = response.getOutputStream();
			int i = 0;
			while (i != -1) {
				i = in.read();
				outStream.write(i);
			}
			in.close();
		}
	}

	private Object[] getImageBytes(String sql, String id) {
		Object[] obj = new Object[1];
		if (id != null && !id.equalsIgnoreCase("0") && !id.equalsIgnoreCase("-1")) {
			try (Connection connection = DatabaseConnection.createConnection();
					PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, id.trim());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					obj[0] = rs.getBytes(1);

				}
				rs.close();
				rs = null;
			} catch (Exception e) {
				System.out.println("Error while downloading photo");
			}
		}
		return obj;
	}

}
