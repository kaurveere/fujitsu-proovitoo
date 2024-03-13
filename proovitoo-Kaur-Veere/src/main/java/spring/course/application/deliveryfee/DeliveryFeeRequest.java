package spring.course.application.deliveryfee;

public class DeliveryFeeRequest {//class to store weather data
    private String name;
    private String phenomenon;
    private double temperature;
    private double windspeed;

    public DeliveryFeeRequest() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
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

    public String getPhenomenon() {
        return phenomenon;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindspeed() {
        return windspeed;
    }
}
