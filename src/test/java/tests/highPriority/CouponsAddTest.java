package tests.highPriority;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CouponsPage;
import tests.BaseTest;

import java.sql.SQLException;

public class CouponsAddTest extends BaseTest{

    @Test
    public void addCoupon(){
        CouponsPage couponsPage = new CouponsPage(driver);
        couponsPage.clickCouponsMenu();
        couponsPage.showAllCoupons("-1");  // All coupons
        couponsPage.clickAddCoupon();
        couponsPage.enableCheckBoxCouponEnabled();
        couponsPage.clickSaveButton();
        Assert.assertTrue(couponsPage.isCreatedCouponPresent("10000 coins", "0"), "Coupon doesn't created");
        log.info("TEST PASS >> Coupon was successfully added");
    }

    @Test (dependsOnMethods = "addCoupon")
    public void checkAndDeleteCouponInDB() throws SQLException, ClassNotFoundException {
        CouponsPage couponsPage = new CouponsPage(driver);
        couponsPage.deleteCreatedCouponFromDB();
        log.info("Created Coupon deleted successfully from DB");
    }

}
