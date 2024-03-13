package spring.course.application.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WeatherDataInsertion {
    public static void insertData(int timestamp, String name, int wmocode, double airTemp, double windSpeed, String phenomenom) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:weather", "sa", "")) {
            // Insert data into the 'weather' table
            String insertQuery = "INSERT INTO weather (timestamp, name, wmocode, temperature, windspeed, phenomenon) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

                preparedStatement.setInt(1, timestamp);
                switch (name) {
                    case "Pärnu" -> preparedStatement.setString(2, "Pärnu");
                    case "Tallinn-Harku" -> preparedStatement.setString(2, "Tallinn");
                    case "Tartu-Tõravere" -> preparedStatement.setString(2, "Tartu");
                    default -> preparedStatement.setString(2, "Wrong city name");
                }
                preparedStatement.setInt(3, wmocode);
                preparedStatement.setDouble(4, airTemp);
                preparedStatement.setDouble(5, windSpeed);
                preparedStatement.setString(6, phenomenom);

                int rowsAffected = preparedStatement.executeUpdate();
                //System.out.println("Rows affected: " + rowsAffected);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
