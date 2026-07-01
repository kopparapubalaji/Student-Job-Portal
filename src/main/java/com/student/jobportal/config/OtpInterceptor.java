package com.student.jobportal.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class OtpInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        // If user is authenticated in Spring Security but not anonymous
        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            HttpSession session = request.getSession(false);
            Boolean otpVerified = (session != null) ? (Boolean) session.getAttribute("otp_verified") : null;
            
            // If OTP is not verified, they must verify it
            if (otpVerified == null || !otpVerified) {
                String uri = request.getRequestURI();
                // Exclude paths that don't need OTP verification check
                if (!uri.equals("/verify-otp") && !uri.equals("/logout") && !uri.startsWith("/css/") && !uri.startsWith("/js/")) {
                    response.sendRedirect("/verify-otp");
                    return false;
                }
            }
        }
        return true;
    }
}
