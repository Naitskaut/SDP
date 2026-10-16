package AbstractFactory;

public class PromoBodyFormatter implements MessageBodyFormatter {
    //Concrete Products
    @Override
    public String format(String message) {
        return "🎉 " + message + " 🔥 Don't miss out!";
    }
}