package Subsystem;

public class Projector {

    private boolean on;
    private DvdPlayer input;

    public void on() {
        this.on = true;
        System.out.println("Projector on");
    }

    public void off() {
        this.on = false;
        this.input = null;
        System.out.println("Projector off");
    }

    public void setInput(DvdPlayer dvd) {
        if (!on) {
            throw new IllegalStateException("Projector is off");
        }
        if (!dvd.isOn()) {
            throw new IllegalStateException("Projector can't show DVD Player, it is off");
        }
        this.input = dvd;
        System.out.println("Projector input set to DVD Player");
    }

    public void wideScreenMode() {
        if (!on) {
            throw new IllegalStateException("Projector is off");
        }
        System.out.println("Projector in widescreen mode (16:9)");
    }
}