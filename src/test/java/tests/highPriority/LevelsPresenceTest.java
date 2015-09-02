package tests.highPriority;


import org.testng.annotations.Test;
import pages.LevelsPage;
import tests.BaseTest;


public class LevelsPresenceTest extends BaseTest{

    @Test  //  Check the levels presence
    public void testLevelsPresence(){
        LevelsPage levelsPage = new LevelsPage(driver);
        levelsPage.clickLevelsMenu();
        levelsPage.selectEntriesInDropDownList();
        elements.waitForPageLoaded(driver);
        levelsPage.clickSearchFieldToProvokeElementsShown();
        levelsPage.isAllLevelsPresence();
        log.info("TEST PASS >> levels presence");
    }


}
