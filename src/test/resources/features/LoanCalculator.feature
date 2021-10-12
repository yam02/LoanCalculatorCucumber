Feature: Feature to test borrow estimates

  @smoke
  Scenario Outline: test to calculate the borrowing estimate of User and error message for invalid input
    Given User navigates to loan calculator page
    When user enters Your Details as "<applicationType>", "<dependents>", "<borrowType>"
    When provides Your Earnings as $"<income>", $"<otherIncome>"
    When provides Your Expenses as $"<livingExpenses>", $"<homeLoanRepayments>p", $"<otherLoanRepayments>", $"<otherCommitments>", $"<totalCreditCardLimits>"
    And clicks on "Work out how much I could borrow" button
    Then $"<estimate>" should be displayed

    Examples:
      | applicationType | dependents | borrowType      | income | otherIncome | livingExpenses | homeLoanRepayments | otherLoanRepayments | otherCommitments | totalCreditCardLimits | estimate                                                                                                                                                        |
      | Single          | 0          | Home to live in | 80,000 | 10,000      | 500            | 0                  | 100                 | 0                | 10000                 | $508,000                                                                                                                                                         |
      | Single          | 0          | Home to live in | 0      | 0           | 1              | 0                  | 0                   | 0                | 0                     | Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 035 500. |

  @smoke
  Scenario Outline: Verify Start Over Button
    Given User navigates to loan calculator page
    When user enters Your Details as "<applicationType>", "<dependents>", "<borrowType>"
    When provides Your Earnings as $"<income>", $"<otherIncome>"
    When provides Your Expenses as $"<livingExpenses>", $"<homeLoanRepayments>p", $"<otherLoanRepayments>", $"<otherCommitments>", $"<totalCreditCardLimits>"
    When clicks on "Work out how much I could borrow" button
    When $"<estimate>" should be displayed
    And clicks on Start Over button
    Then all fields should be cleared.

    Examples:
      | applicationType | dependents | borrowType      | income | otherIncome | livingExpenses | homeLoanRepayments | otherLoanRepayments | otherCommitments | totalCreditCardLimits | estimate                                                                                                                                                        |
      | Single          | 0          | Home to live in | 80,000 | 10,000      | 500            | 0                  | 100                 | 0                | 10000                 | $508,000                                                                                                                                                         |
