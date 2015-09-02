package tests.highPriority;


import org.testng.annotations.Test;
import pages.StorePage;
import tests.BaseTest;

public class StoreBundleItemTest extends BaseTest{

    @Test   //  create bundle item for Store with iTunes_id that already exists
    public void testCreateBundleWithITunesIdThatAlreadyExist(){
        StorePage storePage = new StorePage(driver);
        storePage.openStoreMenu();
        storePage.clickTabBundleItems();
        storePage.clickAddBundle();
        storePage.inputTextIntoFieldBundleItunesId("s_BGL42");
        storePage.clickSaveBundleItemButton();
        storePage.isErrorMessagePresent();
        log.info("TEST PASS >>> it is not possible to save changes, Error message appears");
    }

    @Test (dependsOnMethods = "testCreateBundleWithITunesIdThatAlreadyExist")  //  create bundle item for Store with GooglePlay_id that already exists
    public void testCreateBundleWithGooglePlayIdThatAlreadyExist(){
        StorePage storePage = new StorePage(driver);
        storePage.openStoreMenu();
        storePage.clickTabBundleItems();
        storePage.clickAddBundle();
        storePage.inputTextIntoFieldBundleGooglePlayId("s_an40");
        storePage.clickSaveBundleItemButton();
        storePage.isErrorMessagePresent();
        log.info("TEST PASS >>> it is not possible to save changes, Error message appears");
    }

    @Test (dependsOnMethods = "testCreateBundleWithGooglePlayIdThatAlreadyExist")  //  disable bundle item that is used in some Store
    public void testDisableBundleUsedInSomeStore(){
        StorePage storePage = new StorePage(driver);
        storePage.openStoreMenu();
        storePage.clickTabBundleItems();
        storePage.clickBundleItem1647();
        storePage.disableBundleItem();
        storePage.clickSaveBundleItemButton();
        storePage.isErrorMessagePresent();
        log.info("TEST PASS >>> it is not possible to save changes, Error message appears");
    }

}


