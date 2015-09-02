package tests.highPriority;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ItemsPage;
import tests.BaseTest;

public class ItemsTest extends BaseTest{

    @Test  // change some parameters and save – check if they were saved
    public void testSavingChangedParameters(){
        ItemsPage itemsPage = new ItemsPage(driver);
        itemsPage.clickItemsMenu();
        itemsPage.selectVirtualItemCategory("Gifts");
        itemsPage.chooseFirstItemFromTable();  // after first click filter updates
        itemsPage.chooseFirstItemFromTable();  // for some reason we need to click to this element for second time
        itemsPage.disableCheckBoxBotCanUse();
        itemsPage.setPositionNumber("99");
        itemsPage.setLevelFromNumber("5");
        itemsPage.setAmountNumber("100");
        itemsPage.setXpNumber("200");
        itemsPage.clickButtonSave();
//        driver.navigate().back(); // driver.executeScript("window.history.go(-1)");
        itemsPage.chooseFirstItemFromTable();
        Assert.assertTrue(itemsPage.isAllChangesSaved(), "Some changes doesn't saved");
        log.info("TEST PASS >>> Parameters Changed -> Saved -> Checked if they were saved");
    }

    @Test (dependsOnMethods = "testSavingChangedParameters") // turn back changes from previous test
    public void testTurnBackChanges() {
        ItemsPage itemsPage = new ItemsPage(driver);
        itemsPage.enableCheckBoxBotCanUse();
        itemsPage.setPositionNumber("2");
        itemsPage.setLevelFromNumber("0");
        itemsPage.setAmountNumber("20");
        itemsPage.setXpNumber("5");
        itemsPage.clickButtonSave();
        itemsPage.chooseFirstItemFromTable();
        Assert.assertTrue(itemsPage.isAllChangesTurnedBack(), "Some changes doesn't saved");
        log.info("TEST PASS >>> Changes are turned back");
    }
}
