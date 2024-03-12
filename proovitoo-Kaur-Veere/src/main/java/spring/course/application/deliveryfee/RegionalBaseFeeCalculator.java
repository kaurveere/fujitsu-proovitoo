package spring.course.application.deliveryfee;

public class RegionalBaseFeeCalculator { //Calculates regional base fee
    public double calculateRegionalBaseFee(String city, VehicleType vehicleType) {
        return switch (city) {
            case "Tallinn" -> calculateTallinnRegionalBaseFee(vehicleType);
            case "Tartu" -> calculateTartuRegionalBaseFee(vehicleType);
            case "Pärnu" -> calculateParnuRegionalBaseFee(vehicleType);
            default -> 0.0;
        };
    }

    private double calculateTallinnRegionalBaseFee(VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> 4.0;
            case SCOOTER -> 3.5;
            case BIKE -> 3.0;
            default -> 0.0;
        };
    }
    private double calculateTartuRegionalBaseFee(VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> 3.5;
            case SCOOTER -> 3.0;
            case BIKE -> 2.5;
            default -> 0.0;
        };
    }
    private double calculateParnuRegionalBaseFee(VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> 3.0;
            case SCOOTER -> 2.5;
            case BIKE -> 2.0;
            default -> 0.0;
        };
    }
}

