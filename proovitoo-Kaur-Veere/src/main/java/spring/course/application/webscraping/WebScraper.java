package spring.course.application.webscraping;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import spring.course.application.service.WeatherDataInsertion;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
// This class is responsible for scraping weather data from a website.
@Service
public class WebScraper {
    // Scrapes weather data from the website and inserts it into the database.
    public static void scrape(){
        try {
            // Connect to the website and retrieve the HTML document
            Document document = Jsoup.connect("https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php").get();

            //Getting the current timestamp
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime truncatedDateTime = now.withSecond(0).withNano(0);
            Timestamp timestamp = Timestamp.valueOf(truncatedDateTime);

            // Select all "station" elements from the HTML document
            Elements stationElements = document.select("station");

            // Iterate over each "station" element
            for (Element stationElement : stationElements) {
                // Extract the name of the station
                String name = stationElement.select("name").text().trim();

                // Check if the station name is one of the desired ones
                if ("Tartu-Tõravere".equals(name) || "Tallinn-Harku".equals(name) || "Pärnu".equals(name)) {
                    // Extract necessary information from the station element
                    int wmocode = Integer.valueOf(stationElement.select("wmocode").text().trim());
                    double airTemp = Double.parseDouble(stationElement.select("airtemperature").text().trim());
                    double windSpeed = Double.parseDouble(stationElement.select("windspeed").text().trim());
                    String phenomenon = stationElement.select("phenomenom").text().trim();

                    // Insert the extracted data into the database
                    WeatherDataInsertion.insertData(timestamp, name, wmocode, airTemp, windSpeed, phenomenon);

                }
            }
        } catch (IOException e) {
            System.err.println("Error during web scraping: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
