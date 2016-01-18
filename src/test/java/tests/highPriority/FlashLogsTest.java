package tests.highPriority;


import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FlashLogsPage;
import tests.BaseTest;

public class FlashLogsTest extends BaseTest {

    @Test   //  Check that flash logs tab could be opened and filtering
    public void  flashLogsFiltering() {
        FlashLogsPage flashLogsPage = new FlashLogsPage(driver);
//        logger = report.startTest("flashLogsFiltering");
        flashLogsPage.clickTab2();
        flashLogsPage.clickFlashLogs();
        flashLogsPage.choseDevice("4");   // Android
        flashLogsPage.clickSearchButton();
        Assert.assertTrue(flashLogsPage.isFilteringByDevice(), "something goes wrong with filtering");
        flashLogsPage.clickFirstLogFromListIfNotEmpty();
        log.info("TEST PASS >> filter by Device");
//        logger.log(LogStatus.PASS, "Flash logs filtering verified");
    }
}

