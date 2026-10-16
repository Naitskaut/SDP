import Facade.HomeTheaterFacade;
import Facade.HomeTheaterInstaller;

//Client - uses only the facade, never creates or calls a device directly
public class Main {
    public static void main(String[] args) {
        HomeTheaterInstaller installer = new HomeTheaterInstaller();
        HomeTheaterFacade homeTheater = installer.homeTheater();

        homeTheater.watchMovie("Interstellar");
        System.out.println();
        homeTheater.endMovie();
    }
}