package FiloPL.carrentshop.car_model;

public class CarModelNotExistException extends RuntimeException{
    public CarModelNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
