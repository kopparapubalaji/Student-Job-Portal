package com.student.jobportal.service;

import com.student.jobportal.entity.Application;
import com.student.jobportal.entity.Job;
import com.student.jobportal.entity.User;
import com.student.jobportal.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private NotificationService notificationService;

    public Application applyForJob(User student, Job job) {
        if (applicationRepository.existsByStudentAndJob(student, job)) {
            return null; // Already applied
        }
        Application application = new Application();
        application.setStudent(student);
        application.setJob(job);
        application.setApplicationStatus("APPLIED");
        
        Application savedApplication = applicationRepository.save(application);
        
        // Notification for student
        notificationService.createNotification(student.getId(), "You successfully applied for " + job.getTitle());
        
        // Notification for employer
        notificationService.createNotification(job.getPostedByEmployer().getId(), "Student " + student.getName() + " applied for " + job.getTitle());
        
        // Send email to student
        String studentSubject = "Job Application Successful";
        String studentMessage = "You have successfully applied for " + job.getTitle() + ".";
        emailService.sendEmail(student.getEmail(), studentSubject, studentMessage);
        
        // Send email to employer
        String employerSubject = "New Application Received";
        String employerMessage = "Student " + student.getName() + " has applied for your job: " + job.getTitle() + ".";
        emailService.sendEmail(job.getPostedByEmployer().getEmail(), employerSubject, employerMessage);
        
        return savedApplication;
    }

    public List<Application> getApplicationsByStudent(User student) {
        return applicationRepository.findByStudent(student);
    }

    public List<Application> getApplicationsByJob(Job job) {
        return applicationRepository.findByJob(job);
    }

    public Application updateStatus(Long id, String status) {
        Application application = applicationRepository.findById(id).orElse(null);
        if (application != null) {
            application.setApplicationStatus(status);
            Application saved = applicationRepository.save(application);
            
            // Notify student about status change
            notificationService.createNotification(saved.getStudent().getId(), 
                "Your application for " + saved.getJob().getTitle() + " has been marked as: " + status);
                
            return saved;
        }
        return null;
    }
}
