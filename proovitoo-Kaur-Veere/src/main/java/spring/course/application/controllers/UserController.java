package spring.course.application.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.course.application.model.constants.City;
import spring.course.application.model.constants.VehicleType;
import spring.course.application.webscraping.WebScraper;

import java.sql.Timestamp;

import static spring.course.application.controllers.FeeCalculator.calculateFee;

@RestController
public class UserController {

    //Method to scrape new data on demand
    @PostMapping("/scrape")
    public void scrape(){
        WebScraper.scrape();
    }
    @GetMapping("calculate/{city}/{vehicle}")
    public double getFee(@PathVariable("city") City city,
                         @PathVariable("vehicle") VehicleType vehicle) {
        return calculateFee(city, vehicle, new Timestamp(System.currentTimeMillis()));
    }
    @GetMapping("calculate/{city}/{vehicle}/{timestamp}")
    public double getFee(@PathVariable("city") City city,
                         @PathVariable("vehicle") VehicleType vehicle,
                         @PathVariable(name = "timestamp") String timestamp) {
        System.out.println(timestamp);
        return calculateFee(city, vehicle, Timestamp.valueOf(timestamp));
    }

}
