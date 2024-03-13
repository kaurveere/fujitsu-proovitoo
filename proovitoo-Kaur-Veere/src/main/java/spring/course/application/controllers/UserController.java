package spring.course.application.controllers;

import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.course.application.dataaccess.WeatherDataService;
import spring.course.application.webscraping.WebScraper;
import spring.course.application.webscraping.WebScrapingJob;
import spring.course.application.webscraping.WebScrapingScheduler;

@RestController
public class UserController {

    //Method to scrape new data on demand
    @PostMapping("/scrape")
    public void scrape(){
        WebScraper.main(null);
    }
    //Method to start scrapeing job every 30s
    @PostMapping("/scrapejob")
    public void scrapejob() throws SchedulerException {
        WebScrapingScheduler.main(null);
    }
    @PostMapping("/retrievedata/{city}")
    public void retriveData(@PathVariable String city){
        switch (city) {
            case "tallinn" -> WeatherDataService.retrieveData(1);
            case "tartu" -> WeatherDataService.retrieveData(2);
            case "parnu" -> WeatherDataService.retrieveData(0);
            default -> System.out.println("Viga");
        }
    }

}
