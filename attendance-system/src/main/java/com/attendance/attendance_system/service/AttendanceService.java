package com.attendance.attendance_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.attendance_system.model.Attendance;
import com.attendance.attendance_system.model.User;
import com.attendance.attendance_system.repository.AttendanceRepository;
import com.attendance.attendance_system.repository.UserRepository;

import java.time.*;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository;

    // ================= CHECK IN =================
    public String checkIn(Long userId) {

        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) return "User not found";

        Attendance attendance = new Attendance();
        attendance.setUser(user.get());
        attendance.setDate(LocalDate.now());
        attendance.setCheckIn(LocalTime.now());
        attendance.setStatus("Present");
        attendance.setTotalHours(0);

        attendanceRepository.save(attendance);

        return "Checked In Successfully";
    }

    // ================= CHECK OUT =================
    public String checkOut(Long userId) {

        List<Attendance> list = attendanceRepository.findByUserId(userId);
        if(list.isEmpty()) return "No check-in found";

        Attendance attendance = list.get(list.size()-1);

        attendance.setCheckOut(LocalTime.now());

        double hours = Duration.between(
                attendance.getCheckIn(),
                attendance.getCheckOut()).toMinutes() / 60.0;

        attendance.setTotalHours(hours);

        attendanceRepository.save(attendance);

        return "Checked Out Successfully";
    }

    // ================= MY ATTENDANCE =================
    public List<Attendance> getMyAttendance(Long userId){
        return attendanceRepository.findByUserId(userId);
    }

    // ================= ALL ATTENDANCE =================
    public List<Attendance> getAllAttendance(){
        return attendanceRepository.findAll();
    }

    // ================= MONTHLY REPORT =================
    public String getMonthlyReport(Long userId){

        List<Attendance> list = attendanceRepository.findByUserId(userId);

        int totalDays = 0;
        double totalHours = 0;

        for(Attendance a : list){
            if(a.getTotalHours() > 0){
                totalDays++;
                totalHours += a.getTotalHours();
            }
        }

        return "EMPLOYEE REPORT\n"
                +"Employee ID: " + userId
                +"\nTotal Days Present: " + totalDays
                +"\nTotal Working Hours: " + totalHours;
    }

    // ================= 🔥 SMART DASHBOARD =================
   // ================= DASHBOARD =================
public String getDashboard(){

    List<User> allUsers = userRepository.findAll();
    List<Attendance> allAttendance = attendanceRepository.findAll();

    LocalDate today = LocalDate.now();

    String presentNames = "";
    String absentNames = "";

    int presentCount = 0;
    double todayHours = 0;

    for(User u : allUsers){

        boolean isPresent = false;

        for(Attendance a : allAttendance){
            if(a.getUser().getId().equals(u.getId())
                    && a.getDate()!=null
                    && a.getDate().equals(today)){

                isPresent = true;
                todayHours += a.getTotalHours();
                break;
            }
        }

        if(isPresent){
            presentCount++;
            presentNames += "➡ " + u.getName() + "\n";
        }else{
            absentNames += "➡ " + u.getName() + "\n";
        }
    }

    long totalEmployees = allUsers.size();
    long absentCount = totalEmployees - presentCount;

    return "🔥 COMPANY DASHBOARD 🔥\n\n"
            +"👥 TOTAL EMPLOYEES: "+ totalEmployees + "\n\n"
            +"🟢 PRESENT TODAY ("+presentCount+"):\n"
            + presentNames + "\n"
            +"🔴 ABSENT TODAY ("+absentCount+"):\n"
            + absentNames + "\n"
            +"⏱ TODAY WORKING HOURS: "+ todayHours;
}
}
