package com.attendance.attendance_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.attendance_system.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
