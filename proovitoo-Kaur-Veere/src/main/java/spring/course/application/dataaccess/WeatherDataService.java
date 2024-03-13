package spring.course.application.dataaccess;

import spring.course.application.deliveryfee.DeliveryFeeRequest;

import java.sql.*;

public class WeatherDataService { //Retrieves weather data from the database.
    public static DeliveryFeeRequest retrieveData(int i) {
        DeliveryFeeRequest dfr = new DeliveryFeeRequest();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:weather", "sa", "")) {
            String query = "SELECT * FROM weather WHERE id % 3 = " + i + " ORDER BY id ASC LIMIT ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, i); // Set the limit parameter
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.last()) {
                        int id = resultSet.getInt("id");
                        //Getting the relevant data
                        String name = resultSet.getString("name");
                        String phenomenom = resultSet.getString("phenomenom");
                        double windSpeed = resultSet.getDouble("windspeed");
                        double temperature = resultSet.getDouble("temperature");
                        // Process retrieved data
                        dfr.setName(name);
                        dfr.setPhenomenom(phenomenom);
                        dfr.setTemperature(temperature);
                        dfr.setWindspeed(windSpeed);
                        //printing the retrieved data
                        System.out.println("ID: " + id + ", Name: " + name + ", Phenomenom: " + phenomenom + ", Windspeed: " + windSpeed);
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
