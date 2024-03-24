package spring.course.application;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import spring.course.application.controllers.UserController;
import spring.course.application.deliveryfee.FeeCalculator;
import spring.course.application.exception.ApiException;
import spring.course.application.exception.ForbiddenVehicleException;
import spring.course.application.model.constants.City;
import spring.course.application.model.constants.VehicleType;
import spring.course.application.service.WeatherDataAccess;
import spring.course.application.service.WeatherDataInsertion;
import spring.course.application.model.WeatherInformation;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ApplicationTests {
	@Mock
	private FeeCalculator feeCalculator;

	@InjectMocks
	private UserController userController;
	@Test
	void getFee_WithInvalidCityOrVehicle_ReturnsBadRequest() {
		String city = "invalidCity";
		String vehicle = "invalidVehicle";

		ResponseEntity<?> response = userController.getFee(city, vehicle);

		assertEquals("Invalid city or vehicle type", ((ApiException) Objects.requireNonNull(response.getBody())).message());
	}
	@Test
	void getFee_WithValidCityAndVehicle_ReturnsFee() throws ForbiddenVehicleException {
		// Arrange
		City city = City.tallinn;
		VehicleType vehicle = VehicleType.car;
		double expectedFee = 4.0; // Updated expected fee value

		// Act
		double response = FeeCalculator.calculateFee(city, vehicle, Timestamp.valueOf("2024-01-01 00:00:00"));

		// Assert
		assertEquals(expectedFee, response);
	}

	private void initializeRepository() {
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-01-01 00:00:00"), "Tartu-Tõravere", 123, -2, 30, "Thunderstorm");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-02-01 00:01:00"), "Tartu-Tõravere", 123, 20, 2, "Light snow shower");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-03-01 00:02:00"), "Tartu-Tõravere", 123, 20, 2, "Light rain");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-01-01 00:00:00"), "Tallinn-Harku", 123, 20, 2, "Heavy snowfall");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-02-01 00:01:00"), "Tallinn-Harku", 123, 20, 2, "Few clouds");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-03-01 00:02:00"), "Tallinn-Harku", 123, 20, 2, "Hail");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-01-01 00:00:00"), "Pärnu", 123, 20, 2, "Light rain");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-02-01 00:01:00"), "Pärnu", 123, 20, 2, "Moderate shower");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-03-01 00:02:00"), "Pärnu", 123, 20, 2, "Mist");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-03-24 09:00:00"), "Tallinn-Harku", 123, 20.0, 5.0, "Sunny");
	}
}
