# 🎓 Student Job Portal

A modern **Full Stack Student Job Portal** built using **Java, Spring Boot, Spring Security, Thymeleaf, Hibernate and MySQL**. The application connects students with recruiters through a secure, role-based platform for job postings and applications.

---

# 📑 Table of Contents

- ✨ Features
- 🛠 Technologies Used
- 📂 Project Structure
- ⚙️ Installation & Setup
- 👥 User Roles
- 🔐 Security Features
- 🗄 Database
- 📸 Screenshots
- 🚀 Future Enhancements
- 📜 License

---

# ✨ Features

## 👨‍🎓 Student

- Student Registration & Login
- View Available Jobs
- Apply for Jobs
- Upload Resume
- Track Applied Jobs
- Update Profile

## 🏢 Recruiter

- Recruiter Registration & Login
- Post New Jobs
- Edit & Delete Job Posts
- View Applicants
- Manage Job Listings

## 👨‍💼 Admin

- Manage Students
- Manage Recruiters
- Manage Job Listings
- Monitor Platform Activity

---

# 🛠 Technologies Used

## Backend

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate

## Frontend

- Thymeleaf
- HTML5
- CSS3
- Bootstrap
- JavaScript

## Database

- MySQL

## Build Tool

- Maven

---

# 📂 Project Structure

```text
Student-Job-Portal
│
├── .github
├── .mvn
├── src
│   ├── main
│   └── test
│
├── uploads
├── db_schema.sql
├── pom.xml
├── mvnw
├── mvnw.cmd
├── README.md
└── LICENSE
```

---

# ⚙️ Installation & Setup

## Prerequisites

- Java 17+
- Maven
- MySQL
- Eclipse / IntelliJ IDEA / VS Code

---

## 1️⃣ Clone Repository

```bash
git clone https://github.com/kopparapubalaji/Student-Job-Portal.git
```

---

## 2️⃣ Configure Database

Create a MySQL database.

```sql
CREATE DATABASE student_job_portal;
```

Import

```
db_schema.sql
```

---

## 3️⃣ Configure Application

Update

```properties
application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_job_portal
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

---

## 4️⃣ Run Project

```bash
mvn spring-boot:run
```

---

# 👥 User Roles

| Role | Permissions |
|------|-------------|
| Student | Register, Login, Apply Jobs, Upload Resume |
| Recruiter | Post Jobs, Manage Jobs, View Applicants |
| Admin | Manage Entire Platform |

---

# 🔐 Security Features

- Spring Security Authentication
- Role-Based Authorization
- Password Encryption
- Secure Login System
- Session Management

---

# 🗄 Database

Database includes tables for:

- Students
- Recruiters
- Jobs
- Applications
- Resumes

---

# 📸 Screenshots


## 🏠 Home Page

<p align="center">
<img src="homePage.png" width="100%">
</p>

---

## 🔐 Login Page

<p align="center">
<img src="loginPage.png" width="100%">
</p>

---

## 👨‍🎓 Student Dashboard

<p align="center">
<img src="dashBoard.png" width="100%">
</p>

---

## 💼 Recruiter Dashboard

<p align="center">
<img src="employerPage.png" width="100%">
</p>

---

## 📋 Job Listings

<p align="center">
<img src="jobSearch.png" width="100%">
</p>

---

## 👨‍💼 Admin Dashboard

<p align="center">
<img src="Admin page.png" width="100%">
</p>
Example:

- Login Page
- Student Dashboard
- Recruiter Dashboard
- Job Listings
- Admin Dashboard

---

# 🚀 Future Enhancements

- Email Notifications
- Interview Scheduling
- Resume Builder
- Company Dashboard
- AI Resume Matching
- Job Recommendations
- Dark Mode
- REST API Integration

---

# 🤝 Contributing

Contributions are welcome.

1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Push the branch.
5. Open a Pull Request.

---

# 📜 License

This project is licensed under the **MIT License**.

---

<p align="center">

⭐ If you like this project, consider giving it a Star!

</p>
