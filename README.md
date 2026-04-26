# 🛒 Cura Healthcare Automation Framework

## Project Overview

This project is a Selenium-based automation framework developed to test the **Cura Healthcare Service web application**.

It follows the **Page Object Model (POM)** design pattern and is built using **Java, Selenium WebDriver, TestNG**, and **Extent Reports**.

---

## Features

*  Page Object Model (POM) implementation
*  Data-driven testing using TestNG DataProvider
*  Cross-browser support (Chrome, Firefox)
*  Configurable via `config.properties`
*  Reusable utilities (ConfigReader, BasePage)
*  Extent Reports integration
*  Thread-safe WebDriver (ThreadLocal)
*  Proper synchronization using WebDriverWait

---

## Tech Stack

* **Language:** Java
* **Automation Tool:** Selenium WebDriver
* **Test Framework:** TestNG
* **Build Tool:** Maven (if used)
* **Reporting:** Extent Reports
* **IDE:** Eclipse

---

## Project Structure

```
src/test/java
│
├── base
│   └── BaseTest.java
│
├── pages
│   ├── LoginPage.java
│   ├── HomePage.java
│   └── HistoryPage.java
│
├── tests
│   ├── LoginTest.java
│   ├── AppointmentBookingTest.java
│   ├── AppointmentHistoryTest.java
│   └── FormValidationsTest.java
│
├── utils
│   ├── ConfigReader.java
│   ├── ExtentTestManager.java
│   └── TestListener.java
│
└── resources
    └── config.properties
```

---

## Configuration

Edit `config.properties`:

```
browser=chrome
baseUrl=https://katalon-demo-cura.herokuapp.com/
username=John Doe
password=ThisIsNotAPassword
timeout=10
```

---

## How to Run Tests

### Using TestNG XML

* Right-click `testng.xml`
* Click **Run As → TestNG Suite**

### Using Eclipse

* Right-click test class → Run As → TestNG Test

---

## Test Coverage

### ✔ Login Tests

* Valid login
* Invalid login
* Protected page redirect
* Logout functionality

### ✔ Appointment Tests

* Book appointment
* Verify details
* Readmission checkbox

### ✔ History Tests

* Verify appointment history
* Multiple appointments

### ✔ Validation Tests

* Empty login
* Empty date
* Long comment

---

##  Reporting

* Extent Reports generated after execution
* Located in `/reports` folder

---



## Conclusion

This framework demonstrates a scalable and maintainable automation design using industry best practices.

---

## 👨‍💻 Author

Katakamsetty Lakshmi Manasa
