package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.LoanCalculatorPage;

import java.util.concurrent.TimeUnit;

public class LoanCalculatorSteps {

    WebDriver driver;
    LoanCalculatorPage loanCalculatorPage;

    @Before
    public  void launchBrowser(){
        String path = System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Given("User navigates to loan calculator page")
    public void userNavigatesToLoanCalculatorPage() {
        driver.navigate().to("https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/");
    }

    @When("user enters Your Details as {string}, {string}, {string}")
    public void userEntersYourDetailsAs(String arg0, String count, String arg2) {
        loanCalculatorPage = new LoanCalculatorPage(driver);
        loanCalculatorPage.enterYourDetails(count);
    }

    @When("provides Your Earnings as ${string}, ${string}")
    public void providesYourEarningsAs(String income, String otherIncome) {
        loanCalculatorPage = new LoanCalculatorPage(driver);
        loanCalculatorPage.enterYourEarning(income, otherIncome);
    }

    @When("provides Your Expenses as ${string}, ${string}, ${string}, ${string}, ${string}")
    public void providesYourExpensesAs(String livingExpenses, String homeLoanRepayments, String otherLoanRepayments,
                                       String otherCommitments, String totalCreditCardLimits) {
        loanCalculatorPage = new LoanCalculatorPage(driver);

        loanCalculatorPage.enterYourExpenses(livingExpenses, homeLoanRepayments, otherLoanRepayments, otherCommitments,
                totalCreditCardLimits);
    }

    @When("clicks on {string} button")
    public void clicks_on_button(String string) {
        loanCalculatorPage.clickBorrowCalculatorButton();

    }

    @Then("${string} should be displayed")
    public void shouldBeDisplayed(String expectedResult) {

        String Actual = loanCalculatorPage.getResult();
        Assert.assertTrue(Actual.equals(expectedResult),
                "The Actual is: "+Actual +" and Expected is: "+expectedResult);
    }

    @And("clicks on Start Over button")
    public void clicksOnStartOverButton() {
        loanCalculatorPage.clickStartOverButton();
    }

    @Then("all fields should be cleared.")
    public void allFieldsShouldBeCleared() {
        loanCalculatorPage.verifyFieldsAreReset();
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}