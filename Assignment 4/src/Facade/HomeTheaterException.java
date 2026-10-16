package Facade;

public class HomeTheaterException extends RuntimeException {
    //the only error the facade throws, so client doesn't need to know device errors
    public HomeTheaterException(String message, Throwable cause){
        super(message, cause);
    }
}