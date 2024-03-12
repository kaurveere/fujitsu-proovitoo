package spring.course.application.webscraping;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class WebScrapingJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Executing web scraping job...");
        WebScraper.main(null);
    }
}
