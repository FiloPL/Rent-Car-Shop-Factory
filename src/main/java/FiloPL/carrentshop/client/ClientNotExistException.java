package FiloPL.carrentshop.client;

public class ClientNotExistException extends RuntimeException{
    public ClientNotExistException(String errorMessage){
        super(errorMessage);
    }
}
