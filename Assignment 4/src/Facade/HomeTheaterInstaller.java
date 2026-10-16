package Facade;

import Subsystem.Amplifier;
import Subsystem.DvdPlayer;
import Subsystem.PopcornPopper;
import Subsystem.Projector;
import Subsystem.Screen;
import Subsystem.TheaterLights;

public class HomeTheaterInstaller {
    //creates all devices once and gives the client a ready facade
    private final HomeTheaterFacade homeTheater;

    public HomeTheaterInstaller(){
        homeTheater = new HomeTheaterFacade(new Amplifier(), new DvdPlayer(), new Projector(),
                new Screen(), new TheaterLights(), new PopcornPopper());
    }

    public HomeTheaterFacade homeTheater(){
        return homeTheater;
    }
}