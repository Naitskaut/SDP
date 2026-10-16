package AbstractFactory;

public class Client {
    private final BrandFactory factory;

    public Client(BrandFactory factory) {
        this.factory = factory;
    }

    public String compose(String message) {
        MessageHeader header = factory.createHeader();
        MessageBodyFormatter formatter = factory.createBody();
        ActionButton button = factory.createButton();

        String headerText = header.render();
        String bodyText = formatter.format(message);
        String buttonText = button.getLabel();
        return headerText + "\n"+ bodyText + "\n"+ buttonText;
    }
}
