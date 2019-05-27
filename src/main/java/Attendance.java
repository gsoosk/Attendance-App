import Data.UTClass;

import java.util.ArrayList;

public class Attendance {
    private static Attendance ourInstance = new Attendance();

    public static Attendance getInstance() {
        return ourInstance;
    }


    public ArrayList<UTClass> classes;
    private Attendance() {

    }
}
