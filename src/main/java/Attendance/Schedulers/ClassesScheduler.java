package Attendance.Schedulers;

import Attendance.Attendance;
import Requests.AttendanceRequest;

public class ClassesScheduler implements Runnable{
    public void run() {
        Attendance.getInstance().setClasses(
                AttendanceRequest.getClasses().getClasses());
    }
}
