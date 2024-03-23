package spring.course.application.controllers;

import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.course.application.service.WeatherDataAccess;
import spring.course.application.deliveryfee.VehicleType;
import spring.course.application.webscraping.WebScraper;

import static spring.course.application.deliveryfee.DeliveryFeeCalculator.deliveryFeeRequest;

@RestController
public class UserController {

    //Method to scrape new data on demand
    @PostMapping("/scrape")
    public void scrape(){
        WebScraper.scrape();
    }
    @PostMapping("/retrievedata/{city}")
    public void retriveData(@PathVariable String city){
        WeatherDataAccess.retrieveData(city);
    }
    @GetMapping("calculate/{city}/{vehicle}")
    public double calculate(@PathVariable String city, @PathVariable VehicleType vehicle){
        return deliveryFeeRequest(city, vehicle);
    }

}
