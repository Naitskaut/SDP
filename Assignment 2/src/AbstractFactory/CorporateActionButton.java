package AbstractFactory;

public class CorporateActionButton implements ActionButton {
    //Concrete Products
    @Override
    public String getLabel(){
        return "Learn More";
    }
}
