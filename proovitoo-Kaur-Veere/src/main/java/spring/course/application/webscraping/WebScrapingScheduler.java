package spring.course.application.webscraping;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class WebScrapingScheduler {

    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(WebScrapingJob.class)
                .withIdentity("webScrapingJob", "group1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("webScrapingTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?")) // Run every 30 minutes
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
