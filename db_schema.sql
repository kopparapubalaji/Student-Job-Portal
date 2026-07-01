-- MySQL Schema for Job Portal
CREATE DATABASE IF NOT EXISTS jobportal_db;
USE jobportal_db;

-- The tables will be automatically created by Spring Data JPA due to ddl-auto=update
-- However, if you want to create them manually or for reference:

-- Table for Users
-- CREATE TABLE users (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     name VARCHAR(255),
--     email VARCHAR(255) UNIQUE,
--     password VARCHAR(255),
--     role VARCHAR(50),
--     profile_description TEXT,
--     resume_path VARCHAR(255)
-- );

-- Table for Jobs
-- CREATE TABLE jobs (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     title VARCHAR(255),
--     description TEXT,
--     skills_required VARCHAR(255),
--     salary VARCHAR(255),
--     location VARCHAR(255),
--     category VARCHAR(255),
--     employer_id BIGINT,
--     posted_date DATETIME,
--     FOREIGN KEY (employer_id) REFERENCES users(id)
-- );

-- Table for Applications
-- CREATE TABLE applications (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     application_status VARCHAR(50),
--     student_id BIGINT,
--     job_id BIGINT,
--     applied_date DATETIME,
--     FOREIGN KEY (student_id) REFERENCES users(id),
--     FOREIGN KEY (job_id) REFERENCES jobs(id)
-- );
