package FiloPL.carrentshop.promotion;

public class PromotionNotExistException  extends RuntimeException{

    public PromotionNotExistException(String message){
        super(message);
    }
}
