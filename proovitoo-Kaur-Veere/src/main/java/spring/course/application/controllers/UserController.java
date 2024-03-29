package spring.course.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.course.application.exception.ApiException;
import spring.course.application.exception.ForbiddenVehicleException;
import spring.course.application.model.constants.City;
import spring.course.application.model.constants.VehicleType;
import spring.course.application.webscraping.WebScraper;

import java.sql.Timestamp;

import static spring.course.application.deliveryfee.FeeCalculator.calculateFee;

@RestController
public class UserController {
    /**
     * sample requests:
     * .../calculate/tallinn/car
     * .../calculate/parnu/car/2024-03-24 09:00:00
     *
     * @param city              supported cities - tallinn, tartu, parnu
     * @param vehicle           supported vehicle types - car, scooter, bike
     * @param timestamp         format 'yyyy-mm-dd hh:mm:ss' , optional, tries to find the closest weather data to this timestamp
     * @return ResponseEntity - if an exception occurred, then the error message will be returned, otherwise the calulated fee
     */

    //Function to calculate the fee without a timestamp
    @GetMapping("calculate/{city}/{vehicle}")
    public ResponseEntity<?> getFee(
            @PathVariable("city") String cityStr,
            @PathVariable("vehicle") String vehicleStr) {
        try {

            City city = City.valueOf(cityStr);
            VehicleType vehicle = VehicleType.valueOf(vehicleStr);

            double fee = calculateFee(city, vehicle, new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.ok(fee);
        } catch (IllegalArgumentException e) {
            ApiException apiException = new ApiException(
                    "Invalid city or vehicle type",
                    HttpStatus.BAD_REQUEST,
                    new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
        } catch (ForbiddenVehicleException e) {
            ApiException apiException = new ApiException(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
        }
    }
    //Function to calculate the fee with a timestamp
    @GetMapping("calculate/{city}/{vehicle}/{timestamp}")
    public ResponseEntity<?> getFee(@PathVariable("city") String cityStr,
                                     @PathVariable("vehicle") String vehicleStr,
                                     @PathVariable(name = "timestamp") String timestampStr) {
        try {
            City city = City.valueOf(cityStr);
            VehicleType vehicle = VehicleType.valueOf(vehicleStr);
            Timestamp timestamp = Timestamp.valueOf(timestampStr);

            double fee = calculateFee(city, vehicle, timestamp);
            return ResponseEntity.ok(fee);
        } catch (IllegalArgumentException e) {
            ApiException apiException = new ApiException(
                    "Invalid city or vehicle type or timestamp",
                    HttpStatus.BAD_REQUEST,
                    new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
        } catch (ForbiddenVehicleException e) {
            ApiException apiException = new ApiException(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
        }
    }
    //Function to scrape new data on demand
    @PostMapping("/scrape")
    public void scrape(){
        WebScraper.scrape();
    }

}
