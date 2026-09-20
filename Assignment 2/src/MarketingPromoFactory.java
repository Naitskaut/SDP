public class MarketingPromoFactory implements BrandFactory{
    //Concrete Factories
    @Override
    public MessageHeader createHeader(){
        return new PromoHeader();
    }
    @Override
    public MessageBodyFormatter createBody(){
        return new PromoBodyFormatter();
    }
    @Override
    public ActionButton createButton(){
        return new PromoActionButton();
    }
}
