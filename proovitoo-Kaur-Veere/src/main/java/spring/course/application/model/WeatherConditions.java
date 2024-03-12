package spring.course.application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "WEATHER")
public record WeatherConditions(@Id Integer id, Integer timestamp, String name, Integer wmocode, Double temperature, Double windspeed, String phenomenon) {
}
