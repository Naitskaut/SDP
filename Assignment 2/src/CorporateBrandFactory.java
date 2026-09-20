public class CorporateBrandFactory implements BrandFactory{
    //Concrete Factories
    @Override
    public MessageHeader createHeader(){
        return new CorporateHeader();
    }
    @Override
    public MessageBodyFormatter createBody(){
        return new CorporateBodyFormatter();
    }
    @Override
    public ActionButton createButton(){
        return new CorporateActionButton();
    }
}
