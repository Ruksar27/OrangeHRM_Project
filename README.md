# OrangeHRM_Project
The OrangeHRM project for QA automation testing focuses on ensuring the functionality and reliability of the OrangeHRM web application, a widely used open-source human resource management system. This project employs a robust automation framework built primarily using Selenium WebDriver and TestNG, facilitating comprehensive end-to-end UI testing.

Project Overview
The primary goal of this project is to automate various user interface tests on the OrangeHRM platform. By leveraging automation, the project aims to enhance testing efficiency, reduce manual effort, and ensure that critical functionalities perform as expected.

Key Features
End-to-End Testing: The framework covers a wide range of functionalities within the OrangeHRM application, including user login, employee management, and reporting features.
Selenium WebDriver: Utilized for interacting with web elements, allowing for automated execution of user actions on the application.
TestNG Framework: Employed for organizing test cases effectively, providing powerful annotations, and generating detailed reports.
Extent Reports Integration: This feature allows for the generation of visually appealing and comprehensive test execution reports, aiding in better analysis of test outcomes.

Technologies Used
Programming Language: Java
Automation Tools: Selenium WebDriver, TestNG
Reporting Tools: Extent Reports
Build Tools: Apache Maven

Testing Scenarios
The project includes various testing scenarios such as:
Logging into the OrangeHRM demo site.
Creating and managing employee records.
Conducting searches within the PIM (Personnel Information Management) dashboard.
Validating updates to employee information.
Performing logout operations and verifying session management.
Execution and Reporting
Tests can be executed using Maven or Gradle commands, which compile test classes and run the defined test suites. After execution, detailed reports are generated that include information about passed/failed tests and any screenshots captured during testing.
This automation project not only streamlines the testing process but also contributes to maintaining high software quality by enabling early detection of defects before production deployment.

# OrangeHRM Automation Testing Project

This project demonstrates the implementation of **Automation Testing** for **OrangeHRM** using **Selenium WebDriver**, **TestNG**, and **Maven**. The tests are designed to verify the key functionalities of the **OrangeHRM** application, including login, employee management, and other core HR features.

## Prerequisites

Before you begin, ensure that you have the following installed:

- **Java JDK 8 or above**: Required for running Selenium and TestNG.
- **Maven**: Used to manage dependencies and build the project.
- **Eclipse IDE or IntelliJ IDEA**: IDE for writing and running the tests.
- **Selenium WebDriver**: A tool for automating browsers.
- **TestNG**: A testing framework used for creating and running test cases.
- **ChromeDriver**: WebDriver for Google Chrome.

### Step-by-step setup:

1. **Java Installation**:  
   Download and install Java from the official [Oracle website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html). Make sure the JAVA_HOME environment variable is set correctly.

2. **Maven Installation**:  
   Download and install Maven from the official [Maven website](https://maven.apache.org/download.cgi). Set up the `MAVEN_HOME` environment variable and add Maven to the PATH.

3. **Browser Driver**:  
   Download the appropriate driver for your browser (e.g., ChromeDriver for Chrome) from the official website:
   - [ChromeDriver Download](https://sites.google.com/a/chromium.org/chromedriver/)
   - Extract the downloaded file and add the path to your environment variables.

4. **IDE Setup**:  
   Download and install an IDE of your choice:
   - **[Eclipse IDE](https://www.eclipse.org/downloads/)**
   - **[IntelliJ IDEA](https://www.jetbrains.com/idea/)**

## Project Setup

### 1. Clone the repository:

```bash
git clone https:[//github.com/Ruksar27/OrangeHRM_Project-automation.git](https://github.com/Ruksar27/OrangeHRM_Project)

### 2. Import the project into your IDE:
- Open your IDE and import the project as a **OrangeHRM_Project**.

### 3. Maven Dependencies:

This project uses Maven for dependency management. The necessary dependencies are defined in the `pom.xml` file. When the project is loaded in your IDE, Maven will automatically download the dependencies.

Here’s a sample of the dependencies included in the `pom.xml`:

```xml
<!--
			https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
			<dependency>
				<groupId>org.seleniumhq.selenium</groupId>
				<artifactId>selenium-java</artifactId>
				<version>4.24.0</version>
			</dependency>
			
			<!-- https://mvnrepository.com/artifact/org.testng/testng -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.10.2</version>
    <scope>test</scope>
</dependency>
<!-- https://mvnrepository.com/artifact/org.testng/testng -->


<!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>5.3.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.3.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.18.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.aventstack/extentreports -->
<dependency>
    <groupId>com.aventstack</groupId>
    <artifactId>extentreports</artifactId>
    <version>5.1.2</version>
</dependency>
```

---

## Test Structure

### 1. **TestNG Configuration**:

The **TestNG** test cases are organized into **Test Classes** under the `src/test/java` directory. These test classes are executed using **TestNG**.

Sample TestNG XML configuration (`testng.xml`):

```xml
<?xml version="1.0" encoding="UTF-8"?>
<suite name="OrangeHRM Suite">
    <test name="Login Tests">
        <classes>
            <class name="com.orangehrm.tests.LoginTest"/>
        </classes>
    </test>
    <test name="Employee Management Tests">
        <classes>
            <class name="com.orangehrm.tests.EmployeeTest"/>
        </classes>
    </test>
</suite>
```

### 2. **Test Classes**:

Test cases are written in Java using **Selenium WebDriver** for browser automation and **TestNG** for creating and running the tests. Example:

#### `LoginTest.java`:
```java
package com.orangehrm.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Set up the ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com");
    }

    @Test
    public void testLogin() {
        // Enter credentials and verify successful login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();
        
        assertTrue(driver.getTitle().contains("OrangeHRM"));
    }

    @AfterMethod
    public void teardown() {
        // Close the browser after each test
        driver.quit();
    }
}
```

---

## Running the Tests

### 1. **Running via Maven**:

You can run the test suite using Maven:

```bash
mvn clean test
```

This command will:
- Clean the project (remove any previous build).
- Run all the test cases defined in your **TestNG XML file**.

### 2. **Running via IDE**:

If you are using an IDE like **Eclipse** or **IntelliJ IDEA**, you can run the tests directly by right-clicking the `testng.xml` file and selecting **Run As > TestNG Suite**.

---

## Reporting

The project uses **TestNG** for reporting. After the tests are executed, TestNG will generate an HTML report under the `target/test-classes` directory. Open the `index.html` file to view the test results.

---

## Features

- **Selenium WebDriver** for browser automation.
- **TestNG** for running tests and generating reports.
- **Maven** for managing dependencies.
- Page Object Model (POM) design pattern for maintaining test scripts.

---

## Contribution

If you'd like to contribute to this project, feel free to fork the repository, make your changes, and submit a pull request.

---

## License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.




