# Air NZ QA Automation Demo Project
This project demonstrates core QA automation skills required for the Quality Assurance Engineer role at Air New Zealand, covering UI automation, API testing, and test framework integration.

## 🛠️ Tech Stack
- **Programming Language**: Java 11
- **UI Automation**: Selenium 4.15.0, Playwright 1.40.0
- **API Testing**: Rest Assured 5.3.0
- **Test Frameworks**: JUnit 5, TestNG 7.8.0
- **Build Tool**: Maven

## 📋 Project Structure
``
src/├── main/java/com/airnz/qa/ # Core config & utils└── test/java/com/airnz/qa/├── api/ # API automation tests (Rest Assured)├── ui/ # UI automation tests (Selenium + Playwright)├── junit/ # JUnit 5 integration tests└── testng/ # TestNG integration tests
``

## 🚀 How to Run
### Prerequisites
- Java 11+ installed
- Maven installed
- VS Code with Java Extension Pack

### Run All Tests
```bash
cd Portfolio/Main/Automation-Project/airnz-demo
mvn clean test