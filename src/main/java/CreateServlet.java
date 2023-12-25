import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		try {
			Class.forName(DbConnectStrings.DRIVER_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sql = "INSERT INTO reviews (title, content) VALUES (?, ?)";
		try (Connection connection = DriverManager.getConnection(DbConnectStrings.URL, DbConnectStrings.USER,
				DbConnectStrings.PASSWORD);
				PreparedStatement statment = connection.prepareStatement(sql)) {

			statment.setString(1, title);
			statment.setString(2, content);
			int number = statment.executeUpdate();

			request.setAttribute("message", "Successfully! Create log");

		} catch (Exception e) {
			request.setAttribute("message", "Execute exception:" + e.getMessage());
		}

		String forward = "/list";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);

	}
}
