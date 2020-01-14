package FiloPL.carrentshop.rent_point;

public class RentPointNotExistException extends RuntimeException{
    public RentPointNotExistException(String errorMessage){
        super(errorMessage);
    }
}
