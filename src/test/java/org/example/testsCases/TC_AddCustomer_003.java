package org.example.testsCases;

import com.aventstack.extentreports.Status;
import org.example.PageObjects.AddCustomerPage;
import org.example.PageObjects.HomePage;
import org.example.PageObjects.LoginPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_AddCustomer_003 extends BaseClass {
    @Test
    public void customer_data() throws IOException, InterruptedException {

        logger.log(Status.INFO, "The Test is started");

        LoginPage lp = new LoginPage(driver);
        lp.setUserName(Username);
        lp.setPassword(Password);
        lp.clickLogin();


        logger.log(Status.INFO, "Login is successfull");

        HomePage hm = new HomePage(driver);
        AddCustomerPage ap = new AddCustomerPage(driver);
        hm.clickNewCustomer();
        ap.addNewCustomer();
        logger.log(Status.INFO, "New customer is added");

        String CustomerID = ap.customerId();
        System.out.println("The Customer Id of the of newCustomer is " + CustomerID);

        Thread.sleep(2000);
        hm.clickDelCustomer();
        ap.DeleteCustomer(CustomerID);
        logger.log(Status.INFO, "The user is Deleted");


    }

}