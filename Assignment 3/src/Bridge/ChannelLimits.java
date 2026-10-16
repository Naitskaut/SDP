package Bridge;

final class ChannelLimits {
    //clean code, no magic numbers
    static final int SMS_MAX_LENGTH = 160;
    static final int PUSH_MAX_LENGTH = 40;

    private ChannelLimits(){

    }
    //makes one line from the message and cuts it if channel can't show all of it
    static String shortenToOneLine(String message, int maxLength){
        String line = message.replaceAll("\\s+", " ").trim();
        if (line.length() <= maxLength) {
            return line;
        }
        return line.substring(0, maxLength - 3) + "...";
    }
}
