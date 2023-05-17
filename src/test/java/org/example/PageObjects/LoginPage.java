package org.example.PageObjects;

import com.aventstack.extentreports.Status;
import org.example.utilites.Reports;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;

@Listeners(Reports.class)
public class LoginPage extends Reports {

    WebDriver driver;
    @FindBy(name = "uid")
    WebElement userName;
    @FindBy(name = "password")
    WebElement password;
    @FindBy(name = "btnLogin")
    WebElement btnlogin;

    @FindBy(xpath = "//a[contains(text(),'Log out')]")
    WebElement btnLogout;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);


    }

    public void setUserName(String uname) {
        userName.clear();
        userName.sendKeys(uname);
    }

    public void setPassword(String pwd) {
        password.clear();
        password.sendKeys(pwd);
    }

    public void clickLogout() {
        btnLogout.click();
    }

    public void clickLogin() {
        btnlogin.click();
    }

    public void loginVerification(String status) throws InterruptedException {
        if (status.equalsIgnoreCase("valid")) {
            if (isAlertPresent()) {

                driver.switchTo().alert().accept();
                driver.switchTo().defaultContent();
                Thread.sleep(2000);
                Assert.fail();
                logger.log(Status.INFO, "Login failed");

            } else {
                Assert.assertTrue(true);
                logger.log(Status.PASS, "Login Passed");
                clickLogout();
                Thread.sleep(2000);
                driver.switchTo().alert().accept();
                driver.switchTo().defaultContent();

            }
        } else if (status.equalsIgnoreCase("invalid")) {
            if (isAlertPresent()) {
                driver.switchTo().alert().accept();
                driver.switchTo().defaultContent();
                Assert.assertTrue(true);
                logger.log(Status.PASS, "Login passed");

            } else {
                Assert.fail();
                logger.log(Status.FAIL, "Login Failed");
                clickLogout();
                driver.switchTo().alert().accept();
                driver.switchTo().defaultContent();

            }


        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}
