package spring.course.application.controllers;

import spring.course.application.deliveryfee.WeatherInformation;
import spring.course.application.model.constants.City;
import spring.course.application.model.constants.Phenomenon;
import spring.course.application.model.constants.VehicleType;

import java.sql.Timestamp;

import static spring.course.application.service.WeatherDataAccess.retrieveData;

//Orchastrates the fee calculation process
public class FeeCalculator {
    public static double calculateFee(City city, VehicleType vehicleType, Timestamp timestamp){

        WeatherInformation weatherInformation = retrieveData(city, timestamp);

        double rbf = calculateRBF(city, vehicleType);
        double atef = calculateATEF(weatherInformation, vehicleType);
        double wsef = calculateWSEF(weatherInformation, vehicleType);//TODO - error handeling
        double wpef = calculateWPEF(weatherInformation, vehicleType);//TODO - error handeling

        return rbf + atef + wsef + wpef;
    }
    //Calculates the regional base fee
    private static double calculateRBF(City city, VehicleType vehicle) {
        switch (vehicle){
            case car -> {
                return getCarFee(city);
            }
            case bike -> {
                return getBikeFee(city);
            }
            case scooter -> {
                return getScooterFee(city);
            }
        }
        return 0;
    }
    //Calculates the exta fee for wind speed
    private static double calculateWSEF(WeatherInformation weatherInformation, VehicleType vehicleType) {
        double windSpeed = weatherInformation.getWindspeed();
        if(vehicleType==VehicleType.bike){
            if(windSpeed>=10 && windSpeed<=20) {
                return 0.5;
            } else if (windSpeed>20){
                return 0;//TODO error of usage of vehicle is forbidden
            }
        }
        return 0;
    }
    //Calculates the extra fee for weather phenomens
    private static double calculateWPEF(WeatherInformation weatherInformation, VehicleType vehicleType) {
        Phenomenon phenomenon = weatherInformation.getPhenomenon();
        if(vehicleType != VehicleType.car){
            switch (phenomenon){
                case normal -> {
                    return 0;
                }
                case rain -> {
                    return 0.5;
                }
                case snow -> {
                    return 1;
                }
                case forbidden -> {
                    return 0;//TODO - error handel
                }
            }
        }
        return 0;
    }
    //Calculates the extra fee for air temperature
    private static double calculateATEF(WeatherInformation weatherInformation, VehicleType vehicleType) {
        double airTemp = weatherInformation.getTemperature();
        if(vehicleType!=VehicleType.car){
            if(airTemp<-10) return 1;
            if(airTemp<=0) return 0.5;
        }
        return 0;
    }

    static double getCarFee(City city){
        switch (city){
            case tartu -> {
                return getCarTartuFee();
            }
            case parnu -> {
                return getCarParnuFee();
            }
            case tallinn -> getCarTallinnFee();
        }
        return 0;
    }
    static double getScooterFee(City city){
        switch (city){
            case tartu -> {
                return getScooterTartuFee();
            }
            case parnu -> {
                return getScooterParnuFee();
            }
            case tallinn -> getScooterTallinnFee();
        }
        return 0;
    }
    static double getBikeFee(City city){
        switch (city){
            case tartu -> {
                return getBikeTartuFee();
            }
            case parnu -> {
                return getBikeParnuFee();
            }
            case tallinn -> getBikeTallinnFee();
        }
        return 0;
    }
    private static double carTartuFee = 4;
    private static double scooterTartuFee = 3.5;
    private static double bikeTartuFee = 3;
    private static double carTallinnFee = 3.5;
    private static double scooterTallinnFee = 3;
    private static double bikeTallinnFee = 2.5;
    private static double carParnuFee = 3;
    private static double scooterParnuFee = 2.5;
    private static double bikeParnuFee = 2;

    public static double getCarTartuFee() {
        return carTartuFee;
    }

    public void setCarTartuFee(double carTartuFee) {
        this.carTartuFee = carTartuFee;
    }

    public static double getScooterTartuFee() {
        return scooterTartuFee;
    }

    public void setScooterTartuFee(double scooterTartuFee) {
        this.scooterTartuFee = scooterTartuFee;
    }

    public static double getBikeTartuFee() {
        return bikeTartuFee;
    }

    public void setBikeTartuFee(double bikeTartuFee) {
        this.bikeTartuFee = bikeTartuFee;
    }

    public static double getCarTallinnFee() {
        return carTallinnFee;
    }

    public void setCarTallinnFee(double carTallinnFee) {
        this.carTallinnFee = carTallinnFee;
    }

    public static double getScooterTallinnFee() {
        return scooterTallinnFee;
    }

    public void setScooterTallinnFee(double scooterTallinnFee) {
        this.scooterTallinnFee = scooterTallinnFee;
    }

    public static double getBikeTallinnFee() {
        return bikeTallinnFee;
    }

    public void setBikeTallinnFee(double bikeTallinnFee) {
        this.bikeTallinnFee = bikeTallinnFee;
    }

    public static double getCarParnuFee() {
        return carParnuFee;
    }

    public void setCarParnuFee(double carParnuFee) {
        this.carParnuFee = carParnuFee;
    }

    public static double getScooterParnuFee() {
        return scooterParnuFee;
    }

    public void setScooterParnuFee(double scooterParnuFee) {
        this.scooterParnuFee = scooterParnuFee;
    }

    public static double getBikeParnuFee() {
        return bikeParnuFee;
    }

    public void setBikeParnuFee(double bikeParnuFee) {
        this.bikeParnuFee = bikeParnuFee;
    }
}
