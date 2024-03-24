package spring.course.application.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import spring.course.application.webscraping.WebScraper;

@Configuration
@EnableScheduling
public class ScheduledTasksConfig {

    @Bean
    public ScheduledTasks scheduledTasks(WebScraper webScraper) {
        return new ScheduledTasks(webScraper);
    }
}
