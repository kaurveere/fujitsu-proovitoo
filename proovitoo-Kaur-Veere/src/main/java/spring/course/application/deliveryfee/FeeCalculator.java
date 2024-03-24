package spring.course.application.deliveryfee;

import spring.course.application.model.WeatherInformation;
import spring.course.application.exception.ForbiddenVehicleException;
import spring.course.application.model.constants.City;
import spring.course.application.model.constants.Phenomenon;
import spring.course.application.model.constants.VehicleType;

import java.sql.Timestamp;

import static spring.course.application.service.WeatherDataAccess.retrieveData;

//Orchastrates the fee calculation process
public class FeeCalculator {
    /**
     * Calculates the fee based on city, vehicle type, and timestamp.
     *
     * @param city The city for which the fee is calculated.
     * @param vehicleType The type of vehicle.
     * @param timestamp The timestamp for which the fee is calculated.
     * @return The calculated fee.
     * @throws ForbiddenVehicleException If the vehicle is not allowed in the city.
     */
    public static double calculateFee(City city, VehicleType vehicleType, Timestamp timestamp) throws ForbiddenVehicleException {
        // Retrieve weather information for the given city and timestamp
        WeatherInformation weatherInformation = retrieveData(city, timestamp);

        // Calculate RBF (regional base fee)
        double rbf = calculateRBF(city, vehicleType);

        // Calculate ATEF (Air temperature extra fee)
        double atef = calculateATEF(weatherInformation, vehicleType);

        // Calculate WSEF (Wind speed extra fee)
        double wsef = calculateWSEF(weatherInformation, vehicleType);

        // Calculate WPEF (Weather phenomenon extra fee)
        double wpef = calculateWPEF(weatherInformation, vehicleType);

        // Sum up all the calculated fees
        return rbf + atef + wsef + wpef;
    }

    //Calculates the regional base fee
    private static double calculateRBF(City city, VehicleType vehicle) {
        switch (vehicle) {
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
    private static double calculateWSEF(WeatherInformation weatherInformation, VehicleType vehicleType) throws ForbiddenVehicleException {
        double windSpeed = weatherInformation.getWindspeed();
        if (vehicleType == VehicleType.bike) {
            if (windSpeed >= 10 && windSpeed <= 20) {
                return 0.5;
            } else if (windSpeed > 20) {
                throw new ForbiddenVehicleException();
            }
        }
        return 0;
    }

    //Calculates the extra fee for weather phenomens
    private static double calculateWPEF(WeatherInformation weatherInformation, VehicleType vehicleType) throws ForbiddenVehicleException {
        Phenomenon phenomenon = weatherInformation.getPhenomenon();
        if (vehicleType != VehicleType.car) {
            switch (phenomenon) {
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
                    throw new ForbiddenVehicleException();
                }
            }
        }
        return 0;
    }

    //Calculates the extra fee for air temperature
    private static double calculateATEF(WeatherInformation weatherInformation, VehicleType vehicleType) {
        double airTemp = weatherInformation.getTemperature();
        if (vehicleType != VehicleType.car) {
            if (airTemp < -10) return 1;
            if (airTemp <= 0) return 0.5;
        }
        return 0;
    }

    static double getCarFee(City city) {
        switch (city) {
            case tartu -> {
                return getCarTartuFee();
            }
            case parnu -> {
                return getCarParnuFee();
            }
            case tallinn -> {
                return getCarTallinnFee();
            }
        }
        return 0;
    }

    static double getScooterFee(City city) {
        switch (city) {
            case tartu -> {
                return getScooterTartuFee();
            }
            case parnu -> {
                return getScooterParnuFee();
            }
            case tallinn -> {
                return getScooterTallinnFee();
            }
        }
        return 0;
    }

    static double getBikeFee(City city) {
        switch (city) {
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
    //Different fees and getters
    private static double carTartuFee = 3.5;
    private static double scooterTartuFee = 3;
    private static double bikeTartuFee = 2.5;
    private static double carTallinnFee = 4;
    private static double scooterTallinnFee = 3.5;
    private static double bikeTallinnFee = 3;
    private static double carParnuFee = 3;
    private static double scooterParnuFee = 2.5;
    private static double bikeParnuFee = 2;

    public static double getCarTartuFee() {
        return carTartuFee;
    }

    public static double getScooterTartuFee() {
        return scooterTartuFee;
    }

    public static double getBikeTartuFee() {
        return bikeTartuFee;
    }

    public static double getCarTallinnFee() {
        return carTallinnFee;
    }

    public static double getScooterTallinnFee() {
        return scooterTallinnFee;
    }

    public static double getBikeTallinnFee() {
        return bikeTallinnFee;
    }

    public static double getCarParnuFee() {
        return carParnuFee;
    }

    public static double getScooterParnuFee() {
        return scooterParnuFee;
    }

    public static double getBikeParnuFee() {
        return bikeParnuFee;
    }
}