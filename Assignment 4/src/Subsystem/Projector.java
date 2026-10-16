package Subsystem;

public class Projector {
    private final int lampHoursLeft;
    private boolean on;
    private DvdPlayer input;

    public Projector() {
        this(2000);
    }

    public Projector(int lampHoursLeft) {
        this.lampHoursLeft = lampHoursLeft;
    }

    public void on() {
        if (lampHoursLeft <= 0) {
            throw new IllegalStateException("Projector lamp is burned out");
        }
        this.on = true;
        System.out.println("Projector on");

    }

    public void off() {
        if (!on){
            return;
        }
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