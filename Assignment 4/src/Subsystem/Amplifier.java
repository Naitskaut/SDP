package Subsystem;


public class Amplifier {

    private DvdPlayer dvd;
    private boolean on;
    private int volume;

    public void on() {
        this.on = true;
        System.out.println("Amplifier on");
    }

    public void off() {
        this.on = false;
        this.dvd = null;
        System.out.println("Amplifier off");
    }

    public void setVolume(int volume) {
        if (!on) {
            throw new IllegalStateException("Amplifier is off");
        }
        if (volume < 0 || volume > 11) {
            throw new IllegalArgumentException("Volume must be from 0 to 11, got " + volume);
        }
        this.volume = volume;
        System.out.println("Amplifier volume set to " + volume);
    }

    public void setDvd(DvdPlayer dvd) {
        if (!on) {
            throw new IllegalStateException("Amplifier is off");
        }
        if (!dvd.isOn()) {
            throw new IllegalStateException("Amplifier can't take input from DVD Player, it is off");
        }
        this.dvd = dvd;
        System.out.println("Amplifier input set to DVD Player");
    }
}
