import Facade.HomeTheaterFacade;
import Subsystem.Amplifier;
import Subsystem.DvdPlayer;
import Subsystem.PopcornPopper;
import Subsystem.Projector;
import Subsystem.Screen;
import Subsystem.TheaterLights;

public class Main {
    public static void main(String[] args) {
        Amplifier amp = new Amplifier();
        DvdPlayer dvd = new DvdPlayer();
        Projector projector = new Projector();
        Screen screen = new Screen();
        TheaterLights lights = new TheaterLights();
        PopcornPopper popper = new PopcornPopper();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, dvd, projector, screen, lights, popper);

        homeTheater.watchMovie("Interstellar");
        System.out.println();
        homeTheater.endMovie();
    }
}