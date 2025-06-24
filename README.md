# 🍬 Sweet Shop Automation Testing System

**Graduation Project - Al Hussein Technical University (HTU)**  
**Author:** Eng. Dalia Al Daja  
**Date:** June 2025

---

## 📌 Project Overview

This project is a **Test Automation Framework** for a Sweet Shop web application. The test simulates realistic user interactions on the website and includes database integration for dynamic data handling.  
Technologies used include **Java**, **Selenium WebDriver**, **TestNG**, and **Jenkins CI**, following the **Page Object Model (POM)** architecture.

---

## 🧩 Tech Stack

- **Java 17**  
- **Selenium WebDriver**  
- **TestNG**  
- **Maven**  
- **JDBC (MySQL DB connection)**  
- **Jenkins (CI)**  
- **Design Pattern:** Page Object Model (POM)  
- **Browser:** Chrome

---

## 🗂️ Project Structure

```

SweetShopTest/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/                      # Page Object classes for the web application
│   │       │   ├── BasketPage.java
│   │       │   ├── CheckoutPage.java
│   │       │   ├── HomePage.java
│   │       │   └── LoginPage.java
│   │       └── utils/
│   │           └── ExtentReportManager.java   # Handles ExtentReports for test reporting
│   │
│   └── resources/                         # Configuration and resource files
│
├── src/
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   └── BaseTest.java           # Base test class for WebDriver setup and teardown
│       │   ├── tests/
│       │   │   └── FullFlowTest.java       # Full scenario test case
│       │   ├── utils/
│       │   │   └── DatabaseUtil.java       # Utility class for database access
│       │   └── SweetShopTest.SweetShopTest # Main test suite or entry point
│
└── README.md                              # Project documentation

---

## ✅ Test Scenario Covered

1. **Open Website**  
2. Click on **“Browse Sweets”** to display all items  
3. Add **all items** to the basket  
4. Navigate to the **basket page**  
5. **Insert test data** into the database (first name, last name, city, zip code)  
6. **Read data** from DB and use it in checkout fields  
7. City field tested via:
   - Select dropdown (for predefined city)
   - Manual input (for custom city)
8. Zip code generated **randomly**  
9. Fill in **payment details** and click **"Continue to Checkout"**  
10. Empty the basket:
    - Confirm alert popup
11. Return to **Browse Sweets**  
12. Filter: add **only items with "fruit"** to the basket  
13. Delete **one item** from the basket:
    - Confirm deletion alert
14. Try to checkout with **incomplete fields** to trigger validation messages

---

## 📸 Reports

- **TestNG HTML Report**  
- **Screenshots on failure** (stored in `/screenshots`)  
- **Console logs** with step-by-step result

---

## ▶️ How to Run

### Requirements
- Java 17+  
- Maven  
- Chrome  
- MySQL database (local or test DB)

### Run via Maven

```bash
mvn clean test
```

---

## 🔁 Jenkins Integration

- Configured to run test automatically via Jenkins job  
- Test reports archived  
- Useful for monitoring test stability over builds

---

## 🗃️ Database Integration

- Used **JDBC** to:
  - Insert sample test data
  - Read data dynamically during test
- Ensures real-life data flow simulation between frontend & backend

---

## 🎓 Academic Info

- **University:** Al Hussein Technical University (HTU)  
- **Program:** Upskilling Program – Software QA Track  
- **Supervisor:** Eng. Abduraheem Alsaka  

---

## 🙌 Author Note

This project showcases a complete automation scenario simulating a real user journey, including cart actions, dynamic form filling, database interaction, validations, and alert handling — all with clean code and modular structure.

---
