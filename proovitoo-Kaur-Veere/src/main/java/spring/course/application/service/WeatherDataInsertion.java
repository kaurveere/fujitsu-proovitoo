package spring.course.application.service;

import java.sql.*;

public class WeatherDataInsertion {
    /**
     * Inserts weather data into the database.
     *
     * @param timestamp   The timestamp for the weather data.
     * @param name        The name of the city for which weather data is inserted.
     * @param wmocode     The weather observation code.
     * @param airTemp     The air temperature.
     * @param windSpeed   The wind speed.
     * @param phenomenom  The weather phenomenon.
     */
    public static void insertData(Timestamp timestamp, String name, int wmocode, double airTemp, double windSpeed, String phenomenom) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:weather", "sa", "")) {
            // SQL query to insert weather data into the database
            String insertQuery = "INSERT INTO weather (timestamp, name, wmocode, temperature, windspeed, phenomenon) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setTimestamp(1, timestamp);
                switch (name) {
                    case "Pärnu":
                        preparedStatement.setString(2, "parnu");
                        break;
                    case "Tallinn-Harku":
                        preparedStatement.setString(2, "tallinn");
                        break;
                    case "Tartu-Tõravere":
                        preparedStatement.setString(2, "tartu");
                        break;
                    default:
                        System.out.println("Wrong city name");
                        return; // Exit method if city name is not recognized
                }
                preparedStatement.setInt(3, wmocode);
                preparedStatement.setDouble(4, airTemp);
                preparedStatement.setDouble(5, windSpeed);
                preparedStatement.setString(6, phenomenom);

                // Execute the SQL update
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
