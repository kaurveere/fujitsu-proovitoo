package spring.course.application.service;

import spring.course.application.deliveryfee.DeliveryFeeRequest;

import java.sql.*;

public class WeatherDataAccess { //Retrieves weather data from the database.
    public static DeliveryFeeRequest retrieveData(String name) {
        DeliveryFeeRequest dfr = new DeliveryFeeRequest();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:weather", "sa", "")) {
            String query = "SELECT * FROM weather WHERE name  = \'"+ name +"\'  AND id = (SELECT MAX(id) FROM weather WHERE name = \'"+ name +"\')";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.last()) {
                        int id = resultSet.getInt("id");
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dfr;
    }
}
