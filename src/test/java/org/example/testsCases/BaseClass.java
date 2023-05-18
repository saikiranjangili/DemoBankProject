package org.example.testsCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.example.utilites.ExcelUtilities;
import org.example.utilites.ReadConfig;
import org.example.utilites.Reports;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

@Listeners(Reports.class)
public class BaseClass extends Reports {

    public static WebDriver driver;
    public static ExcelUtilities xl = new ExcelUtilities();
    ReadConfig rConfig = new ReadConfig();
    public String baseUrl = rConfig.getApplicationURL();
    public String Username = rConfig.getUserName();
    public String Password = rConfig.getPassword();


    @Parameters("browser")
    @BeforeClass
    public void setup(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        }
        if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseUrl);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }


    public void captureScreen(WebDriver driver, String tname) throws IOException {
        TakesScreenshot s = (TakesScreenshot) driver;
        File source = s.getScreenshotAs((OutputType.FILE));
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken");

    }

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws IOException {

        int rownum = xl.getRowCount("Login");
        int colcount = xl.getCellCount("Login", 1);

        String[][] logindata = new String[rownum][colcount];

        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                logindata[i - 1][j] = xl.getCellData("Login", i, j);
            }

        }
        return logindata;
    }

    //    @DataProvider(name = "CustomerData")
    public String[][] CusData() throws IOException {

        int rownumm = xl.getRowCount("Customer");
        int colcountt = xl.getCellCount("Customer", 1);

        String[][] Customerdata = new String[rownumm][colcountt];

        for (int i = 1; i <= rownumm; i++) {
            for (int j = 0; j < colcountt; j++) {
                Customerdata[i - 1][j] = xl.getCellData("Customer", i, j);
            }

        }
        return Customerdata;
    }


}
