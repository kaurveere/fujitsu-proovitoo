package spring.course.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.course.application.model.WeatherConditions;
@Repository
public interface WeatherConditionsRepository extends CrudRepository<WeatherConditions, Integer> {
}
