import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DestroyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int postId = Integer.parseInt(request.getParameter("id"));

		try {
			Class.forName(DbConnectStrings.DRIVER_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sql = "DELETE FROM reviews WHERE id = ?";
		try (Connection connection = DriverManager.getConnection(DbConnectStrings.URL, DbConnectStrings.USER,
				DbConnectStrings.PASSWORD);
				PreparedStatement statment = connection.prepareStatement(sql)) {

			statment.setInt(1, postId);
			int number = statment.executeUpdate();

			request.setAttribute("message", "Successfully! Delete log " + postId);

		} catch (Exception e) {
			request.setAttribute("message", "Execute exception:" + e.getMessage());
		}

		String forward = "/list";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);

	}
}
