package org.example.testsCases;

import com.aventstack.extentreports.Status;
import org.example.PageObjects.AddCustomerPage;
import org.example.PageObjects.HomePage;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_DeleteCustomer_006 extends BaseClass {

    @Test
    public void DeleteCustomerData() throws IOException, InterruptedException {

        logger.log(Status.INFO, "Test started");
        TC_AddCustomer_003 addcustest = new TC_AddCustomer_003();
        addcustest.customer_data();

        AddCustomerPage customerpage = new AddCustomerPage(driver);
        HomePage homepage = new HomePage(driver);

        logger.log(Status.INFO, "Customer is been added");
        homepage.clickDelCustomer();
        logger.log(Status.INFO, "Deletion of the customer is started");

        customerpage.DeleteCustomer(customerpage.customerId());
        logger.log(Status.INFO, "The customer data is deleted");

        logger.log(Status.INFO, " The test is passed");
    }
}
