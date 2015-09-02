package tests.highPriority;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.LocationsPage;
import tests.BaseTest;


public class LocationsTest extends BaseTest {

    @Test
    public void movementBetweenLocations(){
        LocationsPage locationsPage = new LocationsPage(driver);
        locationsPage.clickMenuLocations();
        locationsPage.clickOnLocationsInTableRandom();
        locationsPage.clickBackButton();
        locationsPage.clickOnLocationsInTableRandom();
        locationsPage.clickBackButton();
        locationsPage.clickOnLocationsInTableRandom();
        locationsPage.clickBackButton();
        log.info("TEST PASS >> successfully move between Locations");
    }

    @Test (dependsOnMethods = "movementBetweenLocations")
    public void changeTipsParameter(){
        LocationsPage locationsPage = new LocationsPage(driver);
        locationsPage.clickFirstLocationInTable();
        locationsPage.inputValueInTipField("6");
        locationsPage.clickSaveButton();
        locationsPage.clickOkButton();
        elements.checkForErrorsFromLogs();
        log.info("TEST PASS >> change -'Tip' parameter");
    }

    @Test (dependsOnMethods = "changeTipsParameter")
    public void turnBackChanges(){
        LocationsPage locationsPage = new LocationsPage(driver);
        locationsPage.clickFirstLocationInTable();
        locationsPage.inputValueInTipField("5");
        locationsPage.clickSaveButton();
        locationsPage.clickOkButton();
        log.info("TEST PASS >> turn back -'Tip' parameter");
    }
}
