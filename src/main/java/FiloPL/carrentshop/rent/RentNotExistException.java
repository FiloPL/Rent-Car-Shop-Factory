package FiloPL.carrentshop.rent;

public class RentNotExistException extends RuntimeException {
    public RentNotExistException(String message) {
        super(message);
    }
}
