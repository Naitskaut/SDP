package Facade;

import Subsystem.Amplifier;
import Subsystem.DvdPlayer;
import Subsystem.PopcornPopper;
import Subsystem.Projector;
import Subsystem.Screen;
import Subsystem.TheaterLights;

public class HomeTheaterFacade {
    //Facade - client calls watchMovie()/endMovie(), facade calls devices in the right order
    private static final int MOVIE_LIGHT_LEVEL = 10;
    private static final int MOVIE_VOLUME = 5;

    private final Amplifier amp;
    private final DvdPlayer dvd;
    private final Projector projector;
    private final Screen screen;
    private final TheaterLights lights;
    private final PopcornPopper popper;

    public HomeTheaterFacade(Amplifier amp, DvdPlayer dvd, Projector projector,
                             Screen screen, TheaterLights lights, PopcornPopper popper) {
        this.amp = amp;
        this.dvd = dvd;
        this.projector = projector;
        this.screen = screen;
        this.lights = lights;
        this.popper = popper;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch \"" + movie + "\"...");
        popper.on();
        popper.pop();
        lights.dim(MOVIE_LIGHT_LEVEL);
        screen.down();

        //dvd goes first, projector and amp don't accept a source that is off
        dvd.on();
        projector.on();
        projector.setInput(dvd);
        projector.wideScreenMode();

        amp.on();
        amp.setDvd(dvd);
        amp.setVolume(MOVIE_VOLUME);

        dvd.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        popper.off();
        lights.on();
        screen.up();
        projector.off();
        amp.off();
        dvd.stop();
        dvd.eject();
        dvd.off();
    }

}