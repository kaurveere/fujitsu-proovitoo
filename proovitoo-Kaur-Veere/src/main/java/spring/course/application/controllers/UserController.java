package spring.course.application.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.course.application.webscraping.WebScraper;

@RestController
public class UserController {

    //Method to scrape new data on demand
    @PostMapping("/scrape")
    public void scrape(){
        WebScraper.main(null);
    }

}
