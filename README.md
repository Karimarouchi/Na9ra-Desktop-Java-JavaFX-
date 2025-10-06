# Na9ra – Desktop E-Learning (Java + JavaFX)

**Na9ra** is a modern desktop e-learning platform developed in **Java** and **JavaFX**.  
It allows students to learn online through an interactive interface with pre-recorded courses, user management, event organization, quizzes, and more — all built with a strong focus on **security and communication**.

---

## ✨ Key Features
- **Online Learning Platform**: Students can browse and enroll in available courses directly within the desktop app.  
- **Course Management**: Administrators and instructors can create, update, and manage courses, sessions, and learning materials.  
- **User Management**: Full CRUD operations for students, instructors, and administrators.  
- **Event Management**: Ability to schedule, publish, and notify users about educational events or webinars.  
- **Quiz and Evaluation System**: Interactive quizzes for students, automatic correction, and scoring.  
- **Secure Authentication**: Role-based login system with password encryption and access control.  

---

## 📧 Notifications & Communication
- **Email Reminders**:  
  The system automatically sends email notifications to students about **assignment deadlines, upcoming events, or course updates**.
- **SMS Alerts**:  
  SMS messages are sent to users when **a new account is created** or when important system alerts occur (using an integrated SMS API such as Twilio).  
- **Admin Dashboard**:  
  Allows administrators to monitor users, view platform statistics, and manage email/SMS templates.  

---

## 🛠️ Technologies
- **Language**: Java 17+  
- **Framework**: JavaFX (for GUI)  
- **Build Tool**: Maven or Gradle  
- **Database**: MySQL / Oracle  
- **Mail API**: JavaMail / SMTP  
- **SMS API**: Twilio (or equivalent)  
- **Security**: BCrypt password hashing, input validation, and exception handling  

---

## ⚙️ Build & Run
### Maven
```bash
mvn clean javafx:run
