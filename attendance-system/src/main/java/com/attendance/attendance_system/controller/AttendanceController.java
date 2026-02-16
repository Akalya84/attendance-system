package com.attendance.attendance_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.attendance_system.model.Attendance;
import com.attendance.attendance_system.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin("*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // CHECK IN
    @PostMapping("/checkin")
    public String checkIn(@RequestParam Long userId){
        return attendanceService.checkIn(userId);
    }

    // CHECK OUT
    @PostMapping("/checkout")
    public String checkOut(@RequestParam Long userId){
        return attendanceService.checkOut(userId);
    }

    // VIEW MY ATTENDANCE
    @GetMapping("/my")
    public List<Attendance> myAttendance(@RequestParam Long userId){
        return attendanceService.getMyAttendance(userId);
    }

    // MANAGER VIEW ALL
    @GetMapping("/all")
    public List<Attendance> allAttendance(){
        return attendanceService.getAllAttendance();
    }

    // ⭐ MONTHLY REPORT
    @GetMapping("/report/{userId}")
    public String getReport(@PathVariable Long userId){
        return attendanceService.getMonthlyReport(userId);
    }

    // ⭐ DASHBOARD
    @GetMapping("/dashboard")
    public String dashboard(){
        return attendanceService.getDashboard();
    }
}
