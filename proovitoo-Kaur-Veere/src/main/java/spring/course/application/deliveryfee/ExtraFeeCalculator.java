package spring.course.application.deliveryfee;

public class ExtraFeeCalculator { //Calculates extra fees

    public static double calculateExtraFee(double temperature, double windSpeed, String weatherPhenomenon, VehicleType vehicleType) {
        double extraFee = 0.0;

        if (vehicleType == VehicleType.scooter || vehicleType == VehicleType.bike) {
            extraFee += calculateAirTemperatureExtraFee(temperature);
            if (vehicleType == VehicleType.bike) {
                extraFee += calculateWindSpeedExtraFee(windSpeed);
            }
            extraFee += calculateWeatherPhenomenonExtraFee(weatherPhenomenon);
        }

        return extraFee;
    }

    private static double calculateAirTemperatureExtraFee(double temperature) {
        if (temperature < -10) {
            return 1.0;
        } else if (temperature >= -10 && temperature < 0) {
            return 0.5;
        }
        return 0.0;
    }

    private static double calculateWindSpeedExtraFee(double windSpeed) {
        if (windSpeed >= 10 && windSpeed <= 20) {
            return 0.5;
        } else if (windSpeed > 20) {
            // Handle wind speed greater than 20 m/s error
            throw new IllegalArgumentException("Usage of selected vehicle type is forbidden due to high wind speed");
        }
        return 0.0;
    }

    private static double calculateWeatherPhenomenonExtraFee(String weatherPhenomenon) {
        // Handle forbidden weather phenomenon error
        return switch (weatherPhenomenon) {
            case "Light snow shower", "Moderate snow shower", "Heavy snow shower", "Light sleet", "Moderate sleet",
                 "Light snowfall", "Moderate snowfall" , "Heavy snowfall", "Blowing snow", "Drifting snow" -> 1.0;
            case "Light shower", "Moderate shower", "Heavy shower", "Light rain", "Moderate rain", "Heavy rain" -> 0.5;
            case "Glaze", "Hail", "Thunder", "Thunderstorm" ->
                    throw new IllegalArgumentException("Usage of selected vehicle type is forbidden due to weather phenomenon");
            default -> 0.0;
        };
    }
}

