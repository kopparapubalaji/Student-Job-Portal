package com.student.jobportal.service;

import com.student.jobportal.entity.Job;
import com.student.jobportal.entity.User;
import com.student.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job postJob(Job job, User employer) {
        job.setPostedByEmployer(employer);
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> getJobsByEmployer(User employer) {
        return jobRepository.findByPostedByEmployer(employer);
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Autowired
    private com.student.jobportal.repository.ApplicationRepository applicationRepository;

    @org.springframework.transaction.annotation.Transactional
    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null) {
            applicationRepository.deleteByJob(job);
            jobRepository.deleteById(id);
        }
    }

    public List<Job> searchJobs(String query) {
        if (query == null || query.isEmpty()) {
            return jobRepository.findAll();
        }
        return jobRepository.findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCaseOrCategoryContainingIgnoreCase(query, query, query);
    }

    public int calculateMatchScore(String studentSkills, String jobSkills) {
        if (studentSkills == null || studentSkills.isEmpty() || jobSkills == null || jobSkills.isEmpty()) {
            return 0;
        }

        String[] sSkills = studentSkills.split(",");
        String[] jSkills = jobSkills.split(",");

        int matchCount = 0;
        for (String js : jSkills) {
            String trimmedJs = js.trim().toLowerCase();
            for (String ss : sSkills) {
                if (trimmedJs.equals(ss.trim().toLowerCase())) {
                    matchCount++;
                    break;
                }
            }
        }

        if (jSkills.length == 0) return 0;
        return (int) (((double) matchCount / jSkills.length) * 100);
    }
}
