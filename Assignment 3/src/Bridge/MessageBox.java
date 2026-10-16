package Bridge;

final class MessageBox {
    //shared output for all channels, so the box is not copied into every Concrete Implementor
    private MessageBox(){

    }

    static void print(String channel, String recipient, String message){
        System.out.println("┌─ " + channel + " " + "─".repeat(30));
        System.out.println("│ To: " + recipient);

        String[] lines = message.split("\n");
        for (String line : lines) {
            System.out.println("│ " + line);
        }

        System.out.println("└" + "─".repeat(33));
        System.out.println();
    }
}
