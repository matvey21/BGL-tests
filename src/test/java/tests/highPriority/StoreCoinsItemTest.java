package tests.highPriority;


import org.testng.annotations.Test;
import pages.StorePage;
import tests.BaseTest;

public class StoreCoinsItemTest extends BaseTest{

    @Test   //  create coins item for Store with iTunes_id that already exists
    public void testCreateItemWithITunesIdThatAlreadyExist(){
        StorePage storePage = new StorePage(driver);
        storePage.openStoreMenu();
        storePage.clickTabCoinsItems();
        storePage.clickAddItem();
        storePage.inputTextIntoFieldCoinsItunesId("s_p1.1");
        storePage.clickSaveCoinsItemButton();
        storePage.isErrorMessagePresent();
        log.info("TEST PASS >>> it is not possible to save changes, Error message appears");
    }

    @Test (dependsOnMethods = "testCreateItemWithITunesIdThatAlreadyExist")  //  create coins item for Store with GooglePlay_id that already exists
    public void testCreateItemWithGooglePlayIdThatAlreadyExist(){
        StorePage storePage = new StorePage(driver);
        storePage.openStoreMenu();
        storePage.clickTabCoinsItems();
        storePage.clickAddItem();
        storePage.inputTextIntoFieldCoinsGooglePlayId("s_an1");
        storePage.clickSaveCoinsItemButton();
        storePage.isErrorMessagePresent();
        log.info("TEST PASS >>> it is not possible to save changes, Error message appears");
    }

    @Test (dependsOnMethods = "testCreateItemWithGooglePlayIdThatAlreadyExist")  //  disable coins item that is used in some Store
    public void testDisableItemUsedInSomeStore(){
        StorePage storePage = new StorePage(driver);
        storePage.openStoreMenu();
        storePage.clickTabCoinsItems();
        storePage.clickCoinsItem1636();
        storePage.disableCoinsItem();
        storePage.clickSaveCoinsItemButton();
        storePage.isErrorMessagePresent();
        log.info("TEST PASS >>> it is not possible to save changes, Error message appears");
    }

}
