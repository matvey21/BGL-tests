package tests.highPriority;


import libsmy.WebElements;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;


public class TestBGL2053 extends BaseTest{

    private static WebDriver driver;
    private static WebElements elements;
    private static Logger log = Logger.getLogger(TestBGL2053.class);

    @Test
    public void  TestBGL2053a() {

        loginPage.openLogInPage();
        loginPage.userLogIn("dimam", "3AcUS9uN");
        Assert.assertTrue(loginPage.isUserLoggedIn(), "LoginPage user doesn't login");
        storePage.backgammonBtn();
        cpaeventsPage.clickTab2();
        cpaeventsPage.clickCPAevents();
      driver.findElement(By.cssSelector("#from-date")).click();
//        elements.clickButton(By.id(".//*[@id='from-date']"));
//        elements.clearTextField(By.id(".//*[@id='from-date']"));
//
//        cpaeventsPage.setTimeRangeFromDate("Aug 1, 2015");
//        cpaeventsPage.setTimeRangeToDate("Sep 1, 2015");
        cpaeventsPage.clickPaymentsType();
        cpaeventsPage.clickPaymentsType_All();
        cpaeventsPage.clickApplyFilterButton();
        log.info("method works");
    }
}
