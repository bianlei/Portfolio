# Bug Report Template
## Overview
This template follows **QA industry-standard practices** for bug reporting, designed to ensure clarity, reproducibility, and efficient collaboration between QA teams, developers, and product managers. Use this template to document bugs with all necessary details to speed up diagnosis and resolution.

---

## Summary
**Brief description of the bug:** [Concise 1-2 sentence summary of the issue, including the core problem and affected feature/module]

---

## Preconditions (Mandatory)
[List all prerequisite conditions required to reproduce the bug, e.g., user roles, system states, test data, or configuration settings]
1. [Example: Logged in as a Procurement Specialist with order creation permissions]
2. [Example: Target vendor (ID: VEN-XXXX-XXXX) is marked as inactive in the system]

---

## Steps to Reproduce
[Provide clear, sequential steps to reproduce the bug. Use exact actions and test data to ensure consistency]
1. **Step 1:** [Navigate to the affected module/page, e.g., Go to ERP Procurement Module → Create New Purchase Order]
2. **Step 2:** [Perform specific actions, e.g., Fill in mandatory fields with PO number: PO-2024-Q4-XXXX]
3. **Step 3:** [Perform trigger action, e.g., Click the Submit for Approval button]
4. **Step 4:** [Observe the system response]
5. ...

---

## Expected Behavior
[Describe what the system should do if the feature works correctly. Align with business requirements and user expectations]
1. [Example: The system blocks the submission and displays a user-friendly error message]
2. [Example: No data loss occurs, and the page remains on the purchase order creation interface]

---

## Actual Behavior
[Describe exactly what the system does when the bug occurs. Include error messages, page behavior, or data issues]
1. [Example: The page freezes for 3-5 seconds and redirects to a 500 Internal Server Error page]
2. [Example: Filled purchase order data is lost and not saved as a draft]
3. [Example: Error message displayed: "Oops! Something went wrong. Please try again later"]

---

## Screenshots & Logs
| Screenshot Description | Link/Attachment |
|------------------------|-----------------|
| [e.g., Purchase order form with inactive vendor selected] | [Link to screenshot or file path, e.g., screenshots/po_vendor_inactive.png] |
| [e.g., Error page after submission] | [Link to screenshot or file path, e.g., screenshots/po_500_error.png] |

**Backend/Frontend Error Log Snippet (If Applicable):**

[Paste relevant error logs here, e.g., Java NullPointerException stack trace or JavaScript console errors]


---

## Environment
- **Operating System:** [e.g., macOS Sonoma 14.5, Windows 11 Pro 22H2]
- **Browser/Version:** [e.g., Mozilla Firefox 121.0 (64-bit), Google Chrome 120.0.6099.216]
- **Software Version:** [e.g., ERP Procurement Module v2.1.4, Web App v1.5.0]
- **Database:** [e.g., PostgreSQL 14.8, MySQL 8.0.35]
- **Additional Environment Details:** [e.g., Production/Staging/Test environment, deployed via Jenkins on YYYY-MM-DD]

---

## Additional Context
[Add extra details to help diagnose the bug, including impact, regression notes, or workarounds]
1. **Impact Scope:** [e.g., Affects all procurement users selecting inactive vendors; ~8% of monthly orders are impacted]
2. **Regression Note:** [e.g., Bug not present in v2.1.3; introduced after YYYY-MM-DD deployment]
3. **Workaround:** [e.g., Manually verify vendor status in the vendor management module before creating orders]
4. **Related Issues:** [e.g., Link to related GitHub issues or JIRA tickets]

---

## Possible Root Cause & Solution
**Root Cause Hypothesis:** [Based on technical knowledge and logs, propose a potential cause for the bug]
[Example: The validateVendorStatus method lacks a null check for the vendor object, leading to NullPointerException]

**Proposed Solution:** [List actionable fixes for developers to implement]
1. [Example: Add a null check for the vendor object in PurchaseOrderService.java before accessing getActiveStatus()]
2. [Example: Implement frontend validation to disable the Submit button for inactive vendors]

---

## Severity & Priority
| Severity | Classification | Rationale |
|----------|----------------|-----------|
| **[ ] Critical** | Blocks all work, no workaround | [Explain if applicable] |
| **[ ] Major** | Impacts core business functions, has workaround | [Explain if applicable] |
| **[ ] Minor** | Low impact, cosmetic issues | [Explain if applicable] |
| **[ ] Trivial** | Very minor, no user impact | [Explain if applicable] |

| Priority | Classification | Rationale |
|----------|----------------|-----------|
| **[ ] High** | Resolve immediately | [Explain if applicable] |
| **[ ] Medium** | Resolve in current sprint | [Explain if applicable] |
| **[ ] Low** | Resolve in future sprints | [Explain if applicable] |

---

## Post-Fix Test Cases (Validation)
| Test Case ID | Test Scenario | Expected Result |
|--------------|---------------|-----------------|
| [e.g., TC-PO-001] | [e.g., Submit PO with an active vendor] | [e.g., PO is submitted successfully and redirected to approval queue] |
| [e.g., TC-PO-002] | [e.g., Submit PO with an inactive vendor] | [e.g., Frontend blocks submission with clear error message] |
| ... | ... | ... |

---

Thank you for your attention to this issue!
