package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CouponsPage extends BasePage {

    public CouponsPage(WebDriver driver) {
        super(driver);
    }

    private static By menue_coupons = By.xpath("(.//*[@href='/BackgammonCoupons'])[1]");
    private static By button_add_coupon = By.xpath(".//*[@id='add-coupon-btn']");
    private static By checkBox_CouponEnabled = By.xpath(".//*[@id='coupon-widget']//div[@class='ibutton-container']/input");
    private static By button_Save = By.xpath(".//*[@value='Save' and @name='btn-edit-save']");

    //    private static By  = By.xpath("");
    //    private static By  = By.xpath("");


    public void clickCouponsMenu() {
        elements.clickButton(menue_coupons);
        log.info("Click_Menu -> 'CouponsPage' ");
    }

    public void clickAddCoupon() {
        elements.clickButton(button_add_coupon);
        log.info("Click -> 'Add Coupon' ");
    }

    public void clickSaveButton() {
        elements.clickButton(button_Save);
        log.info("Click -> 'Save' ");
    }


    public void enableCheckBoxCouponEnabled() {
        String isDisabled = (driver.findElement(checkBox_CouponEnabled).getAttribute("checked"));
        if (isDisabled == null || !isDisabled.isEmpty()) {
            elements.clickButton(checkBox_CouponEnabled);
            log.info("checkBox_CouponEnabled : Enabled");
        } else {
            log.info("checkBox_CouponEnabled - was already Enabled");
        }
    }

    public void disableCheckBoxCouponEnabled() {
        String isDisabled = (driver.findElement(checkBox_CouponEnabled).getAttribute("checked"));
        if (isDisabled == null || isDisabled.isEmpty()) {   // isEmpty() content("checked") isEmpty()  isDisabled==null ||
            log.info("checkBox_CouponEnabled - was already Disabled");
        } else {
            elements.clickButton(checkBox_CouponEnabled);
            log.info("checkBox_CouponEnabled : Disabled");
        }
    }


}