package com.student.jobportal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    @Column(length = 1000)
    private String description;

    @NotBlank(message = "Skills are required")
    private String skillsRequired;

    @NotBlank(message = "Salary is required")
    private String salary;

    @NotBlank(message = "Location is required")
    private String location;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private User postedByEmployer;

    private LocalDateTime postedDate = LocalDateTime.now();
    
    private String category;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSkillsRequired() { return skillsRequired; }
    public void setSkillsRequired(String skillsRequired) { this.skillsRequired = skillsRequired; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public User getPostedByEmployer() { return postedByEmployer; }
    public void setPostedByEmployer(User postedByEmployer) { this.postedByEmployer = postedByEmployer; }

    public LocalDateTime getPostedDate() { return postedDate; }
    public void setPostedDate(LocalDateTime postedDate) { this.postedDate = postedDate; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
