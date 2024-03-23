package spring.course.application.deliveryfee;

public class RegionalBaseFeeCalculator { //Calculates regional base fee
    public static double calculateRegionalBaseFee(String city, VehicleType vehicleType) {
        return switch (city.toLowerCase()) {
            case "tallinn" -> calculateTallinnRegionalBaseFee(vehicleType);
            case "tartu" -> calculateTartuRegionalBaseFee(vehicleType);
            case "pärnu" -> calculateParnuRegionalBaseFee(vehicleType);
            default -> 0.0;
        };
    }

    private static double calculateTallinnRegionalBaseFee(VehicleType vehicleType) {
        return switch (vehicleType) {
            case car -> 4.0;
            case scooter -> 3.5;
            case bike -> 3.0;
            default -> 0.0;
        };
    }
    private static double calculateTartuRegionalBaseFee(VehicleType vehicleType) {
        return switch (vehicleType) {
            case car -> 3.5;
            case scooter -> 3.0;
            case bike -> 2.5;
            default -> 0.0;
        };
    }
    private static double calculateParnuRegionalBaseFee(VehicleType vehicleType) {
        return switch (vehicleType) {
            case car -> 3.0;
            case scooter -> 2.5;
            case bike -> 2.0;
            default -> 0.0;
        };
    }
}

