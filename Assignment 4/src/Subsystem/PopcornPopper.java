package Subsystem;

public class PopcornPopper {

    private boolean on;

    public void on() {
        this.on = true;
        System.out.println("Popcorn Popper on");
    }

    public void off() {
        if (!on) {
            return;
        }
        this.on = false;
        System.out.println("Popcorn Popper off");
    }

    public void pop() {
        if (!on) {
            throw new IllegalStateException("Popcorn Popper is off");
        }
        System.out.println("Popcorn Popper popping popcorn!");
    }
}