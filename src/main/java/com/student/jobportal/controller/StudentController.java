package com.student.jobportal.controller;

import com.student.jobportal.entity.Job;
import com.student.jobportal.entity.User;
import com.student.jobportal.service.ApplicationService;
import com.student.jobportal.service.JobService;
import com.student.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationService applicationService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private com.student.jobportal.service.NotificationService notificationService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        java.util.List<Job> jobs = jobService.getAllJobs();
        
        java.util.Map<Long, Integer> matchScores = new java.util.HashMap<>();
        for (Job job : jobs) {
            matchScores.put(job.getId(), jobService.calculateMatchScore(user.getSkills(), job.getSkillsRequired()));
        }
        
        model.addAttribute("jobs", jobs);
        model.addAttribute("matchScores", matchScores);
        model.addAttribute("applications", applicationService.getApplicationsByStudent(user));
        model.addAttribute("notifications", notificationService.getUserNotifications(user.getId()));
        return "student/dashboard";
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("user", user);
        return "student/profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute User user, @RequestParam("resume") MultipartFile resume, @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        User currentUser = userService.findByEmail(userDetails.getUsername());
        user.setId(currentUser.getId());

        if (!resume.isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            String fileName = UUID.randomUUID().toString() + "_" + resume.getOriginalFilename();
            Path path = Paths.get(uploadPath + fileName);
            Files.write(path, resume.getBytes());
            user.setResumePath(fileName);
        }

        userService.updateProfile(user);
        return "redirect:/student/profile?success";
    }

    @GetMapping("/jobs/search")
    public String searchJobs(@RequestParam("query") String query, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        java.util.List<Job> jobs = jobService.searchJobs(query);
        
        java.util.Map<Long, Integer> matchScores = new java.util.HashMap<>();
        for (Job job : jobs) {
            matchScores.put(job.getId(), jobService.calculateMatchScore(user.getSkills(), job.getSkillsRequired()));
        }
        
        model.addAttribute("jobs", jobs);
        model.addAttribute("matchScores", matchScores);
        model.addAttribute("applications", applicationService.getApplicationsByStudent(user));
        model.addAttribute("notifications", notificationService.getUserNotifications(user.getId()));
        return "student/dashboard";
    }

    @PostMapping("/jobs/apply/{id}")
    public String applyJob(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        Job job = jobService.getJobById(id);
        applicationService.applyForJob(user, job);
        return "redirect:/student/dashboard?applied";
    }
}
