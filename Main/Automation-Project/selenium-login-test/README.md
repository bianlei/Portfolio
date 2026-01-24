# Selenium Login Automation Test
A Python-based UI automation project to test web login functionality, designed to demonstrate professional QA automation skills for New Zealand tech roles.

## Core Features
- Fully automated test execution (no manual intervention)
- Two key test scenarios (valid/invalid credentials)
- Robust error handling and clear terminal feedback
- Detailed test report with step-by-step screenshots

## Project Structure
selenium-login-test/
├─ login_test.py          # Core automation script
├─ README.md              # Project overview & quick start guide
├─ test_report.md         # Full test report with results & screenshots
└─ screenshots/           # Test execution screenshots
   ├─ login_scenario_step1_login_page.png
   ├─ login_scenario_step2_enter_valid_credentials.png
   ├─ login_scenario_step3_successful_login.png
   ├─ login_scenario_step4_enter_invalid_password.png
   ├─ login_scenario_step5_failed_login_error.png
   └─ login_terminal.png

## Quick Start
### Prerequisites
- Python 3.10+ installed
- Google Chrome browser

### Installation
```bash
# Install required packages
pip install selenium webdriver-manager

### Run Tests
# Navigate to project directory
cd Main/Automation-Project/selenium-login-test

# Execute automation script
python login_test.py