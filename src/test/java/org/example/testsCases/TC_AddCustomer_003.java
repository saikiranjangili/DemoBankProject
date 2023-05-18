package org.example.testsCases;

import com.aventstack.extentreports.Status;
import org.example.PageObjects.AddCustomerPage;
import org.example.PageObjects.HomePage;
import org.example.PageObjects.LoginPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_AddCustomer_003 extends BaseClass {
    public String CustomerID;

    @Test
    public void customer_data() throws IOException, InterruptedException {
        logger.log(Status.INFO, "The Test is started");
        AddCustomerPage customerpage = new AddCustomerPage(driver);
        HomePage homepage = new HomePage(driver);
        LoginPage loginpage = new LoginPage(driver);

        TC_LoginTest_001 logintest = new TC_LoginTest_001();
        logintest.LoginTest();
        logger.log(Status.INFO, "Login is successfull");

        homepage.clickNewCustomer();
        customerpage.addNewCustomer();
        logger.log(Status.INFO, "New customer is added");

        CustomerID = customerpage.customerId();
        System.out.println("The Customer Id of the of newCustomer is " + CustomerID);

    }

}