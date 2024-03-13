package spring.course.application.deliveryfee;

public class DeliveryFeeRequest {//class to store weather data
    private String name;
    private String phenomenom;
    private double temperature;
    private double windspeed;

    public DeliveryFeeRequest() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhenomenom(String phenomenom) {
        this.phenomenom = phenomenom;
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

    public String getPhenomenom() {
        return phenomenom;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindspeed() {
        return windspeed;
    }
}
