package spring.course.application.dataaccess;

import java.sql.*;

public class WeatherDataService { //Retrieves weather data from the database.
    public static void retrieveData(int i) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:weather", "sa", "")) {
            String query = "SELECT * FROM weather WHERE id % 3 = " + i + " ORDER BY id ASC LIMIT ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, i); // Set the limit parameter
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.last()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        // Retrieve other columns as needed

                        // Process retrieved data
                        System.out.println("ID: " + id + ", Name: " + name);
                    } else {
                        System.out.println("No data found.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
