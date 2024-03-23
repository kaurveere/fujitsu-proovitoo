package spring.course.application.service;

import java.sql.*;

public class WeatherDataInsertion {
    public static void insertData(Timestamp timestamp, String name, int wmocode, double airTemp, double windSpeed, String phenomenom) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:weather", "sa", "")) {
            String insertQuery = "INSERT INTO weather (timestamp, name, wmocode, temperature, windspeed, phenomenon) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setTimestamp(1, timestamp);
                switch (name) {
                    case "Pärnu" -> preparedStatement.setString(2, "pärnu");
                    case "Tallinn-Harku" -> preparedStatement.setString(2, "tallinn");
                    case "Tartu-Tõravere" -> preparedStatement.setString(2, "tartu");
                    default -> System.out.println("Wrong city name");
                }
                preparedStatement.setInt(3, wmocode);
                preparedStatement.setDouble(4, airTemp);
                preparedStatement.setDouble(5, windSpeed);
                preparedStatement.setString(6, phenomenom);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
