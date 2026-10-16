package AbstractFactory;

public class CorporateBodyFormatter implements MessageBodyFormatter {
    @Override
    public String format(String message){
        return message + "\n\nBest regards,\nThe Team";
    }
}
