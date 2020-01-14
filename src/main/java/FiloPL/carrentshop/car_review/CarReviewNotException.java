package FiloPL.carrentshop.car_review;

public class CarReviewNotException extends RuntimeException{
    public CarReviewNotException(String errorMessage) {
        super(errorMessage);
    }
}

