package org.example.utilites;

// Listener class used to generate Extent reports

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reports implements ITestListener {

    public static ExtentReports extent;
    public static ExtentTest logger;
    //public static Logger log;
    public ExtentSparkReporter htmlReporter;


    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());   // time stamp
        String repName = "Test-Report-" + timeStamp + "html";
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName);  // specify location
        try {
            htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extent = new ExtentReports();
        extent.attachReporter((htmlReporter));
        extent.setSystemInfo("Host name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "Saikiran");

        htmlReporter.config().setDocumentTitle("DemoBanking Test Project");         //Title of report
        htmlReporter.config().setReportName("Functional Test Report");              // name of the report
        htmlReporter.config().setTheme(Theme.DARK);

    }

    public void onTestStart(ITestResult result) {
        logger = extent.createTest(result.getMethod().getMethodName());
        logger.log(Status.INFO, MarkupHelper.createLabel(result.getName(), ExtentColor.WHITE));

    }

    public void onTestSuccess(ITestResult result) {
        logger = extent.createTest(result.getName());         // Create New Entry in the report
        logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));         // send the passed information

    }

    public void onTestFailure(ITestResult result) {
        logger = extent.createTest(result.getName());
        logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
        String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + result.getName() + ".png";

        File f = new File(screenshotPath);
        if (f.exists()) {
            logger.fail("Screenshot is below : " + logger.addScreenCaptureFromPath(screenshotPath));
        }
    }

    public void onTestSkipped(ITestResult result) {
        logger = extent.createTest(result.getName());
        logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }


    public void onFinish(ITestContext context) {
        extent.flush();
    }


}
