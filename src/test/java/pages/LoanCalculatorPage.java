package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoanCalculatorPage{

    protected WebDriver driver;

    private By applicationTypeSingle = By.xpath("//*[@for='application_type_single']");
    private By noOfDependents = By.cssSelector("[title='Number of dependants']");
    private By borrowTypeHome = By.xpath("//label[@for='borrow_type_home']");
    private By yourIncomeText = By.xpath("//span[@id='q2q1i1']/following-sibling::input");
    private By yourOtherIncomeText = By.xpath("//span[@id='q2q2i1']/following-sibling::input");
    private By livingExpensesText = By.id("expenses");
    private By homeLoanRepaymentText = By.id("homeloans");
    private By otherLoanRepaymentText = By.id("otherloans");
    private By otherCommitmentText = By.xpath("//span[@id='q3q4i1']/following-sibling::input");
    private By totalCreditLimitText = By.id("credit");
    private By borrowCalculatorButton = By.id("btnBorrowCalculater");
    private By estimatedAmount = By.id("borrowResultTextAmount");
    private By startOverButton = By.cssSelector("[aria-label='Start over']");
    private By errorMsg = By.xpath("//span[@class='borrow__error__text']");
    private By yourDetailsHeader = By.id("q1heading");

    public LoanCalculatorPage(WebDriver driver) {
        this.driver = driver;
        if(!driver.getCurrentUrl().contains("much-borrow")) {
            throw new IllegalStateException("The Current page is "+driver.getCurrentUrl());
        }
    }

    public void navigateToPage(String url){
        driver.navigate().to(url);
    }

    public void enterYourDetails(String dependents) {
        driver.findElement(applicationTypeSingle).click();
        Select select =new Select(driver.findElement(noOfDependents));
        select.selectByVisibleText(dependents);
        driver.findElement(borrowTypeHome).click();
    }

    public void enterYourEarning(String income, String otherIncome) {
        driver.findElement(yourIncomeText).sendKeys(income);
        driver.findElement(yourOtherIncomeText).sendKeys(otherIncome);
    }

    public void enterYourExpenses(String livingExpenses, String homeLoanRepayments, String otherLoanRepayments,
                                  String otherCommitments, String totalCreditCardLimits) {
        driver.findElement(livingExpensesText).sendKeys(livingExpenses);
        driver.findElement(homeLoanRepaymentText).sendKeys(homeLoanRepayments);
        driver.findElement(otherLoanRepaymentText).sendKeys(otherLoanRepayments);
        driver.findElement(otherCommitmentText).sendKeys(otherCommitments);
        driver.findElement(totalCreditLimitText).sendKeys(totalCreditCardLimits);
    }

    public void clickBorrowCalculatorButton(){
        driver.findElement(borrowCalculatorButton).click();
    }

    public String getResult() {
        //WebDriverWait wait = new WebDriverWait(driver, 30);
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(errorMsg)));
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (driver.findElement(estimatedAmount).isDisplayed()) {
            return driver.findElement(estimatedAmount).getText();
        }
        else if(driver.findElement(errorMsg).isDisplayed()){
            return driver.findElement(errorMsg).getText();
        }
        return null;
    }

    public void clickStartOverButton(){
        driver.findElement(startOverButton).click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(yourDetailsHeader)));
    }

    public void verifyFieldsAreReset(){
        driver.findElement(yourIncomeText).getText().equals("$0");
        driver.findElement(yourOtherIncomeText).getText().equals("$0");
        driver.findElement(livingExpensesText).getText().equals("$0");
        driver.findElement(totalCreditLimitText).getText().equals("$0");
        driver.findElement(borrowCalculatorButton).isDisplayed();
    }
}
