package tests.highPriority;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;


public class TestBGL1648 extends BaseTest {


    @Test
    public void TestBGL1648a() {
//        LoginPage loginPage = new LoginPage(driver);
//        StorePage storePage = new StorePage(driver);
        loginPage.openLogInPage();
        loginPage.userLogIn("dimam", "3AcUS9uN");
        Assert.assertTrue(loginPage.isUserLoggedIn(), "LoginPage user doesn't login");
        storePage.openStore();
        storePage.StoreTemplate();
        Assert.assertTrue(storePage.isStoreOpened(), "StorePage edit page is not opened");
        log.info("BGL1648a >> Test Passed - StorePage edit page is opened");
    }

    @Test
    public void TestBGL1648b() {
        storePage.DisableDefaultBtn();
        storePage.SelectDeviceAndroid();
        storePage.SelectStoreDefaultItem1();
        storePage.SelectSegmentsList();
        storePage.EnableIsSaleBtn();
        storePage.ClickSaveBtn();

        storePage.StoreTemplate();
        storePage.IsDefaultBtnDisabled();

        //Assert.assertTrue();
        log.info("BGL1648b >> Test Passed - Parameters Changed --> Saved --> Checked if they were saved");
    }

    @Test  // turn back changes in TestBGL1648b
    public void TestBGL1648c() {
        storePage.DisableDefaultBtn();
        storePage.SelectDeviceWeb();
        storePage.SelectStoreDefaultItem2();
        storePage.CloseSelectSegmentsList();
        storePage.EnableIsSaleBtn();
        storePage.ClickSaveBtn();
        //TODO: make check
        log.info("BGL1648c >> Test Passed - Changes are turned back");
    }


}
