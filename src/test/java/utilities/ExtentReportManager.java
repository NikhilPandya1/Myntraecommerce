package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import testCases.Baseclass;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    String repName;

    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        String reportPath = System.getProperty("user.dir") 
                + File.separator + "reports" 
                + File.separator + repName;

        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Myntra Automation Report");
        sparkReporter.config().setReportName("Myntra Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "Myntra");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result) {
        test.set(extent.createTest(result.getTestClass().getName()));
        test.get().assignCategory(result.getMethod().getGroups());
        test.get().log(Status.PASS, result.getName() + " got successfully executed");
    }

    public void onTestFailure(ITestResult result) {
        test.set(extent.createTest(result.getTestClass().getName()));
        test.get().assignCategory(result.getMethod().getGroups());
        test.get().log(Status.FAIL, result.getName() + " got failed");
        test.get().log(Status.INFO, result.getThrowable().getMessage());

        try {
            Baseclass bs = (Baseclass) result.getInstance();
            String imgPath = bs.captureScreen(result.getName());
            test.get().addScreenCaptureFromPath(imgPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test.set(extent.createTest(result.getTestClass().getName()));
        test.get().assignCategory(result.getMethod().getGroups());
        test.get().log(Status.SKIP, result.getName() + " got skipped");

        if (result.getThrowable() != null) {
            test.get().log(Status.INFO, result.getThrowable().getMessage());
        }
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();
        System.out.println("Extent Report generated at: " 
            + System.getProperty("user.dir") 
            + File.separator + "reports" 
            + File.separator + repName);
    }
}