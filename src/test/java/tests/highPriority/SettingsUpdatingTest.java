package tests.highPriority;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SettingsPage;
import tests.BaseTest;

public class SettingsUpdatingTest extends BaseTest{

    @Test   // Change BG_Android_Version setting -> save changes -> Reload page and check changes.
    public void testSaveChangesAndroidVersion (){
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.clickTab1();
        settingsPage.clickSettingsMenu();
        settingsPage.clickBgAndroidVersion();
        settingsPage.inputValue("1.2.0");
        settingsPage.clickButtonSubmit();
        elements.waitForAjaxOnPage();
        elements.refreshPage();
        Assert.assertTrue(settingsPage.isNewAndroidVersionPresent(), "Android version doesn't changes");
        log.info("TEST PASS >> Android Version changed to 1.2.0");
    }

    @Test (dependsOnMethods = "testSaveChangesAndroidVersion")  // Turn the changes back.
    public void testTurnBackChangesAndroidVersion (){
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.clickBgAndroidVersion();
        settingsPage.inputValue("1.0.0");
        settingsPage.clickButtonSubmit();
        elements.waitForAjaxOnPage();
        Assert.assertTrue(settingsPage.isOldAndroidVersionPresent(), "Android version doesn't changes");
        log.info("TEST PASS >> Android Version turned back to 1.0.0");
    }
}
