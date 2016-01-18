package tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import libsmy.SendMail;
import libsmy.TakeScreenshot;
import libsmy.TestListener;
import libsmy.WebElements;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.testng.ITestResult;

import javax.imageio.ImageIO;

import static com.relevantcodes.extentreports.ExtentReports.*;

//import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;

public class BaseTest {

    protected WebDriver driver;
    protected WebElements elements;
    protected WebElement element;
    protected Logger log;
    protected LoginPage loginPage;
    public TestListener testListener;
    public ExtentReports report;
    public ExtentTest logger;


    @BeforeClass
    public void Setup()  {
//        System.setProperty("webdriver.firefox.bin","c:\\Users\\matvienkod\\Mozilla Firefox Portable 27\\FirefoxPortable.exe");
//        System.setProperty("webdriver.chrome.driver", "c:\\Users\\matvienkod\\workspace\\chromedriver_win32 (2.14).exe");
//        driver = new ChromeDriver();
      /*  FirefoxProfile ffProfile = new FirefoxProfile();
        try {
            JavaScriptError.addExtension(ffProfile);
        } catch (IOException e) {
            log.info("JavaScriptError addExtension");
        }*/
//        DesiredCapabilities caps = DesiredCapabilities.firefox();
//        LoggingPreferences logPrefs = new LoggingPreferences();
//        logPrefs.enable(LogType.BROWSER, java.util.logging.Level.ALL);
//        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

//        driver = new FirefoxDriver(ffProfile);

//        String date = new SimpleDateFormat("yyyyMMddhhmm'.html'").format(new Date());
//        report = new ExtentReports("/home/dima/Downloads/-autotest-data/ExtentReports/AutomationTestReport"+date, true);
//        report.loadConfig(new File("/home/dima/IdeaProjects/stage-CMS/src/main/resources/extent-config.xml"));
//        logger = report.startTest("Test Name", "Sample description");

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

//        logger.log(LogStatus.INFO, "Browser Started");

        testListener = new TestListener();
        elements = new WebElements(driver);
        log = Logger.getLogger(this.getClass());

        String stageUrlParam = System.getProperty("stage_url_param");
        elements.openURL(stageUrlParam);
//        logger.log(LogStatus.INFO, " "+ urlParam+ "CMS is up and running");

// pre-condition to tests. Login user and open certain game.
        loginPage = new LoginPage(driver);
        loginPage.userLogIn("dimam", "3AcUS9uN");
        Assert.assertTrue(loginPage.isUserLoggedIn(), "LoginPage user doesn't login");
        loginPage.backgammonBtn();
// TODO: How to login user by set data into http request
    }


    @AfterMethod
    public void takeScreenShotOfPageOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println("testResult.getStatus = " + testResult.getStatus() + " -> failure");
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String randomUUID = UUID.randomUUID().toString();
            String randomHEX = Long.toHexString(Double.doubleToLongBits(Math.random()));

            String filepath = ("/home/dima/Downloads/-autotest-data/screenshots_on_FAILURE/"
                    + driver.getTitle() + testResult.getName() + randomHEX + ".jpg");
            FileUtils.copyFile(scrFile, new File(filepath));

//            String image = logger.addScreenCapture(filepath);
//            logger.log(LogStatus.FAIL, "test fail", image);
        }
    }
/*    public void analyzeLog() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            //do something useful with the data
        }
    }*/

    @AfterClass
    public void TearDown() {
        loginPage.userLogOut();
// TODO: how delete named cookies ?
//      driver.manage().deleteCookie();

//        report.endTest(logger);
//        report.flush();

        driver.close();  // Close the new window
        if (driver != null) {
            driver.quit();
        }  // Close driver session
    }

    // problem because of trying to send report while file isn't created yet
//    @AfterClass (dependsOnMethods ="TearDown")
//    public void SendMailReport() {
//            try {
//                SendMail.execute("index.html");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }



}

