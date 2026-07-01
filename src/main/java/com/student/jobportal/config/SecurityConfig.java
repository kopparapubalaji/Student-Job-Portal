package com.student.jobportal.config;

import com.student.jobportal.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @org.springframework.beans.factory.annotation.Autowired
    private com.student.jobportal.repository.UserRepository userRepository;

    @org.springframework.beans.factory.annotation.Autowired
    private com.student.jobportal.service.EmailService emailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/register", "/login", "/css/**", "/js/**").permitAll()
                .requestMatchers("/student/**").hasAuthority("ROLE_STUDENT")
                .requestMatchers("/employer/**").hasAuthority("ROLE_EMPLOYER")
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .successHandler((request, response, authentication) -> {
                    String email = authentication.getName();
                    com.student.jobportal.entity.User user = userRepository.findByEmail(email).orElse(null);
                    
                    if (user != null) {
                        String otp = String.format("%06d", new java.util.Random().nextInt(999999));
                        user.setOtp(otp);
                        user.setOtpExpiry(java.time.LocalDateTime.now().plusMinutes(5));
                        user.setOtpVerified(false);
                        userRepository.save(user);
                        
                        emailService.sendEmail(user.getEmail(), "Your Login OTP", "Your OTP is: " + otp + ". It is valid for 5 minutes.");
                    }
                    
                    request.getSession().setAttribute("otp_verified", false);
                    response.sendRedirect("/verify-otp");
                })
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity in student project

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
