import Requests.AttendanceRequest;
import Tools.CommandHandler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.exec();
    }
}
