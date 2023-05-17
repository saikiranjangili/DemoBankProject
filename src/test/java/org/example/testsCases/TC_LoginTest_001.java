package org.example.testsCases;

import com.aventstack.extentreports.Status;
import org.example.PageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_LoginTest_001 extends BaseClass {
    @Test
    public void LoginTest() {

        logger.log(Status.INFO, "Test is Started");

        LoginPage lp = new LoginPage(driver);
        lp.setUserName(Username);
        logger.log(Status.INFO, "Username is entered");

        lp.setPassword(Password);
        logger.log(Status.INFO, "Password entered");

        lp.clickLogin();

        //System.out.println(driver.getTitle());

        if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
            Assert.assertTrue(true);
            logger.log(Status.PASS, "Login Test Passed");

        } else {
            Assert.fail();
            logger.log(Status.FAIL, "Login Test failed");
        }


    }
}
