package Data;

import java.util.ArrayList;
import java.util.Date;

public class Attendance {
    private int status;
    private String date;
    private ArrayList <UTClass> classes;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<UTClass> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<UTClass> classes) {
        this.classes = classes;
    }
}
