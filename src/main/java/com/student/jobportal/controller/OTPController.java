package com.student.jobportal.controller;

import com.student.jobportal.entity.User;
import com.student.jobportal.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class OTPController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/verify-otp")
    public String verifyOtpPage(HttpSession session, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/login"; // Must login first
        }
        Boolean otpVerified = (Boolean) session.getAttribute("otp_verified");
        if (otpVerified != null && otpVerified) {
            return "redirect:/"; // Already verified
        }
        return "verify-otp";
    }

    @PostMapping("/verify-otp")
    public String verifyOtpSubmit(@RequestParam String otp, HttpSession session, Authentication authentication, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElse(null);
        
        if (user != null && user.getOtp() != null && user.getOtp().equals(otp)) {
            if (user.getOtpExpiry().isAfter(LocalDateTime.now())) {
                user.setOtpVerified(true);
                user.setOtp(null); // Clear OTP after use
                userRepository.save(user);
                
                session.setAttribute("otp_verified", true);
                
                // Redirect based on role
                String role = authentication.getAuthorities().iterator().next().getAuthority();
                if (role.equals("ROLE_STUDENT")) {
                    return "redirect:/student/dashboard";
                } else if (role.equals("ROLE_EMPLOYER")) {
                    return "redirect:/employer/dashboard";
                } else if (role.equals("ROLE_ADMIN")) {
                    return "redirect:/admin/dashboard";
                }
                return "redirect:/";
            } else {
                model.addAttribute("error", "OTP has expired. Please log in again.");
                return "verify-otp";
            }
        }
        
        model.addAttribute("error", "Invalid OTP.");
        return "verify-otp";
    }
}
