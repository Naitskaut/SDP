public class CorporateHeader implements MessageHeader{
    //Concrete Products
    @Override
    public String render(){
        return "Dear Customer, ";
    }
}
