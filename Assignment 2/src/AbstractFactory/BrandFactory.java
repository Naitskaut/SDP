package AbstractFactory;

public interface BrandFactory {
    //Abstract Factory
    MessageHeader createHeader();
    MessageBodyFormatter createBody();
    ActionButton createButton();
}
