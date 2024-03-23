package spring.course.application.service;

import spring.course.application.deliveryfee.WeatherInformation;
import spring.course.application.model.constants.City;

import java.sql.*;

public class WeatherDataAccess { //Retrieves weather data from the database.
    public static WeatherInformation retrieveData(City name, Timestamp timestamp) {
        WeatherInformation dfr = new WeatherInformation();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:weather", "sa", "")) {
            String query = "SELECT * FROM weather WHERE name = ? ORDER BY ABS(TIMESTAMPDIFF(SECOND, timestamp, ?)) LIMIT 1";
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, String.valueOf(name));
                statement.setTimestamp(2, timestamp);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.last()) {

                    //Getting the relevant data
                    String cityName = resultSet.getString("name");
                    String phenomenon = resultSet.getString("phenomenon");
                    double windSpeed = resultSet.getDouble("windspeed");
                    double temperature = resultSet.getDouble("temperature");
                    // Process retrieved data
                    dfr.setName(cityName);
                    dfr.setPhenomenon(phenomenon);
                    dfr.setTemperature(temperature);
                    dfr.setWindspeed(windSpeed);

                } else {
                    System.out.println("No data found.");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dfr;
    }
}
