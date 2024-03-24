package spring.course.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.course.application.webscraping.WebScraper;

@Component
public class ScheduledTasks {

    public ScheduledTasks(WebScraper webScraper) {
    }
    // Cron expression for running 15 minutes past every full hour
    @Scheduled(cron = "0 15 * * * *")
    public void execute() {
        WebScraper.scrape();
    }
}
