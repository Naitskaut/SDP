package Subsystem;

public class TheaterLights {

    public void on(){
        System.out.println("Theater Lights on");
    }

    public void dim(int level){
        if (level < 0 || level > 100){
            throw new IllegalArgumentException("Light level must be from 0 to 100, got " + level);
        }
        System.out.println("Theater Lights dimming to " + level + "%");
    }
}