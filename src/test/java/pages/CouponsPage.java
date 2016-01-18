package pages;

import libsmy.MySQLconnector;
import libsmy.WebElements;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.python.antlr.base.stmt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CouponsPage extends BasePage {

    private int countOfCouponsInTableBeforeCreateCoupon;
    public CouponsPage(WebDriver driver) {
        super(driver);
    }

    private static By menue_coupons = By.xpath("(.//*[@href='/BackgammonCoupons'])[1]");
    private static By button_add_coupon = By.xpath(".//*[@id='add-coupon-btn']");
    private static By checkBox_CouponEnabled = By.xpath(".//*[@id='coupon-widget']//div[@class='ibutton-container']/input");
    private static By button_Save = By.xpath(".//*[@value='Save' and @name='btn-edit-save']");
    private static By dropDown_ShowEntries = By.xpath(".//*[@id='coupons-table_length']//select[@name='coupons-table_length']");

    //    private static By  = By.xpath("");


    public void clickCouponsMenu() {
        elements.clickButton(menue_coupons);
        log.info("Click_Menu -> 'CouponsPage' ");
    }

    public void showAllCoupons(final String itemValue){
        elements.selectItemFromDropDownListByValue(dropDown_ShowEntries, itemValue);
        log.info("Show All from DropDown list");
        List<WebElement> listOfLocatedCouponsInTable = driver.findElements(By.xpath(".//*[@id='coupons-table']/tbody//td[3]"));
        this.countOfCouponsInTableBeforeCreateCoupon = listOfLocatedCouponsInTable.size();
        log.info("Count of Coupons in Table Before Add " +countOfCouponsInTableBeforeCreateCoupon);
    }

    public void clickAddCoupon() {
        elements.clickButton(button_add_coupon);
        log.info("Click -> 'Add Coupon' ");
    }

    public void clickSaveButton() {
        elements.clickButton(button_Save);
        log.info("Click -> 'Save' ");
        elements.waitForAjaxOnPage();
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

    public boolean isCreatedCouponPresent(final String couponName, final String claimedTimes) {
        try {
            driver.findElement(By.xpath(".//*[@id='coupons-table']//tr[1]/td[4][text()='" + couponName + "']"));
            driver.findElement(By.xpath(".//*[@id='coupons-table']//tr[1]/td[7][text()='" + claimedTimes + "']"));
            log.info(couponName + " is Presented");

            List<WebElement> listOfLocatedCouponsInTableAfter = driver.findElements(By.xpath(".//*[@id='coupons-table']/tbody//td[3]"));
            int countOfCouponsInTableAfterCreateCoupon = listOfLocatedCouponsInTableAfter.size();
               if (countOfCouponsInTableAfterCreateCoupon != countOfCouponsInTableBeforeCreateCoupon){
                log.info("Count of Coupons in Table After Add NewOne = " + countOfCouponsInTableAfterCreateCoupon + " >> Coupon created Successfully");
                return true;
                 } else {
                   log.info("Coupon is not present");
                   return false;
                     }
        } catch (NoSuchElementException exception) {
            log.info("Coupon is not present");
            log.info("Something goes wrong!!! number of coupons tha same as was before creating new coupon");
            return false;
            }
    }

// How to compare one element from all list
   /* public void isCreatedCouponPresent2(){
        List<WebElement> located_elements_table = driver.findElements(By.xpath("./*//*[@id='coupons-table']//tr/td[3]"));

    }*/

    public void deleteCreatedCouponFromDB() throws SQLException, ClassNotFoundException {
        MySQLconnector mySQLconnector = new MySQLconnector();
        mySQLconnector.dbConnection();
        //Query to Execute
        String query = "delete from bg_coupons order by create_date desc limit 1;";
        // Execute the SQL Query. Store results in ResultSet
        mySQLconnector.stmt.execute(query);
        log.info("Record is deleted from >> bg_coupons << table!");
        // closing DB Connection
        mySQLconnector.connect.close();
    }

}