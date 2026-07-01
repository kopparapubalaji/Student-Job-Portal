from reportlab.lib.pagesizes import letter
from reportlab.pdfgen import canvas
from reportlab.lib.colors import HexColor

def create_resume(file_path):
    c = canvas.Canvas(file_path, pagesize=letter)
    width, height = letter

    # --- Header Background ---
    c.setFillColor(HexColor("#1E3A8A")) # Deep professional blue
    c.rect(0, height - 130, width, 130, fill=1)

    # --- Header Text (Name and Title) ---
    c.setFillColor(HexColor("#FFFFFF"))
    c.setFont("Helvetica-Bold", 36)
    c.drawString(40, height - 55, "Balaji.k")
    c.setFont("Helvetica", 14)
    c.drawString(40, height - 80, "Senior Full-Stack Engineer | Cloud Architect | Tech Lead")

    # --- Header Text (Contact Info) ---
    c.setFont("Helvetica", 10)
    c.drawString(40, height - 105, "Location: Bangalore, India")
    c.drawString(200, height - 105, "Email: balaji@gmail.com")
    c.drawString(380, height - 105, "Phone: +91 98765 43210")
    c.drawString(40, height - 120, "LinkedIn: linkedin.com/in/balajik")
    c.drawString(200, height - 120, "GitHub: github.com/balajik")
    c.drawString(380, height - 120, "Portfolio: balaji-portfolio.com")

    # Reset text color for body
    c.setFillColor(HexColor("#1F2937"))
    
    # Helper function for section headers
    def draw_section_header(title, y_pos):
        c.setFont("Helvetica-Bold", 14)
        c.setFillColor(HexColor("#1E3A8A"))
        c.drawString(40, y_pos, title.upper())
        c.setStrokeColor(HexColor("#D1D5DB"))
        c.setLineWidth(1)
        c.line(40, y_pos - 5, width - 40, y_pos - 5)
        c.setFillColor(HexColor("#374151"))
        return y_pos - 25

    y = height - 165

    # --- Professional Summary ---
    y = draw_section_header("Professional Summary", y)
    c.setFont("Helvetica", 10)
    summary = (
        "Highly motivated and solutions-driven Senior Full-Stack Engineer with over 6 years of experience "
        "architecting and developing\nscalable web applications, distributed systems, and cloud infrastructure. "
        "Proven ability to lead Agile development teams, optimize\ndatabase performance, and drive continuous "
        "integration and deployment (CI/CD) practices. Passionate about leveraging\nmodern technologies to solve "
        "complex business problems and deliver exceptional user experiences."
    )
    for line in summary.split("\n"):
        c.drawString(40, y, line)
        y -= 14

    y -= 10

    # --- Core Competencies ---
    y = draw_section_header("Technical Skills", y)
    c.setFont("Helvetica-Bold", 10)
    c.drawString(40, y, "Languages & Frameworks:")
    c.setFont("Helvetica", 10)
    c.drawString(185, y, "Java, Spring Boot, Python, React, JavaScript, TypeScript, Node.js, Express, Dart, Flutter")
    
    y -= 15
    c.setFont("Helvetica-Bold", 10)
    c.drawString(40, y, "Cloud & DevOps:")
    c.setFont("Helvetica", 10)
    c.drawString(185, y, "AWS, Azure, Docker, Kubernetes, Terraform, CI/CD, Jenkins, GitHub Actions")

    y -= 15
    c.setFont("Helvetica-Bold", 10)
    c.drawString(40, y, "Databases:")
    c.setFont("Helvetica", 10)
    c.drawString(185, y, "MySQL, PostgreSQL, Oracle, MongoDB, Redis")

    y -= 15
    c.setFont("Helvetica-Bold", 10)
    c.drawString(40, y, "Tools & Others:")
    c.setFont("Helvetica", 10)
    c.drawString(185, y, "Git, Agile/Scrum, JIRA, Figma, Tableau, Selenium, JUnit, RESTful APIs, Microservices")

    y -= 25

    # --- Experience ---
    y = draw_section_header("Professional Experience", y)
    
    # Experience 1
    c.setFont("Helvetica-Bold", 12)
    c.drawString(40, y, "Lead Software Engineer")
    c.setFont("Helvetica", 10)
    c.drawString(width - 150, y, "Jan 2021 - Present")
    y -= 15
    c.setFont("Helvetica-Oblique", 11)
    c.drawString(40, y, "Global Tech Solutions | Bangalore, India")
    y -= 18
    c.setFont("Helvetica", 10)
    exp1 = [
        "Architected and deployed a highly available microservices ecosystem using Java and Spring Boot.",
        "Led a cross-functional team of 8 engineers in migrating legacy monolithic applications to AWS cloud infrastructure.",
        "Implemented Docker and Kubernetes for container orchestration, reducing deployment times by 40%.",
        "Optimized complex PostgreSQL queries, improving system response time and reducing server load by 25%.",
        "Mentored junior developers and instituted code review standards that decreased critical bugs by 30%."
    ]
    for bullet in exp1:
        c.drawString(50, y, "• " + bullet)
        y -= 14

    y -= 10

    # Experience 2
    c.setFont("Helvetica-Bold", 12)
    c.drawString(40, y, "Full-Stack Developer")
    c.setFont("Helvetica", 10)
    c.drawString(width - 150, y, "Jun 2018 - Dec 2020")
    y -= 15
    c.setFont("Helvetica-Oblique", 11)
    c.drawString(40, y, "InnovateHub Startups | Hyderabad, India")
    y -= 18
    c.setFont("Helvetica", 10)
    exp2 = [
        "Developed responsive and dynamic user interfaces using React, Redux, and modern CSS/Tailwind.",
        "Built robust backend REST APIs in Node.js and Express, supporting over 100,000 daily active users.",
        "Integrated third-party APIs (Stripe, Twilio) and managed real-time data flow using WebSockets.",
        "Participated in daily stand-ups and sprint planning as part of an Agile Scrum team."
    ]
    for bullet in exp2:
        c.drawString(50, y, "• " + bullet)
        y -= 14

    y -= 15

    # --- Projects ---
    y = draw_section_header("Notable Projects", y)
    c.setFont("Helvetica-Bold", 11)
    c.drawString(40, y, "Smart Campus Management System")
    y -= 14
    c.setFont("Helvetica", 10)
    c.drawString(50, y, "• A comprehensive Spring Boot & React portal streamlining event registrations and campus analytics.")
    y -= 14
    c.drawString(50, y, "• Implemented role-based dashboards, automated email notifications, and JWT-based authentication.")
    
    y -= 25

    # --- Education & Certifications ---
    y = draw_section_header("Education & Certifications", y)
    
    c.setFont("Helvetica-Bold", 11)
    c.drawString(40, y, "Master of Computer Applications (MCA)")
    c.setFont("Helvetica", 10)
    c.drawString(width - 150, y, "Graduated: 2018")
    y -= 14
    c.drawString(40, y, "National Institute of Technology (NIT) | CGPA: 9.2/10")

    y -= 20
    c.setFont("Helvetica-Bold", 11)
    c.drawString(40, y, "Certifications:")
    y -= 14
    c.setFont("Helvetica", 10)
    c.drawString(50, y, "• AWS Certified Solutions Architect - Associate")
    y -= 14
    c.drawString(50, y, "• Oracle Certified Professional: Java SE 11 Developer")

    c.save()

if __name__ == "__main__":
    create_resume("uploads/resume.pdf")
    print("Resume created successfully at uploads/resume.pdf")
