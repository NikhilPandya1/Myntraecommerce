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
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import com.epam.healenium.SelfHealingDriver;
import java.net.URL;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Baseclass {

    public SelfHealingDriver driver;
    public Logger logger;
    public Properties p;


    @BeforeClass(alwaysRun = true)
    @Parameters({"os", "browser"})
    public void setup(@Optional("windows") String os,
                      @Optional("chrome") String br) throws IOException {

        FileReader file = new FileReader(
                System.getProperty("user.dir")
                        + "/src/test/resources/config.properties");

        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        WebDriver delegate = null;

        // =========================
        // REMOTE EXECUTION
        // =========================

        if (p.getProperty("execution_env")
                .equalsIgnoreCase("remote")) {

            System.out.println("Running in REMOTE mode");

            switch (br.toLowerCase()) {

                case "chrome":

                    ChromeOptions chromeOptions = new ChromeOptions();

                    chromeOptions.addArguments(
                            "--disable-blink-features=AutomationControlled");

                    chromeOptions.addArguments(
                            "--disable-notifications");

                    chromeOptions.setExperimentalOption(
                            "excludeSwitches",
                            Collections.singletonList("enable-automation"));

                    chromeOptions.setExperimentalOption(
                            "useAutomationExtension",
                            false);

                    delegate = new RemoteWebDriver(
                            new URL("http://192.168.101.9:4444"),
                            chromeOptions);

                    break;

                case "edge":

                    EdgeOptions edgeOptions = new EdgeOptions();

                    edgeOptions.addArguments(
                            "--disable-blink-features=AutomationControlled");

                    edgeOptions.addArguments(
                            "--disable-notifications");

                    delegate = new RemoteWebDriver(
                            new URL("http://192.168.101.9:4444"),
                            edgeOptions);

                    break;

                case "firefox":

                    FirefoxOptions firefoxOptions = new FirefoxOptions();

                    firefoxOptions.addArguments(
                            "--disable-notifications");

                    delegate = new RemoteWebDriver(
                            new URL("http://192.168.101.9:4444"),
                            firefoxOptions);

                    break;

                default:
                    System.out.println("Invalid browser name");
                    return;
            }
        }

        // =========================
        // LOCAL EXECUTION
        // =========================

        else if (p.getProperty("execution_env")
                .equalsIgnoreCase("local")) {

            System.out.println("Running in LOCAL mode");

            switch (br.toLowerCase()) {

                case "chrome":

                    ChromeOptions chromeOptions = new ChromeOptions();

                    chromeOptions.addArguments(
                            "--disable-blink-features=AutomationControlled");

                    chromeOptions.addArguments(
                            "--disable-notifications");

                    chromeOptions.setExperimentalOption(
                            "excludeSwitches",
                            Collections.singletonList("enable-automation"));

                    chromeOptions.setExperimentalOption(
                            "useAutomationExtension",
                            false);

                    delegate = new ChromeDriver(chromeOptions);

                    break;

                case "edge":

                    EdgeOptions edgeOptions = new EdgeOptions();

                    edgeOptions.addArguments(
                            "--disable-blink-features=AutomationControlled");

                    edgeOptions.addArguments(
                            "--disable-notifications");

                    delegate = new EdgeDriver(edgeOptions);

                    break;

                case "firefox":

                    FirefoxOptions firefoxOptions = new FirefoxOptions();

                    firefoxOptions.addArguments(
                            "--disable-notifications");

                    delegate = new FirefoxDriver(firefoxOptions);

                    break;

                default:
                    System.out.println("Invalid browser name");
                    return;
            }
        }

        else {
            System.out.println("Invalid execution_env value");
            return;
        }

        // =========================
        // HEALENIUM WRAP
        // =========================

        if (delegate != null) {

            driver = SelfHealingDriver.create(delegate);

            System.out.println("Driver created successfully");

        } else {

            System.out.println("Driver is NULL");
            return;
        }

        // =========================
        // COMMON SETUP
        // =========================

        driver.manage().deleteAllCookies();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get(p.getProperty("websiteurl"));

        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void quiting() {

        if (driver != null) {

            driver.quit();

            System.out.println("Browser closed");
        }
    }

    // =========================
    // SCREENSHOT METHOD
    // =========================

    public String captureScreen(String testName)
            throws IOException {

        String timeStamp =
                new SimpleDateFormat("yyyyMMddHHmmss")
                        .format(new Date());

        String targetFilePath =
                System.getProperty("user.dir")
                        + File.separator
                        + "screenshot"
                        + File.separator
                        + testName + "_"
                        + timeStamp
                        + ".png";

        TakesScreenshot ts = (TakesScreenshot) driver;

        File sourceFile =
                ts.getScreenshotAs(OutputType.FILE);

        File targetFile =
                new File(targetFilePath);

        FileUtils.copyFile(sourceFile, targetFile);

        return targetFilePath;
    }
}