package tests.highPriority;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.StorePage;
import tests.BaseTest;


public class StoreCoinsStoreTemplatesTest extends BaseTest {


    @Test   // Check if Store edit page is opened,
    public void testStoreEditPageOpens() {
        StorePage storePage = new StorePage(driver);
        storePage.openStoreMenu();
        storePage.openCoinsStoreTemplates();
        Assert.assertTrue(storePage.isStoreEditPageOpens(), "StorePage edit page is not opened");
        log.info("TEST PASS >>> Store edit page opens");
    }

    @Test (dependsOnMethods = "testStoreEditPageOpens")  // change some parameters and save – check if they were saved
    public void testSavingChangedParameters() {
        StorePage storePage = new StorePage(driver);
        storePage.disableDefaultButton();
        storePage.selectDevice("Android");
        storePage.selectStoreDefaultItem("350,000 coins - 39.99$");
        storePage.selectSegments("Level from 0 to 9");
        storePage.enableIsSaleButton();
        storePage.clickSaveButtonCoinsTemplate();
        storePage.openCoinsStoreTemplates();
        Assert.assertTrue(storePage.isAllChangesSaved(), "Some changes doesn't saved");
        log.info("TEST PASS >>> Parameters Changed -> Saved -> Checked if they were saved");
    }

    @Test (dependsOnMethods = "testSavingChangedParameters") // turn back changes from previous test
    public void testTurnBackChanges() {
        StorePage storePage = new StorePage(driver);
        storePage.enableDefaultButton();
        storePage.selectDevice("Web");
        storePage.selectStoreDefaultItem("72,500 coins - 9.99$");
        storePage.clearSegments();
        storePage.disableIsSaleButton();
        storePage.clickSaveButtonCoinsTemplate();
        storePage.openCoinsStoreTemplates();
        Assert.assertTrue(storePage.isAllChangesTurnedBack(), "Some changes doesn't saved");
        log.info("TEST PASS >>> Changes are turned back");
    }

}
