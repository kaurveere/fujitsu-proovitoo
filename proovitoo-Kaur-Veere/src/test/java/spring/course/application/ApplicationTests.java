package spring.course.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.course.application.service.WeatherDataInsertion;

import java.sql.Timestamp;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		initializeRepository();
		
	}

	private void initializeRepository() {
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-01-01 00:00:00"), "tartu", 123, -2, 30, "Thunderstorm");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-02-01 00:01:00"), "tartu", 123, 20, 2, "Light snow shower");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-03-01 00:02:00"), "tartu", 123, 20, 2, "Light rain");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-01-01 00:00:00"), "tallinn", 123, 20, 2, "Heavy snowfall");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-02-01 00:01:00"), "tallinn", 123, 20, 2, "Few clouds");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-03-01 00:02:00"), "tallinn", 123, 20, 2, "Hail");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-01-01 00:00:00"), "parnu", 123, 20, 2, "Light rain");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-02-01 00:01:00"), "parnu", 123, 20, 2, "Moderate shower");
		WeatherDataInsertion.insertData(Timestamp.valueOf("2024-03-01 00:02:00"), "parnu", 123, 20, 2, "Mist");
	}
}
