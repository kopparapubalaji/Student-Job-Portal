package com.student.jobportal.config;

import com.student.jobportal.entity.Job;
import com.student.jobportal.entity.User;
import com.student.jobportal.repository.JobRepository;
import com.student.jobportal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, JobRepository jobRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        User admin = userRepository.findByEmail("admin@jobportal.com").orElse(null);
        if (admin == null) {
            admin = new User();
            admin.setName("Admin User");
            admin.setEmail("admin@jobportal.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole("ROLE_ADMIN");
            admin.setEnabled(true);
            userRepository.save(admin);
        }

        User employer = userRepository.findByEmail("techcorp@jobportal.com").orElse(null);
        if (employer == null) {
            employer = new User();
            employer.setName("Tech Corp");
            employer.setEmail("techcorp@jobportal.com");
            employer.setPassword(passwordEncoder.encode("employer"));
            employer.setRole("ROLE_EMPLOYER");
            employer.setEnabled(true);
            userRepository.save(employer);
        }

        User student = userRepository.findByEmail("jane@jobportal.com").orElse(null);
        if (student == null) {
            student = new User();
            student.setName("Jane Doe");
            student.setEmail("jane@jobportal.com");
            student.setPassword(passwordEncoder.encode("student"));
            student.setRole("ROLE_STUDENT");
            student.setEnabled(true);
            student.setSkills("Java, Spring Boot, MySQL, React, CSS, JavaScript, Python, SQL, Tableau, Agile, Scrum, Communication, SEO, Content Writing, Google Analytics, AWS, Docker, Kubernetes, Figma, User Testing, Wireframing, Network Security, SIEM, Firewalls, Flutter, Dart, Firebase, HRIS, Scheduling, Azure, Terraform, Instagram, Twitter, Hootsuite, Node.js, Express, MongoDB, Excel, Finance, Modeling, Photoshop, Illustrator, InDesign, B2B Sales, CRM, Negotiation, PostgreSQL, Oracle, Selenium, JUnit, TestNG, Event Planning, Organization, Writing, Editing, Customer Service, Problem Solving, Sales, Teamwork");
            student.setResumePath("resume.pdf");
            userRepository.save(student);
        } else if (student.getResumePath() == null) {
            student.setResumePath("resume.pdf");
            userRepository.save(student);
        }

        // Add Jobs
        if (jobRepository.count() <= 8) {
            if (jobRepository.count() == 0) {
                Job job1 = new Job();
                job1.setTitle("Software Engineer");
                job1.setDescription("We are looking for a skilled Java developer to join our backend team. You will work on scalable microservices.");
                job1.setSkillsRequired("Java, Spring Boot, MySQL");
                job1.setSalary("$80k - $120k");
                job1.setLocation("Remote");
                job1.setCategory("Engineering");
                job1.setPostedByEmployer(employer);
                job1.setPostedDate(LocalDateTime.now());
                jobRepository.save(job1);

                Job job2 = new Job();
                job2.setTitle("Frontend Developer");
                job2.setDescription("Looking for a creative UI/UX developer who is proficient in React and modern CSS.");
                job2.setSkillsRequired("React, CSS, JavaScript");
                job2.setSalary("$70k - $100k");
                job2.setLocation("New York, NY");
                job2.setCategory("Design & UI");
                job2.setPostedByEmployer(employer);
                job2.setPostedDate(LocalDateTime.now().minusDays(2));
                jobRepository.save(job2);

                Job job3 = new Job();
                job3.setTitle("Data Analyst");
                job3.setDescription("Join us to analyze large datasets and generate actionable insights using Python and SQL.");
                job3.setSkillsRequired("Python, SQL, Tableau");
                job3.setSalary("$65k - $95k");
                job3.setLocation("San Francisco, CA");
                job3.setCategory("Data");
                job3.setPostedByEmployer(employer);
                job3.setPostedDate(LocalDateTime.now().minusDays(5));
                jobRepository.save(job3);
            }

            if (jobRepository.count() <= 3) {
                // Additional new jobs
                Job job4 = new Job();
                job4.setTitle("Product Manager");
                job4.setDescription("Lead cross-functional teams to deliver high-quality tech products on time.");
                job4.setSkillsRequired("Agile, Scrum, Communication");
                job4.setSalary("$110k - $150k");
                job4.setLocation("Austin, TX");
                job4.setCategory("Management");
                job4.setPostedByEmployer(employer);
                job4.setPostedDate(LocalDateTime.now().minusDays(1));
                jobRepository.save(job4);

                Job job5 = new Job();
                job5.setTitle("Marketing Specialist");
                job5.setDescription("Develop and execute digital marketing campaigns to drive user acquisition.");
                job5.setSkillsRequired("SEO, Content Writing, Google Analytics");
                job5.setSalary("$55k - $80k");
                job5.setLocation("Chicago, IL");
                job5.setCategory("Marketing");
                job5.setPostedByEmployer(employer);
                job5.setPostedDate(LocalDateTime.now().minusDays(3));
                jobRepository.save(job5);

                Job job6 = new Job();
                job6.setTitle("DevOps Engineer");
                job6.setDescription("Manage cloud infrastructure, CI/CD pipelines, and ensure high availability.");
                job6.setSkillsRequired("AWS, Docker, Kubernetes");
                job6.setSalary("$100k - $140k");
                job6.setLocation("Remote");
                job6.setCategory("Engineering");
                job6.setPostedByEmployer(employer);
                job6.setPostedDate(LocalDateTime.now().minusHours(5));
                jobRepository.save(job6);
                
                Job job7 = new Job();
                job7.setTitle("UX Researcher");
                job7.setDescription("Conduct user interviews and usability testing to improve our product experience.");
                job7.setSkillsRequired("Figma, User Testing, Wireframing");
                job7.setSalary("$75k - $110k");
                job7.setLocation("Seattle, WA");
                job7.setCategory("Design & UI");
                job7.setPostedByEmployer(employer);
                job7.setPostedDate(LocalDateTime.now().minusDays(4));
                jobRepository.save(job7);
                
                Job job8 = new Job();
                job8.setTitle("Cybersecurity Analyst");
                job8.setDescription("Monitor network traffic for security breaches and investigate violations.");
                job8.setSkillsRequired("Network Security, SIEM, Firewalls");
                job8.setSalary("$90k - $130k");
                job8.setLocation("Boston, MA");
                job8.setCategory("Security");
                job8.setPostedByEmployer(employer);
                job8.setPostedDate(LocalDateTime.now().minusDays(6));
                jobRepository.save(job8);
            }

            // 10 Even Newer Jobs
            Job job9 = new Job();
            job9.setTitle("Mobile App Developer");
            job9.setDescription("Build fast and beautiful iOS and Android applications using Flutter.");
            job9.setSkillsRequired("Flutter, Dart, Firebase");
            job9.setSalary("$85k - $125k");
            job9.setLocation("Remote");
            job9.setCategory("Engineering");
            job9.setPostedByEmployer(employer);
            job9.setPostedDate(LocalDateTime.now());
            jobRepository.save(job9);

            Job job10 = new Job();
            job10.setTitle("HR Coordinator");
            job10.setDescription("Assist in talent acquisition, onboarding, and employee relations.");
            job10.setSkillsRequired("Communication, HRIS, Scheduling");
            job10.setSalary("$50k - $70k");
            job10.setLocation("Miami, FL");
            job10.setCategory("Human Resources");
            job10.setPostedByEmployer(employer);
            job10.setPostedDate(LocalDateTime.now().minusDays(1));
            jobRepository.save(job10);

            Job job11 = new Job();
            job11.setTitle("Cloud Architect");
            job11.setDescription("Design secure and scalable cloud infrastructure for enterprise clients.");
            job11.setSkillsRequired("AWS, Azure, Terraform");
            job11.setSalary("$140k - $180k");
            job11.setLocation("Remote");
            job11.setCategory("Engineering");
            job11.setPostedByEmployer(employer);
            job11.setPostedDate(LocalDateTime.now().minusDays(2));
            jobRepository.save(job11);

            Job job12 = new Job();
            job12.setTitle("Social Media Manager");
            job12.setDescription("Manage social media accounts and grow audience engagement.");
            job12.setSkillsRequired("Instagram, Twitter, Hootsuite");
            job12.setSalary("$60k - $85k");
            job12.setLocation("Los Angeles, CA");
            job12.setCategory("Marketing");
            job12.setPostedByEmployer(employer);
            job12.setPostedDate(LocalDateTime.now().minusDays(3));
            jobRepository.save(job12);

            Job job13 = new Job();
            job13.setTitle("Backend Developer");
            job13.setDescription("Build high-performance REST APIs and microservices.");
            job13.setSkillsRequired("Node.js, Express, MongoDB");
            job13.setSalary("$90k - $130k");
            job13.setLocation("Austin, TX");
            job13.setCategory("Engineering");
            job13.setPostedByEmployer(employer);
            job13.setPostedDate(LocalDateTime.now().minusDays(4));
            jobRepository.save(job13);

            Job job14 = new Job();
            job14.setTitle("Financial Analyst");
            job14.setDescription("Analyze financial data to help guide business decisions.");
            job14.setSkillsRequired("Excel, Finance, Modeling");
            job14.setSalary("$75k - $105k");
            job14.setLocation("New York, NY");
            job14.setCategory("Finance");
            job14.setPostedByEmployer(employer);
            job14.setPostedDate(LocalDateTime.now().minusDays(5));
            jobRepository.save(job14);

            Job job15 = new Job();
            job15.setTitle("Graphic Designer");
            job15.setDescription("Create visual concepts, by hand or using computer software.");
            job15.setSkillsRequired("Photoshop, Illustrator, InDesign");
            job15.setSalary("$55k - $80k");
            job15.setLocation("Remote");
            job15.setCategory("Design & UI");
            job15.setPostedByEmployer(employer);
            job15.setPostedDate(LocalDateTime.now().minusDays(6));
            jobRepository.save(job15);

            Job job16 = new Job();
            job16.setTitle("Sales Executive");
            job16.setDescription("Drive revenue growth by bringing in new enterprise clients.");
            job16.setSkillsRequired("B2B Sales, CRM, Negotiation");
            job16.setSalary("$70k + Commission");
            job16.setLocation("Chicago, IL");
            job16.setCategory("Sales");
            job16.setPostedByEmployer(employer);
            job16.setPostedDate(LocalDateTime.now().minusDays(7));
            jobRepository.save(job16);

            Job job17 = new Job();
            job17.setTitle("Database Administrator");
            job17.setDescription("Ensure database security, performance, and uptime.");
            job17.setSkillsRequired("PostgreSQL, Oracle, SQL");
            job17.setSalary("$95k - $135k");
            job17.setLocation("Seattle, WA");
            job17.setCategory("Data");
            job17.setPostedByEmployer(employer);
            job17.setPostedDate(LocalDateTime.now().minusDays(8));
            jobRepository.save(job17);

            Job job18 = new Job();
            job18.setTitle("Quality Assurance Tester");
            job18.setDescription("Write automated tests and ensure software quality before release.");
            job18.setSkillsRequired("Selenium, JUnit, TestNG");
            job18.setSalary("$65k - $90k");
            job18.setLocation("Remote");
            job18.setCategory("Engineering");
            job18.setPostedByEmployer(employer);
            job18.setPostedDate(LocalDateTime.now().minusDays(9));
            jobRepository.save(job18);
        }

        if (jobRepository.count() <= 18) {
            Job job19 = new Job();
            job19.setTitle("Event Manager");
            job19.setDescription("Plan, coordinate, and execute large-scale corporate events and campus drives.");
            job19.setSkillsRequired("Event Planning, Organization, Communication");
            job19.setSalary("$60k - $85k");
            job19.setLocation("New York, NY");
            job19.setCategory("Management");
            job19.setPostedByEmployer(employer);
            job19.setPostedDate(LocalDateTime.now().minusDays(2));
            jobRepository.save(job19);

            Job job20 = new Job();
            job20.setTitle("Content Writer");
            job20.setDescription("Create engaging written content for blogs, newsletters, and marketing materials.");
            job20.setSkillsRequired("Writing, Editing, SEO");
            job20.setSalary("$50k - $70k");
            job20.setLocation("Remote");
            job20.setCategory("Marketing");
            job20.setPostedByEmployer(employer);
            job20.setPostedDate(LocalDateTime.now().minusDays(3));
            jobRepository.save(job20);

            Job job21 = new Job();
            job21.setTitle("Customer Support Representative");
            job21.setDescription("Provide excellent support to our global client base via phone and email.");
            job21.setSkillsRequired("Customer Service, Communication, Problem Solving");
            job21.setSalary("$40k - $55k");
            job21.setLocation("Austin, TX");
            job21.setCategory("Support");
            job21.setPostedByEmployer(employer);
            job21.setPostedDate(LocalDateTime.now().minusDays(1));
            jobRepository.save(job21);

            Job job22 = new Job();
            job22.setTitle("Sales Associate");
            job22.setDescription("Identify new business opportunities and build relationships with clients.");
            job22.setSkillsRequired("Sales, Negotiation, Teamwork");
            job22.setSalary("$55k + Commission");
            job22.setLocation("Chicago, IL");
            job22.setCategory("Sales");
            job22.setPostedByEmployer(employer);
            job22.setPostedDate(LocalDateTime.now().minusHours(8));
            jobRepository.save(job22);
        }
    }
}
