package spring.course.application.service;

import spring.course.application.model.WeatherInformation;
import spring.course.application.model.constants.City;

import java.sql.*;

public class WeatherDataAccess { //Retrieves weather data from the database.
    /**
     * Retrieves weather data for a specific city and timestamp from the database.
     *
     * @param name      The name of the city for which weather data is retrieved.
     * @param timestamp The timestamp for which weather data is retrieved.
     * @return WeatherInformation object containing the retrieved weather data.
     */
    public static WeatherInformation retrieveData(City name, Timestamp timestamp) {
        //Creates an object to store weather data
        WeatherInformation weatherInformation = new WeatherInformation();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:weather", "sa", "")) {
            // Query to retrieve weather data with the smallest time difference
            String query = "SELECT * FROM weather WHERE name = ? ORDER BY ABS(TIMESTAMPDIFF(SECOND, timestamp, ?)) LIMIT 1";
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, String.valueOf(name));
                statement.setTimestamp(2, timestamp);

                ResultSet resultSet = statement.executeQuery();
                // Check if any data is retrieved
                if (resultSet.last()) {
                    //Getting the relevant data
                    String cityName = resultSet.getString("name");
                    String phenomenon = resultSet.getString("phenomenon");
                    double windSpeed = resultSet.getDouble("windspeed");
                    double temperature = resultSet.getDouble("temperature");

                    // Process retrieved data
                    weatherInformation.setName(cityName);
                    weatherInformation.setPhenomenon(phenomenon);
                    weatherInformation.setTemperature(temperature);
                    weatherInformation.setWindspeed(windSpeed);

                } else {
                    System.out.println("No data found.");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weatherInformation;
    }
}
