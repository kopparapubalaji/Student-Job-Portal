package com.student.jobportal.service;

import com.student.jobportal.entity.User;
import com.student.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Autowired
    private com.student.jobportal.repository.ApplicationRepository applicationRepository;
    
    @Autowired
    private com.student.jobportal.repository.NotificationRepository notificationRepository;
    
    @Autowired
    private com.student.jobportal.repository.JobRepository jobRepository;

    @org.springframework.transaction.annotation.Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            notificationRepository.deleteByUserId(user.getId());
            applicationRepository.deleteByStudent(user);
            
            if ("ROLE_EMPLOYER".equals(user.getRole())) {
                List<com.student.jobportal.entity.Job> jobs = jobRepository.findByPostedByEmployer(user);
                for (com.student.jobportal.entity.Job job : jobs) {
                    applicationRepository.deleteByJob(job);
                    jobRepository.deleteById(job.getId());
                }
            }
            userRepository.deleteById(id);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
        );
    }
    
    public User updateProfile(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setProfileDescription(user.getProfileDescription());
            existingUser.setSkills(user.getSkills());
            if (user.getResumePath() != null) {
                existingUser.setResumePath(user.getResumePath());
            }
            return userRepository.save(existingUser);
        }
        return null;
    }
}
