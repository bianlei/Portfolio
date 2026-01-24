# Version Control Documentation

This document records my practical specifications and workflows of version control (Git) in QA-related work, focusing on **how to use Git to manage test assets (test cases, automation scripts, etc.) and collaborate with the team**.


## 1. Branching Strategy
I use branch management primarily to isolate different QA work scenarios (e.g., test script development, new feature testing), avoiding interference with the main version of test assets.

| Branch Type          | Purpose (QA Scenarios)                                                                 | Naming Example                  |
|----------------------|----------------------------------------------------------------------------------------|---------------------------------|
| **Main Branch (main)** | Stores the final, validated version of test assets (e.g., formal test cases, stable automation scripts) | `main`                          |
| **Automation Branches** | Develop/upgrade test automation scripts (e.g., Selenium scripts for login module testing) | `automation/login-test-script`  |
| **Feature Branches** | Test new product features (e.g., write test cases for the newly added "payment module") | `feature/test-payment-module`   |
| **Bugfix Branches**  | Fix problems in test assets (e.g., correct errors in existing test cases/bug report templates) | `bugfix/fix-test-case-logic`    |


## 2. Pull Requests & Code Review (QA Collaboration)
Pull Requests (PR) are my core way to ensure the quality of test assets before merging. I follow this process for all QA-related modifications:

### Pull Request Structure
Each PR includes:
- **Modified content**: Clear description (e.g., "Added 5 boundary test cases for the user registration module")
- **Testing proof**: Screenshots of local test results (e.g., the automation script runs successfully)
- **Link to issues**: If it’s to fix a problem, link to the corresponding JIRA/GitHub Issue (e.g., "Fixes #3: Error in login script’s password input logic")


## 3. Continuous Integration (CI)
I use GitHub Actions to build automated CI pipelines, which **automatically verify the validity of test assets** (to avoid invalid test scripts being merged into the main branch).

### My GitHub Actions CI Pipeline
When I push code to a branch or submit a PR, the pipeline automatically runs:
1. **Lint check**: Verify that the format of test cases (e.g., Markdown) and automation scripts (e.g., Python) is standardized
2. **Automation script test**: Run the modified Selenium script to confirm it can execute successfully (e.g., the login test can open the page and input credentials)
3. **Test case validation**: Check that the newly added test cases have no duplicate/conflicting content

### Example CI Configuration Snippet (For Reference)
```yaml
# .github/workflows/qa-ci.yml
name: QA Asset Validation
on: [push, pull_request]

jobs:
  validate-automation-scripts:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Set up Python
        uses: actions/setup-python@v5
        with:
          python-version: "3.11"
      - name: Install dependencies
        run: pip install selenium webdriver-manager
      - name: Run automation script test
        run: python Main/Automation-Project/login_test.py