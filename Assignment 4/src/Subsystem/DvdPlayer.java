package Subsystem;

public class DvdPlayer {

    private boolean on;
    private String movie;
    private boolean playing;

    public void on(){
        this.on = true;
        System.out.println("DVD Player on");
    }

    public void off(){
        stop();
        this.on = false;
        System.out.println("DVD Player off");
    }

    public void play(String movie){
        if (!on){
            throw new IllegalStateException("DVD Player is off");
        }
        if (movie == null || movie.isBlank()){
            throw new IllegalArgumentException("Movie title must not be empty");
        }
        this.movie = movie;
        this.playing = true;
        System.out.println("DVD Player playing \"" + movie + "\"");
    }

    public void stop(){
        if (!playing){
            return;
        }
        this.playing = false;
        System.out.println("DVD Player stopped \"" + movie + "\"");
    }

    public void eject(){
        if (movie == null){
            return;
        }
        System.out.println("DVD Player ejected \"" + movie + "\"");
        this.movie = null;
    }

    public boolean isOn(){
        return on;
    }
}