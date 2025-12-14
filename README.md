🏥 Healthcare Management System (Java Web)
A Java web-based Healthcare Management System built using Servlets + JDBC + MySQL.
It manages patients (users) and doctors with proper validation, DAO-layer code quality, and small dashboard‑style features suited for academic evaluation.

🧰 Tech Stack
☕ Java (Servlets, JDBC)

🌐 HTML5, CSS, JavaScript

🗄️ MySQL

🚀 Apache Tomcat

🧩 VS Code / Eclipse as IDE

✨ Features
🔹 Core Functionality
👤 Patient (User) Management

Add user with Name, Age, City

View all users in a table

🩺 Doctor Management

Add doctor with Name, Specialization, Experience (years)

View all doctors in a table

🧱 DAO Layer

UserDao / UserDaoImpl for user CRUD

DoctorDao / DoctorDaoImpl for doctor CRUD

All DB operations via PreparedStatement for safety and maintainability

🌟 Innovative / Extra Features
🔍 Doctor search by specialization on the doctor listing page

📊 Summary counts:

Total number of users

Total number of doctors

🛡️ Client‑side + server‑side validation:

HTML5 required, min, max

JavaScript checks

Server code checks ranges and catches invalid numbers

🗂️ Project Structure
text
src/
  main/
    java/
      hospital/
        HospitalServlet.java      // User (patient) servlet
        DoctorServlet.java        // Doctor servlet + search
        User.java
        UserDao.java
        UserDaoImpl.java
        Doctor.java
        DoctorDao.java
        DoctorDaoImpl.java
        DatabaseUtil.java
    webapp/
      addRecord.html
      WEB-INF/
        web.xml

lib/
  servlet-api.jar
  mysql-connector-*.jar
HospitalServlet → /hospital : add + list users

DoctorServlet → /doctor : add + list + search doctors

DatabaseUtil → central JDBC connection helper

🛢️ Database Setup (MySQL)
sql
CREATE DATABASE hospital;
USE hospital;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    city VARCHAR(100) NOT NULL
);

CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    experience INT NOT NULL
);
Update credentials in DatabaseUtil.java:

java
private static final String URL = "jdbc:mysql://localhost:3306/hospital";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
🌍 Web Configuration
📄 web.xml
xml
<web-app ... version="4.0">
    <servlet>
        <servlet-name>HospitalServlet</servlet-name>
        <servlet-class>hospital.HospitalServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>HospitalServlet</servlet-name>
        <url-pattern>/hospital</url-pattern>
    </servlet-mapping>

    <servlet>
        <servlet-name>DoctorServlet</servlet-name>
        <servlet-class>hospital.DoctorServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>DoctorServlet</servlet-name>
        <url-pattern>/doctor</url-pattern>
    </servlet-mapping>

    <welcome-file-list>
        <welcome-file>addRecord.html</welcome-file>
    </welcome-file-list>
</web-app>
📝 addRecord.html
Form to add user (POST → /hospital)

Form to add doctor (POST → /doctor)

JavaScript for extra client‑side validation

Links to View All Users and View All Doctors

✅ Validation & Robustness
💻 Client‑Side
HTML5:

required on all inputs

min / max on age and experience

JavaScript:

Non‑empty checks for name, city, specialization

Age must be 1–120

Experience must be ≥ 0

🧮 Server‑Side
Trims and validates all parameters in servlets

Uses try / catch for Integer.parseInt to avoid crashes

Shows clear error messages if validation fails

Inserts into DB only when data is valid

🗄️ Data Access Layer
DAO classes encapsulate SQL logic

All queries use PreparedStatement

Easier to maintain and test, follows standard Java DAO pattern
