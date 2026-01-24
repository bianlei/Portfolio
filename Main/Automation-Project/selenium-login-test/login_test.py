# Main/Automation-Project/selenium-login-test/login_test.py
from selenium import webdriver
from selenium.webdriver.chrome.service import Service  # 新增：适配新版Selenium
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
import time

def test_successful_login():
    """
    Test scenario: Verify successful login with valid credentials
    This function automates the login process with correct username and password,
    and validates that the system grants access to the secure area.
    """
    # Initialize Chrome browser with updated driver service (compatible with Selenium 4.10+)
    service = Service(ChromeDriverManager().install())  # 核心修复：用Service封装驱动路径
    driver = webdriver.Chrome(service=service)  # 传入service参数
    
    try:
        # Navigate to the test login page (public QA practice platform)
        driver.get("https://the-internet.herokuapp.com/login")
        driver.maximize_window()
        time.sleep(1)  # Short implicit wait for page load stability

        # Locate username and password fields (explicit wait for element presence)
        username_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.ID, "username"))
        )
        password_input = driver.find_element(By.ID, "password")

        # Input valid credentials (standard test account for the practice platform)
        username_input.send_keys("tomsmith")
        password_input.send_keys("SuperSecretPassword!")

        # Click login button to submit credentials
        login_button = driver.find_element(By.CSS_SELECTOR, "button[type='submit']")
        login_button.click()

        # Validate successful login by checking the success message
        success_message = WebDriverWait(driver, 10).until(
            EC.visibility_of_element_located((By.ID, "flash"))
        )
        assert "You logged into a secure area!" in success_message.text, "Login success message not found"
        print("✅ Successful login test case passed - Valid credentials grant access")

    except AssertionError as ae:
        # Handle assertion failures (expected test outcomes not met)
        print(f"❌ Successful login test case failed (Assertion): {str(ae)}")
    except Exception as e:
        # Handle unexpected errors (e.g., element not found, page load issues)
        print(f"❌ Successful login test case failed (Unexpected error): {str(e)}")
    finally:
        # Ensure browser is closed regardless of test outcome (clean up)
        time.sleep(2)
        driver.quit()

def test_failed_login_wrong_password():
    """
    Test scenario: Verify failed login with incorrect password
    This function validates that the system rejects login attempts with wrong password
    and displays the correct error message to the user.
    """
    # Initialize Chrome browser with updated driver service
    service = Service(ChromeDriverManager().install())
    driver = webdriver.Chrome(service=service)
    
    try:
        # Navigate to login page
        driver.get("https://the-internet.herokuapp.com/login")
        driver.maximize_window()
        time.sleep(1)

        # Input valid username but incorrect password
        driver.find_element(By.ID, "username").send_keys("tomsmith")
        driver.find_element(By.ID, "password").send_keys("WrongPassword123!")

        # Submit login form
        driver.find_element(By.CSS_SELECTOR, "button[type='submit']").click()

        # Validate error message for incorrect password
        error_message = WebDriverWait(driver, 10).until(
            EC.visibility_of_element_located((By.ID, "flash"))
        )
        assert "Your password is invalid!" in error_message.text, "Incorrect password error message not found"
        print("✅ Failed login test case passed - Incorrect password is rejected")

    except AssertionError as ae:
        print(f"❌ Failed login test case failed (Assertion): {str(ae)}")
    except Exception as e:
        print(f"❌ Failed login test case failed (Unexpected error): {str(e)}")
    finally:
        time.sleep(2)
        driver.quit()

# Execute all test cases sequentially (no manual intervention required)
if __name__ == "__main__":
    print("=== Starting Login Function Automation Tests ===")
    test_successful_login()
    test_failed_login_wrong_password()
    print("=== All Login Tests Completed ===")