# Sample Bug Report
## Overview
In this repository, I follow **QA industry-standard bug reporting practices** to improve collaboration between QA teams, developers and product managers, and to make sure software issues are fixed efficiently. My bug reports focus on three key points: reproducibility, clear descriptions and actionable details, which I have developed through my experience in backend development and QA practice.

Key standards I stick to are listed below:
1. **Precise Descriptions**: Make a clear difference between *observed behavior* and *assumed root causes*, so that developers will not be misled.
2. **Complete Reproducibility**: Provide step-by-step operation processes, including **exact test data, environment details and preconditions** (such as user roles and system status).
3. **Impact-Driven Severity**: Classify bugs according to their **business impact** (not just technical severity) to match product development priorities.
4. **Validation Guidance**: Include post-fix test cases to ensure bugs are fully fixed and prevent similar problems from happening again.

---

## Summary
**Brief description of the bug**: The ERP procurement system returns a 500 internal server error when users try to submit a purchase order with a `vendor_id` that exists in the database but is marked as *inactive*.

---

## Steps to Reproduce
*Preconditions*:
- Log in with the **Procurement Specialist** role (with permissions to create and edit purchase orders)
- The target vendor (ID: `VEN-2024-0089`) has been marked as `inactive` in the vendor management module (status updated via the admin panel 24 hours earlier)

Steps:
1. Go to the ERP procurement module → **Create New Purchase Order** page.
2. Fill in all mandatory fields:
   - Purchase Order Number: `PO-2024-Q4-012`
   - Select Vendor: Search for and choose `VEN-2024-0089` (the system automatically fills in the vendor name: *ABC Logistics Co.* even though the vendor is inactive)
   - Add line items: 5 units of `Laptop - Dell Latitude 5450` (product ID: `PROD-0015`)
   - Set delivery date: 2024-12-15
3. Click the **Submit for Approval** button.
4. Check the system response.

---

## Expected Behavior
1. The system should **stop the purchase order from being submitted** and show a clear, easy-to-understand error message: *"Cannot submit order: Selected vendor (ABC Logistics Co.) is marked as inactive. Please contact the admin to reactivate the vendor or choose an active one."*
2. No 500 error should appear, and the page should stay on the purchase order creation interface without losing any data.

---

## Actual Behavior
1. The page freezes for 3 to 5 seconds, then jumps to a general **500 Internal Server Error** page.
2. All the purchase order data filled in Step 2 is lost and not saved as a draft.
3. The error message shown to users is: *"Oops! Something went wrong. Please try again later or contact IT support."*

---

## Screenshots & Logs
| Screenshot Description | Link/Attachment |
|------------------------|-----------------|
| Purchase order form with the inactive vendor selected | ![Vendor Selection Screenshot](screenshots/po_vendor_inactive.png) |
| 500 error page after submission | ![500 Error Screenshot](screenshots/po_500_error.png) |

**Backend Error Log Snippet** (extracted from Jenkins CI/CD pipeline logs, timestamp: 2024-10-18 14:22:35):

java.lang.NullPointerException: Cannot invoke "com.chinasoft.erp.model.Vendor.getActiveStatus()" because "vendor" is null
at com.chinasoft.erp.service.PurchaseOrderService.validateVendorStatus(PurchaseOrderService.java:189)
at com.chinasoft.erp.controller.PurchaseOrderController.submitOrder(PurchaseOrderController.java:76)


---

## Environment
- **Operating System**: macOS Sonoma 14.5
- **Browser/Version**: Mozilla Firefox 121.0 (64-bit) | Google Chrome 120.0.6099.216 (verified on both browsers)
- **Software Version**: ERP Procurement Module v2.1.4 (deployed through Jenkins on 2024-10-15)
- **Database**: PostgreSQL 14.8 (production environment)

---

## Additional Context
1. **Impact Scope**: This bug affects all procurement users who try to select inactive vendors. According to internal data, about 8% of monthly purchase orders need vendor status checks. So this issue has a moderate business impact, as it delays order creation and requires IT support to step in.
2. **Regression Note**: This bug **did not exist in version v2.1.3** — it appeared after the deployment on October 15, which included the optimization of the vendor status field.
3. **Workaround**: Users can manually check the vendor’s active status in the vendor management module before creating a purchase order. However, this adds 2 to 3 minutes to each order and is easy to make mistakes.

---

## Possible Root Cause & Solution
**Root Cause Hypothesis** (based on backend logs and my knowledge of the code):
The `validateVendorStatus` method in `PurchaseOrderService.java` does not check if the `vendor` object is null *after* getting it from the database. When an inactive vendor is selected, the database query returns a partial object (without filling in the `activeStatus` field), which leads to a `NullPointerException`.

**Proposed Solution**:
1. Add a null check for the `vendor` object in `PurchaseOrderService.java:189` before using the `getActiveStatus()` method.
2. Add frontend validation: Disable the **Submit for Approval** button if the selected vendor is inactive, and show a tooltip to explain the vendor’s status.
3. Add a database constraint to make sure inactive vendors do not show up in the vendor search dropdown for procurement users.

---

## Severity & Priority
| Severity | Classification | Rationale |
|----------|----------------|-----------|
| **[ ] Critical** | Blocks all work, no workaround | ❌ |
| **[x] Major** | Impacts core business functions, has a workaround | Causes 500 errors and data loss, and affects procurement workflows |
| **[ ] Minor** | Low impact, cosmetic issues | ❌ |
| **[ ] Trivial** | Very minor, no user impact | ❌ |

| Priority | Classification | Rationale |
|----------|----------------|-----------|
| **[x] Medium** | Resolve in current sprint | Affects regular users but has a manual workaround; matches the sprint goal of improving the stability of the procurement module |

---

## Post-Fix Test Cases (Validation)
| Test Case ID | Test Scenario | Expected Result |
|--------------|---------------|-----------------|
| TC-PO-001 | Submit a PO with an **active** vendor | PO is submitted successfully, and the page redirects to the approval queue page |
| TC-PO-002 | Submit a PO with an **inactive** vendor | Frontend stops the submission, shows a clear error message, and no data is lost |
| TC-PO-003 | Submit a PO with a vendor marked inactive *after* the PO draft is saved | The system reminds users to check the vendor status again before final submission |
| TC-PO-004 | Check that inactive vendors do not appear in the vendor search dropdown | Inactive vendors are filtered out from the dropdown list for users with the Procurement Specialist role |
