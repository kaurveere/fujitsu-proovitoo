package spring.course.application.deliveryfee;

import static spring.course.application.deliveryfee.ExtraFeeCalculator.calculateExtraFee;
import static spring.course.application.deliveryfee.RegionalBaseFeeCalculator.calculateRegionalBaseFee;

public class DeliveryFeeCalculator {
    //Orchestrates the calculation process
    public static double deliveryFee(DeliveryFeeRequest dfr, VehicleType vehicle){
        double sum = 0; //Stores the delivery fee
        String city = dfr.getName();
        String phenomenon = dfr.getPhenomenon();
        double windSpeed = dfr.getWindspeed();
        double temperature = dfr.getTemperature();

        sum += calculateExtraFee(temperature, windSpeed, phenomenon, vehicle);
        sum += calculateRegionalBaseFee(city, vehicle);

        return sum;
    }

}
