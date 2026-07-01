package com.student.jobportal.controller;

import com.student.jobportal.entity.Application;
import com.student.jobportal.entity.Job;
import com.student.jobportal.entity.User;
import com.student.jobportal.service.ApplicationService;
import com.student.jobportal.service.JobService;
import com.student.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employer")
public class EmployerController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private com.student.jobportal.service.NotificationService notificationService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User employer = userService.findByEmail(userDetails.getUsername());
        List<Job> myJobs = jobService.getJobsByEmployer(employer);
        model.addAttribute("jobs", myJobs);
        model.addAttribute("employer", employer);
        model.addAttribute("notifications", notificationService.getUserNotifications(employer.getId()));

        // Chart Data
        Map<String, Long> categoryCount = myJobs.stream()
                .collect(Collectors.groupingBy(Job::getCategory, Collectors.counting()));
        model.addAttribute("categoryLabels", categoryCount.keySet());
        model.addAttribute("categoryData", categoryCount.values());

        return "employer/dashboard";
    }

    @GetMapping("/jobs/new")
    public String newJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "employer/post-job";
    }

    @PostMapping("/jobs/post")
    public String postJob(@ModelAttribute Job job, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        jobService.postJob(job, user);
        return "redirect:/employer/dashboard?posted";
    }

    @GetMapping("/jobs/edit/{id}")
    public String editJobForm(@PathVariable Long id, Model model) {
        model.addAttribute("job", jobService.getJobById(id));
        return "employer/edit-job";
    }

    @PostMapping("/jobs/update/{id}")
    public String updateJob(@PathVariable Long id, @ModelAttribute Job job, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        job.setId(id);
        jobService.postJob(job, user);
        return "redirect:/employer/dashboard?updated";
    }

    @GetMapping("/jobs/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "redirect:/employer/dashboard?deleted";
    }

    @GetMapping("/jobs/applicants/{jobId}")
    public String viewApplicants(@PathVariable("jobId") Long jobId, Model model) {
        Job job = jobService.getJobById(jobId);
        if (job == null) {
            throw new RuntimeException("Job not found with ID: " + jobId);
        }
        
        List<Application> applications = applicationService.getApplicationsByJob(job);
        
        java.util.Map<Long, Integer> matchScores = new java.util.HashMap<>();
        for (Application app : applications) {
            String studentSkills = (app.getStudent() != null) ? app.getStudent().getSkills() : null;
            matchScores.put(app.getId(), jobService.calculateMatchScore(studentSkills, job.getSkillsRequired()));
        }
        
        model.addAttribute("job", job);
        model.addAttribute("applications", applications);
        model.addAttribute("matchScores", matchScores);
        return "employer/applicants";
    }

    @PostMapping("/applications/update-status")
    public String updateApplicationStatus(@RequestParam Long applicationId, @RequestParam String status, @RequestParam Long jobId) {
        applicationService.updateStatus(applicationId, status);
        return "redirect:/employer/jobs/applicants/" + jobId;
    }
}
