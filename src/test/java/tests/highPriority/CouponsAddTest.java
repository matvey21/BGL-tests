package tests.highPriority;


import org.testng.annotations.Test;
import pages.CouponsPage;
import tests.BaseTest;

public class CouponsAddTest extends BaseTest{

    @Test
    public void addCoupon(){
        CouponsPage couponsPage = new CouponsPage(driver);
        couponsPage.clickCouponsMenu();
        couponsPage.clickAddCoupon();
        couponsPage.clickSaveButton();
        
    }


}
