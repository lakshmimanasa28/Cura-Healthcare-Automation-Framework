# 📘 Design Document – Cura Healthcare Automation Framework

---

## 🎯 Objective

To design a scalable automation framework for testing a healthcare web application using Selenium and TestNG.

---

## 🏗️ Architecture

The framework follows the **Page Object Model (POM)** architecture.

### Key Layers:

1. **Test Layer**

   * Contains all test cases
   * Uses page classes for actions

2. **Page Layer**

   * Encapsulates UI elements and actions
   * Improves maintainability

3. **Base Layer**

   * Handles WebDriver setup and teardown
   * Provides reusable methods

4. **Utility Layer**

   * Configuration handling
   * Reporting
   * Listeners

---

## 🔁 Execution Flow

```
Test → BaseTest → WebDriver Initialization
     → Page Objects → Perform Actions
     → Assertions → Report Generation
```

---

## 🧩 Key Components

### 1. BaseTest

* Initializes WebDriver
* Handles browser setup
* Uses ThreadLocal for parallel safety

---

### 2. BasePage

* Contains reusable methods:

  * click()
  * type()
  * waitForVisibility()
  * getText()

---

### 3. Page Classes

* LoginPage
* HomePage
* HistoryPage

Each page:

* Stores locators
* Provides actions

---

### 4. ConfigReader

* Reads values from `config.properties`
* Enables environment flexibility

---

### 5. Test Classes

* Contain actual test scenarios
* Use TestNG annotations

---

### 6. TestNG Features Used

* @Test
* @BeforeMethod
* @DataProvider
* @Listeners

---

### 7. Reporting

* Extent Reports for visual reporting
* Logs test execution results

---

## 🔄 Design Principles

* **Separation of Concerns**
* **Reusability**
* **Maintainability**
* **Scalability**

---

## ⚙️ Synchronization Strategy

* Explicit waits (WebDriverWait)
* Avoided Thread.sleep where possible

---

## ⚠️ Challenges Faced

| Issue              | Solution             |
| ------------------ | -------------------- |
| Stale elements     | Re-locate elements   |
| Login failures     | Removed forced waits |
| Logout instability | Forced navigation    |
| Timeout errors     | Proper waits         |

---

## 🚀 Future Enhancements

* Parallel execution
* CI/CD integration (Jenkins)
* Docker support
* Cross-browser grid execution

---

## 🎯 Conclusion

The framework is robust, modular, and follows best practices, making it suitable for real-world automation projects.
