package org.example.PageObjects;

import com.aventstack.extentreports.Status;
import org.example.utilites.Reports;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;

@Listeners(Reports.class)
public class HomePage extends Reports {
    public @FindBy(xpath = "//a[contains(text(),'Manager')]")
    WebElement Manager;
    public @FindBy(xpath = "//a[contains(text(),'New Customer')]")
    WebElement NewCustomer;
    @FindBy(xpath = "//a[contains(text(),'Edit Customer')]")
    WebElement EditCustomer;
    @FindBy(xpath = "//a[@href='DeleteCustomerInput.php']")
    WebElement DelCustomer;

    @FindBy(xpath = "//a[contains(text(),'Edit Account')]")
    WebElement EditAccount;

    @FindBy(xpath = "//a[contains(text(),'New Account')]")
    WebElement NewAccount;

    @FindBy(xpath = "//a[contains(text(),'Delete Account')]")
    WebElement DeleteAccount;

    @FindBy(xpath = "//a[contains(text(),'Withdrawal')]")
    WebElement Withdrawal;

    @FindBy(xpath = "//a[contains(text(),'Fund Transfer')]")
    WebElement FundTransfer;

    @FindBy(xpath = "//a[contains(text(),'Change Password')]")
    WebElement ChangePassword;

    @FindBy(xpath = "//a[contains(text(),'Balance Enquiry')]")
    WebElement BalanceEnquiry;

    @FindBy(xpath = "//a[contains(text(),'Mini Statement')]")
    WebElement MiniStatement;

    @FindBy(xpath = "//a[contains(text(),'Customised Statement')]")
    WebElement CustomisedStatement;

    @FindBy(xpath = "//a[contains(text(),'Log out')]")
    WebElement Logout;

    WebDriver driver;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickNewCustomer() {
        NewCustomer.click();
    }

    public void clickEditCustomer() {
        EditCustomer.click();
    }

    public void clickDelCustomer() {

        if (isAlertPresent()) {
            String text = driver.switchTo().alert().getText();
            if (text.equalsIgnoreCase("Email Address Already Exist !!")) {
                logger.log(Status.INFO, "Email already exists proceeding with deletion");
                driver.switchTo().alert().accept();
                //hm.DelCustomer.click();
                DelCustomer.click();
            }
        } else {
            DelCustomer.click();

        }
    }

    public void clickEditAccount() {
        EditAccount.click();
    }

    public void clickNewAccount() {
        NewAccount.click();
    }

    public void clickDeleteAccount() {
        DeleteAccount.click();
    }

    public void clickWithdrawal() {
        Withdrawal.click();
    }

    public void clickFundTransfer() {
        FundTransfer.click();
    }

    public void clickChangePassword() {
        ChangePassword.click();
    }

    public void clickBalanceEnquiry() {
        BalanceEnquiry.click();
    }

    public void clickMiniStatement() {
        MiniStatement.click();
    }

    public void clickCustomisedStatement() {
        CustomisedStatement.click();
    }

    public void clickLogout() {
        Logout.click();
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}


