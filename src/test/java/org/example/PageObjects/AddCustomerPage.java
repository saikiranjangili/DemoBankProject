package org.example.PageObjects;

import org.example.testsCases.BaseClass;
import org.example.utilites.Reports;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.util.Objects;

@Listeners(Reports.class)
public class AddCustomerPage extends Reports {
    @FindBy(xpath = "//input[@name='name']")
    WebElement name;
    @FindBy(xpath = "//input[@value='m']")
    WebElement male;
    @FindBy(xpath = "//input[@value='f']")
    WebElement female;
    @FindBy(xpath = "//input[@name='dob']")
    WebElement dob;
    @FindBy(xpath = "//input[@name='city']")
    WebElement city;
    @FindBy(xpath = "//input[@name='state']")
    WebElement state;
    @FindBy(xpath = "//input[@name='pinno']")
    WebElement pin;
    @FindBy(xpath = "//input[@name='telephoneno']")
    WebElement mobile;
    @FindBy(xpath = "//input[@name='emailid']")
    WebElement email;
    @FindBy(xpath = "//input[@name='password']")
    WebElement password;
    @FindBy(xpath = "//textarea[@name='addr']")
    WebElement address;
    @FindBy(xpath = "//input[@value='Submit']")
    WebElement btnsubmit;
    @FindBy(xpath = "//input[@name='cusid']")
    WebElement txtCusid;
    @FindBy(xpath = "//input[@name='AccSubmit']")
    WebElement AccSubmit;

    @FindBy(xpath = "//tr/td[contains(text(),'Customer ID')]/following::td[1]")
    WebElement customerId;
    BaseClass bs = new BaseClass();
    WebDriver driver;

    HomePage hm = new HomePage(driver);

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addNewCustomer() throws IOException {
        String[][] array = bs.CusData();

        name.sendKeys(array[0][0]);

        String gender = array[0][1];
        if (Objects.equals(gender, "male")) {
            male.click();
        } else {
            female.click();
        }

        dob.sendKeys(array[0][2]);
        address.sendKeys(array[0][3]);
        city.sendKeys(array[0][4]);
        state.sendKeys(array[0][5]);
        pin.sendKeys(array[0][6]);
        mobile.sendKeys(array[0][7]);
        email.sendKeys(array[0][8]);
        password.sendKeys(array[0][9]);

        btnsubmit.click();


    }

    public String customerId() throws InterruptedException {
//        WebElement CussId = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr/td[contains(text(),'Customer ID')]/following::td[1]")));
        Thread.sleep(3000);
        WebElement CussId = driver.findElement(By.xpath("//tr/td[contains(text(),'Customer ID')]/following::td[1]"));
        return CussId.getText();
    }

    public void EditCustomer(String CusID) {

        txtCusid.sendKeys(CusID);
        AccSubmit.click();
    }


    public void DeleteCustomer(String CusID) {
        txtCusid.sendKeys(CusID);
        AccSubmit.click();

    }


}

