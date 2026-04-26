package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

public class Baseclass {

    public WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(alwaysRun = true)
    @Parameters({"os", "browser"})
    public void setup(@Optional("windows") String os,
                      @Optional("chrome") String br) throws IOException {

        FileReader file = new FileReader(System.getProperty("user.dir")
                + "/src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        switch (br.toLowerCase()) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.setExperimentalOption("excludeSwitches",
                        Collections.singletonList("enable-automation"));
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
                edgeOptions.addArguments("--disable-notifications");
                edgeOptions.setExperimentalOption("excludeSwitches",
                        Collections.singletonList("enable-automation"));
                edgeOptions.setExperimentalOption("useAutomationExtension", false);
                driver = new EdgeDriver(edgeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-notifications");
                firefoxOptions.addPreference("dom.webnotifications.enabled", false);
                firefoxOptions.addPreference("credentials_enable_service", false);
                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                System.out.println("Invalid browser name: " + br);
                return;
        }

        if (driver == null) return;

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("webisteurl"));
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void quiting() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String captureScreen(String testName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String targetFilePath = System.getProperty("user.dir")
                + File.separator + "screenshot"
                + File.separator + testName + "_" + timeStamp + ".png";
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(targetFilePath);
        FileUtils.copyFile(sourceFile, targetFile);
        return targetFilePath;
    }
}