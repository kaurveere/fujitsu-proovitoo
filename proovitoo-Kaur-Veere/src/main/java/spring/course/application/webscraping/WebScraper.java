package spring.course.application.webscraping;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import spring.course.application.model.WeatherConditions;
import spring.course.application.repository.WeatherConditionsRepository;
import spring.course.application.weatherdataInsertion.WeatherDataInsertion;

import java.io.IOException;

public class WebScraper {
    public static void main(String[] args) {
        System.out.println("alustan scrapeimist" + '\n' + "--------------");
        scrape();
    }
    public static void scrape(){
        try {
            Document document = Jsoup.connect("https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php").get();
            //Getting the timestamp
            int timestamp = Integer.valueOf(document.select("observations").attr("timestamp"));
            // Select all "station" elements
            Elements stationElements = document.select("station");

            // Iterate over each "station" element
            for (Element stationElement : stationElements) {
                // Extract the "name" value
                String name = stationElement.select("name").text().trim();

                // Check if the station name
                if ("Tartu-Tõravere".equals(name) || "Tallinn-Harku".equals(name) || "Pärnu".equals(name)) {
                    // Extract other information
                    int wmocode = Integer.valueOf(stationElement.select("wmocode").text().trim());
                    double airTemp = Double.parseDouble(stationElement.select("airtemperature").text().trim());
                    double windSpeed = Double.parseDouble(stationElement.select("windspeed").text().trim());
                    String phenomenon = stationElement.select("phenomenom").text().trim();

                    /*
                    System.out.println("Timestamp: " + timestamp);
                    System.out.println("Name: " + name);
                    System.out.println("Wmocode: " + wmocode);
                    System.out.println("Air temperature: " + airTemp);
                    System.out.println("Windspeed: " + windSpeed);
                    System.out.println("Phenomenom: " + phenomenon);
                     */

                    WeatherDataInsertion.insertData(timestamp, name, wmocode, airTemp, windSpeed, phenomenon);

                }
            }
        } catch (IOException e) {
            // Handle exceptions more gracefully
            System.err.println("Error during web scraping: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
