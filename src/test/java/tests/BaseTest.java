package tests;


import com.sun.xml.internal.bind.v2.TODO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.apache.log4j.Logger;
import pages.CPAeventsPage;
import pages.LoginPage;
import pages.StorePage;
import tests.highPriority.TestBGL1648;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    public Logger log;
    public LoginPage loginPage;
    public StorePage storePage;
    public CPAeventsPage cpaeventsPage;


    @BeforeTest
    public void Setup() {
//        System.setProperty("webdriver.firefox.bin","c:\\Users\\matvienkod\\Mozilla Firefox Portable 27\\FirefoxPortable.exe");
        driver = new FirefoxDriver();
        log = Logger.getLogger(BaseTest.class);
        storePage = new StorePage(driver);
        loginPage = new LoginPage(driver);
        cpaeventsPage = new CPAeventsPage(driver);
//        System.setProperty("webdriver.chrome.driver", "c:\\Users\\matvienkod\\workspace\\chromedriver_win32 (2.14).exe");
//        driver = new ChromeDriver();
//		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void TearDown() {
//      CmsPage.userLogOut();  WebDriver driver
// TODO: how delete named cookies ?
//      driver.manage().deleteCookie();
        driver.close();  //Close the new window
        driver.quit();
    }

//    @AfterSuite
//    public void tearDown() {
//        driver.quit();
//    }
}
