import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int postId = Integer.parseInt(request.getParameter("id"));
		if (request.getAttribute("message") == null) {
			request.setAttribute("message", "Edit your post " + postId);
		}

		try {
			Class.forName(DbConnectStrings.DRIVER_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sql = "SELECT * FROM reviews WHERE id =?";
		try (Connection connection = DriverManager.getConnection(DbConnectStrings.URL, DbConnectStrings.USER,
				DbConnectStrings.PASSWORD);
				PreparedStatement statment = connection.prepareStatement(sql)) {
			statment.setInt(1, postId);
			ResultSet results = statment.executeQuery();

			while (results.next()) {

				String id = results.getString("id");
				request.setAttribute("id", id);

				String title = results.getString("title");
				request.setAttribute("title", title);

				String content = results.getString("content");
				request.setAttribute("content", content);
			}

		} catch (Exception e) {
			request.setAttribute("message", "Exception:" + e.getMessage());
		}

		String view = "/WEB-INF/views/edit.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);

	}
}
