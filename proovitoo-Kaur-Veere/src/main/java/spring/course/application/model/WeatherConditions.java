package spring.course.application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table(name = "WEATHER")
public record WeatherConditions(@Id Integer id, Timestamp timestamp, String name, Integer wmocode, Double temperature, Double windspeed, String phenomenon) {
}
