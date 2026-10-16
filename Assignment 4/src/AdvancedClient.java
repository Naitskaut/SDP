import Facade.HomeTheaterException;
import Facade.HomeTheaterFacade;
import Subsystem.Amplifier;
import Subsystem.DvdPlayer;
import Subsystem.PopcornPopper;
import Subsystem.Projector;
import Subsystem.Screen;
import Subsystem.TheaterLights;

//Advanced client - devices are still public and can be used without the facade
public class AdvancedClient {
    public static void main(String[] args) {
        System.out.println("=== Using devices without the facade ===");
        Amplifier amp = new Amplifier();
        DvdPlayer dvd = new DvdPlayer();
        dvd.on();
        amp.on();
        amp.setDvd(dvd);
        amp.setVolume(11);   //facade always uses 5
        amp.off();
        dvd.off();

        System.out.println("\n=== Projector is broken ===");
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, dvd, new Projector(0),
                new Screen(), new TheaterLights(), new PopcornPopper());
        try {
            homeTheater.watchMovie("Dune");
        } catch (HomeTheaterException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}