package tests.highPriority;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CpaEventsPage;
import tests.BaseTest;


public class CpaEventsTest extends BaseTest{

    private static Logger log = Logger.getLogger(CpaEventsTest.class);

    @Test   //  Apply some filter (time range) use Payment type “All”, check what was filtered: (1) Check that commission is not 0 (2)Check filters by Product and by Store
    public void  testFiltersAndDataCheck() {
        CpaEventsPage cpaEventsPage = new CpaEventsPage(driver);
        cpaEventsPage.clickTab2();
        cpaEventsPage.clickCPAevents();
        cpaEventsPage.setTimeRangeFromDate("Aug 1, 2015");
//        cpaEventsPage.setTimeRangeToDate("Sep 1, 2015"); // if comment line then take current date on moment when test runs
        cpaEventsPage.choosePaymentsTypeAll();
        cpaEventsPage.clickApplyFilterButton();
        cpaEventsPage.turnCommissionAsc();
        Assert.assertTrue(cpaEventsPage.isCommissionNotZero(), "Commission is zero");

        cpaEventsPage.showAllEntries();
        cpaEventsPage.filterByProduct("Tokens");
        Assert.assertTrue(cpaEventsPage.isChosenProductPresent(), "Chosen product is not filtering properly");
        cpaEventsPage.filterByStore("iTunes");
        Assert.assertTrue(cpaEventsPage.isChosenStorePresent(), "Chosen store is not filtering properly");
        log.info("TEST PASS >> Filters work correctly");
    }

    @Test (dependsOnMethods = "testFiltersAndDataCheck")
    public void  testCreateReport() {
        CpaEventsPage cpaEventsPage = new CpaEventsPage(driver);
        cpaEventsPage.clickCreateReport();
        cpaEventsPage.uploadFile();
        log.info("File uploaded Successfully");
    }

}
