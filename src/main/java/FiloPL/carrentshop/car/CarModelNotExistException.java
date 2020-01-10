package FiloPL.carrentshop.car;

public class CarModelNotExistException extends RuntimeException{
    public CarModelNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
