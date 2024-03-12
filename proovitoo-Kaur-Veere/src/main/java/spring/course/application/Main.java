package spring.course.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import spring.course.application.model.WeatherConditions;
import spring.course.application.repository.WeatherConditionsRepository;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(WeatherConditionsRepository repository) {
		return args -> {
			//add testdata
			repository.save(new WeatherConditions(null, 123 ,"Tartu", 456, 10.0, 5.0, ""));
		};
	}

}
