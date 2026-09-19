package carbuilder;

public class CarPartsBuilder implements CarBuilder{
    private CarType type;
    private Engine engine;
    private Transmission transmission;
    private int seats;

    public CarPartsBuilder(){
        reset();
    }

    @Override
    public CarPartsBuilder reset(){
        this.type = null;
        this.engine = null;
        this.transmission=null;
        this.seats = 0;
        return this;
    }

    @Override
    public CarPartsBuilder setType(CarType type){
        this.type = type;
        return this;
    }
    @Override
    public CarPartsBuilder setEngine(Engine engine){
        this.engine = engine;
        return this;
    }
    @Override
    public CarPartsBuilder setTransmission(Transmission transmission){
        this.transmission = transmission;
        return this;
    }
    @Override
    public CarPartsBuilder setSeats(int seats){
        this.seats = seats;
        return this;
    }
    public Car getResult(){
        validate();
        return new Car(type,engine,transmission,seats);
    }
    private void validate(){
        if (type == null) {
            throw new IllegalStateException("Car type must be set before building a car");
        }
        if (engine == null) {
            throw new IllegalStateException("Engine must be set before building a car");
        }
        if (transmission == null) {
            throw new IllegalStateException("Transmission must be set before building a car");
        }
        if (seats < CarSeats.MIN_SEATS || seats > CarSeats.MAX_SEATS) {
            throw new IllegalStateException("Seats must be between %d and %d, but was %d".formatted(CarSeats.MIN_SEATS, CarSeats.MAX_SEATS, seats));
        }
    }

}
