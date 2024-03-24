package spring.course.application.deliveryfee;

import spring.course.application.model.constants.Phenomenon;

import java.util.HashMap;
import java.util.Map;

public class WeatherInformation {//class to store weather data
    private String name;
    private Phenomenon phenomenon = Phenomenon.normal;
    private double temperature;
    private double windspeed;
    private final Map<String, Phenomenon> phenomenonMap = new HashMap<String, Phenomenon>() {{
        put("", Phenomenon.normal);
        put("clear", Phenomenon.normal);
        put("Few clouds", Phenomenon.normal);
        put("Variable clouds", Phenomenon.normal);
        put("Cloudy with clear spells", Phenomenon.normal);
        put("Overcast", Phenomenon.normal);
        put("Light snow shower", Phenomenon.snow);
        put("Moderate snow shower", Phenomenon.snow);
        put("Heavy snow shower", Phenomenon.snow);
        put("Light shower", Phenomenon.rain);
        put("Moderate shower", Phenomenon.rain);
        put("Heavy shower", Phenomenon.rain);
        put("Light rain", Phenomenon.rain);
        put("Moderate rain", Phenomenon.rain);
        put("Heavy rain", Phenomenon.rain);
        put("Glaze", Phenomenon.forbidden);
        put("Light sleet", Phenomenon.snow);
        put("Moderate sleet", Phenomenon.snow);
        put("Light snowfall", Phenomenon.snow);
        put("Moderate snowfall", Phenomenon.snow);
        put("Heavy snowfall", Phenomenon.snow);
        put("Blowing snow", Phenomenon.snow);
        put("Drifting snow", Phenomenon.snow);
        put("Hail", Phenomenon.forbidden);
        put("Mist", Phenomenon.normal);
        put("Fog", Phenomenon.normal);
        put("Thunder", Phenomenon.forbidden);
        put("Thunderstorm", Phenomenon.forbidden);
    }};
    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenonMap.get(phenomenon);
    }
    public WeatherInformation() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public String getName() {
        return name;
    }

    public Phenomenon getPhenomenon() {
        return phenomenon;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindspeed() {
        return windspeed;
    }
}
