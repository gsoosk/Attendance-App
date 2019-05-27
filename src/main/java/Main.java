import Requests.AttendanceRequest;
import Tools.CommandHandler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.exec();
        System.exit(0);
    }
}
