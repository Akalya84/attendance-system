package com.attendance.attendance_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.attendance_system.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByUserId(Long userId);
}