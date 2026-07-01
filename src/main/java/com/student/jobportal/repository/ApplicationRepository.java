package com.student.jobportal.repository;

import com.student.jobportal.entity.Application;
import com.student.jobportal.entity.Job;
import com.student.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStudent(User student);
    List<Application> findByJob(Job job);
    boolean existsByStudentAndJob(User student, Job job);
    
    @org.springframework.transaction.annotation.Transactional
    void deleteByJob(Job job);
    
    @org.springframework.transaction.annotation.Transactional
    void deleteByStudent(User student);
}
