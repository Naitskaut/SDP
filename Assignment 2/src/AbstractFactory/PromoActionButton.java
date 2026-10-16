package AbstractFactory;

public class PromoActionButton implements ActionButton {
    //Concrete Products
    @Override
    public String getLabel(){
        return "🔥 Shop Now!(link)";
    }
}
