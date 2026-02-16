# Employee Attendance Management System

This project is a full-stack Employee Attendance Management System developed using Java Spring Boot and MySQL. It helps organizations manage employee attendance, check-in, check-out, and working hours efficiently through a simple dashboard.

## Features
- Add employees and manager
- Employee check-in and check-out
- Automatic working hours calculation
- View present and absent employees
- Dashboard showing total employees, present today, absent today
- Monthly attendance and working hours report
- REST API based system with MySQL database

## Technologies Used
- Java (Spring Boot)
- MySQL
- REST API
- HTML/CSS
- Maven
- GitHub

## How to Run
1. Create MySQL database (attendance_db)
2. Update database username and password in application.properties
3. Run project using:
   mvnw spring-boot:run
4. Open browser:
   http://localhost:8080

## API Examples
- Add Employee: POST /api/users/add  
- Check In: POST /api/attendance/checkin?userId=1  
- Check Out: POST /api/attendance/checkout?userId=1  
- Dashboard: GET /api/attendance/dashboard  

## Developed By
Akalya K  
Final Year B.Tech IT (2026)
VelTech MultiTech college,Avadi

This project is created for learning full stack Java development and placement preparation.
