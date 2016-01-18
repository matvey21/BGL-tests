package tests.highPriority;


import libsmy.WebElements;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.StorePage;
import tests.BaseTest;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

//  BGL-1913  CMS: scheduled sales check start date < end date doesn't work
public class StoreScheduledSalesTest extends BaseTest{


  /*  public WebDriver driver;
    public WebElements elements;
    public Logger log;
    public LoginPage loginPage;

    @BeforeTest
    public void Setup() {
*//*        // http://seleniumeasy.com/selenium-tutorials/how-to-download-a-file-with-webdriver
        FirefoxProfile fxProfile = new FirefoxProfile();
        fxProfile.setPreference("browser.download.folderList", 2); // use the custom folder defined in "browser.download.dir" below
        fxProfile.setPreference("browser.download.manager.showWhenStarting", false);
        fxProfile.setPreference("browser.download.dir","/home/dima/Downloads/-autotest-data");
        fxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
        fxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
        fxProfile.setPreference("browser.helperApps.neverAsk.openFile", "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
        fxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        fxProfile.setPreference("browser.download.manager.focusWhenStarting", false);
        fxProfile.setPreference("browser.download.manager.useWindow", false);
        fxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
        fxProfile.setPreference("browser.download.manager.closeWhenDone", false);
        driver = new FirefoxDriver(fxProfile);*//*

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        elements = new WebElements(driver);
        log = Logger.getLogger(this.getClass());
        loginPage = new LoginPage(driver);

        loginPage.openLogInPage();
        loginPage.userLogIn("dimam", "3AcUS9uN");
        Assert.assertTrue(loginPage.isUserLoggedIn(), "LoginPage user doesn't login");
        loginPage.backgammonBtn();
    }*/

    @Test  // Create scheduler >> Check that start date couldn’t be more than end date (alert)
    public void createWrongScheduler(){
        StorePage storePage = new StorePage(driver);
        storePage.openStoreMenu();
        storePage.openScheduledSalesTab();
        storePage.addScheduler();
        storePage.inputStartTimeDate("Sep 10, 2015 00:00");
        storePage.clickDoneButtonOnCalendar();
        storePage.inputEndTimeDate("Sep 9, 2015 00:00");
        storePage.clickDoneButtonOnCalendar();
        storePage.inputIDs("1,32,33");
        storePage.clickSaveButtonSheduledSales();
        Assert.assertTrue(storePage.isErrorMessagePresent(), "Error message doesn't shows");
        log.info("TEST PASS >> Error message shows");
    }

    @Test(dependsOnMethods = "createWrongScheduler")  // Create scheduler >> Save correct scheduled sale and check the list
    public void createCorrectScheduler(){
        StorePage storePage = new StorePage(driver);
        storePage.inputStartTimeDate("Sep 9, 2015 00:00");
        storePage.clickDoneButtonOnCalendar();
        storePage.inputEndTimeDate("Sep 10, 2015 00:00");
        storePage.clickDoneButtonOnCalendar();
        storePage.clickSaveButtonSheduledSales();
        Assert.assertTrue(storePage.isSchedulerCreated(),"New Scheduler doesn't created");
        log.info("TEST PASS >> Scheduler created");
    }

    @Test (dependsOnMethods = "createCorrectScheduler")  //  Check that report could be exported
    public void exportScheduler() {
        StorePage storePage = new StorePage(driver);
        storePage.exportCreatedScheduler();
        storePage.uploadFile();
        log.info("TEST PASS >> Scheduler Downloaded successfully");
    }

    @Test (dependsOnMethods = "exportScheduler")  //  Delete created report
    public void deleteCreatedScheduler(){
        StorePage storePage = new StorePage(driver);
        storePage.clickRemoveScheduler();
        storePage.clickRemoveYesButtonOnPopup();
        log.info("TEST PASS >> Scheduler removed");
    }

    /*    @AfterTest
    public void TearDown() {
//      CmsPage.userLogOut();  WebDriver driver
//      driver.manage().deleteCookie();
        driver.close();  //Close the new window
        if (driver != null){
            driver.quit();
        }
    }*/
}
