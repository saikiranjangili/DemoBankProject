package org.example.testsCases;


import com.aventstack.extentreports.Status;
import org.example.PageObjects.LoginPage;
import org.testng.annotations.Test;

import java.io.IOException;


public class TC_LoginTest_002 extends BaseClass {

    @Test(dataProvider = "LoginData")
    public void Login_Parameterisation(String uname, String pwd, String Validation) throws InterruptedException, IOException {

        logger.log(Status.INFO, " The parameterization of Login is started with :" + Validation);
        LoginPage lp = new LoginPage(driver);
        lp.setUserName(uname);
        logger.log(Status.INFO, uname);
        lp.setPassword(pwd);
        logger.log(Status.INFO, pwd);
        lp.clickLogin();

        lp.loginVerification(Validation);


    }
}
