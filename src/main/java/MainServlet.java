import models.Weather;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "MainServlet", urlPatterns = {"/main"})
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Weather> weathers = new ArrayList<Weather>();

        Statement statement = null;
        try {
            InitialContext context = new InitialContext();

            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/postgres");

            if (dataSource == null) throw new RuntimeException("Data source not found!");

            Connection connection = dataSource.getConnection();

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT weather_id, city, temperature" +
                    " FROM weather");
            while (resultSet.next()) {
                Weather weather = new Weather(resultSet.getInt("weather_id"),
                                                resultSet.getString("city"),
                                                resultSet.getString("temperature"));

                weathers.add(weather);
            }
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {

                }
            }
        }

        req.setAttribute("size", weathers.size());
        req.setAttribute("weathers", weathers);
        req.getRequestDispatcher("/weather.jsp").forward(req, resp);

    }
}
