package com.student.jobportal.repository;

import com.student.jobportal.entity.Job;
import com.student.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByPostedByEmployer(User employer);
    List<Job> findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCaseOrCategoryContainingIgnoreCase(String title, String location, String category);
}
