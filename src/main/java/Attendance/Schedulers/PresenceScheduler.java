package Attendance.Schedulers;

import Attendance.Attendance;
import Attendance.Exceptions.CanNotCompleteAttendance;
import Requests.AttendanceRequest;

import java.util.HashSet;
import java.util.Iterator;

public class PresenceScheduler implements Runnable{
    public void run() {
        HashSet<String> dataToSend = Attendance.getInstance().getDataToSend();
        for (String data : dataToSend) {
            if (AttendanceRequest.setPresence(data)) {
                dataToSend.remove(data);
            }
        }
        Attendance.getInstance().setDataToSend(dataToSend);
    }
}
