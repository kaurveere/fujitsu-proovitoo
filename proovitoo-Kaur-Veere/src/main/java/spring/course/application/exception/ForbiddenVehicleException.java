package spring.course.application.exception;

public class ForbiddenVehicleException extends Exception{
    public ForbiddenVehicleException(){
        super("Usage of selected vehicle type is forbidden");
    }
}
