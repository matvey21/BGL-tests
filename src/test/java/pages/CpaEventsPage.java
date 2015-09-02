package pages;


import libsmy.WebElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class CpaEventsPage extends BasePage {

    public CpaEventsPage(WebDriver driver){
        super(driver);
    }

    private static By tab2 = By.xpath(".//*[@href='#sub-menu-tab-2']");
    private static By cpaEvents = By.xpath(".//*[@href='/BackgammonCpaEvents']");
    private static By cpaEventsTab = By.xpath(".//*[@href='#tab-cpa']");
    private static By timeRangeFromDate = By.xpath(".//*[@id='from-date']");
    private static By timeRangeToDate = By.xpath(".//*[@id='to-date']");
    private static By paymentsType = By.xpath(".//*[@class='chzn-single']");
    private static By paymentsTypeAll = By.xpath(".//*[@id='payment-type_chzn_o_0' and contains(text(), '(All)')]");
    private static By applyFilterBtn = By.id("btn-update-cpa-events");

    private static By filter_Product = By.xpath("(.//*[contains(@class, 'select_filter')])[1]");
    private static By filter_Store = By.xpath("(.//*[contains(@class, 'select_filter')])[2]");
    private static By tableCpaEvents = By.xpath(".//*[@id='cpa-events-table']/tbody/tr");
    private static By product_Tokens = By.xpath(".//*[@id='cpa-events-table']//tr/td[2][text()= 'Tokens']");
    private static By store_iTunes = By.xpath(".//*[@id='cpa-events-table']//tr/td[6][text()= 'iTunes']");
    private static By sortByCommission = By.xpath(".//*[@class='center sorting' and contains(text(), 'Commission, $')]");
    private static By commissionIsZero = By.xpath(".//*[@id='cpa-events-table']//tr/td[5][text()= '0']");
    private static By showEntries = By.xpath(".//*[@name='cpa-events-table_length']");

    private static By createReportButton = By.xpath(".//*[@id='btn-create-report']");



    public void clickTab2 (){
        elements.clickButton(tab2);
        log.info("Click_Tab -> 'Tab2'");
    }

    public void clickCPAevents (){
        elements.clickButton(cpaEvents);
        log.info("Click_Menu -> 'CPA Events'");
    }

    public void setTimeRangeFromDate (final String fromDate) {
        elements.inputText(timeRangeFromDate, fromDate);
        log.info("Set Time Range From Date: " +fromDate);
    }

    public void setTimeRangeToDate (final String toDate) {
        elements.inputText(timeRangeToDate, toDate);
        log.info("Set Time Range To Date: " + toDate);
    }

    public void choosePaymentsTypeAll () {
        elements.clickButton(paymentsType);
        elements.clickButton(paymentsTypeAll);
        log.info("Select -> Payments Type: '(All)'");
    }

//    FIXME: doesn't work select.selectByVisibleText(itemName)
//    public void choosePaymentsTypeAll(){
//        elements.selectItemFromDropDownListByVisibleText(paymentsType, "(All)");
//    }

    public void clickApplyFilterButton () {
        elements.clickButton(applyFilterBtn);
        log.info("Click_Button -> 'Apply filter'");
    }

    public void turnCommissionAsc () {
//    if (!driver.findElement(sortByCommission).isSelected())
//        log.info("Commission was already sorted by Asc");
//     else
        elements.clickButton(sortByCommission);
        log.info("Commission sorted by Asc");
    }

    public boolean isCommissionNotZero() {
        if (elements.isElementPresent(commissionIsZero)) {
            log.info("Commission '0' in cpa-events-table table present");
            return false;
        } else {
            log.info("commission is not '0'");
            return true;
        }
    }

    public void showAllEntries(){
        elements.selectItemFromDropDownListByVisibleText(showEntries, "5000");
        log.info("Select -> Show Entries 5000");
    }

    public void filterByProduct(final String product){
        elements.selectItemFromDropDownListByVisibleText(filter_Product, product);
        elements.clickButton(cpaEventsTab);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            log.info("Thread sleep Exception");
//        }
//        elements.waitForPageLoaded(driver);
//        elements.waitForAjaxOnPage();
//        elements.waitForLoad(driver);
//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(product_Tokens));
        log.info("Select_Filter -> 'Tokens'");
    }

    public boolean isChosenProductPresent() {
//       FIXME: List of elements tableCpaEvents counts before filterByProduct. Helps (elements.clickButton(cpaEventsTab);
        List<WebElement> located_elements_table = driver.findElements(tableCpaEvents);
        int countRowsInTable = located_elements_table.size();

        List<WebElement> located_elements_products = driver.findElements(product_Tokens);
        int countProducts = located_elements_products.size();

        if (countProducts == countRowsInTable) {
            log.info("Product_Tokens filtering correctly");
            return true;
        } else {
            log.info("Count of filtering products doesn't match count of rows in table");
            return false;
        }
    }

    public void filterByStore(final String store){
        elements.selectItemFromDropDownListByVisibleText(filter_Store, store);
        elements.clickButton(cpaEventsTab);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(store_iTunes));
        log.info("Select_Filter -> 'iTunes'");
    }

    public boolean isChosenStorePresent() {
        List<WebElement> located_elements_table = driver.findElements(tableCpaEvents);
        int countRowsInTable = located_elements_table.size();

        List<WebElement> located_elements_products = driver.findElements(store_iTunes);
        int countProducts = located_elements_products.size();

        if (countProducts == countRowsInTable) {
            log.info("Store_iTunes filtering correctly");
            return true;
        } else {
            log.info("Count of filtering products doesn't match count of rows in table");
            return false;
        }
    }

    public void clickCreateReport(){
        elements.clickButton(createReportButton);
        log.info("Click_Button -> 'Create report'");
    }

    public void uploadFile() {
        try {
            Robot rb = new Robot();
            boolean flag = true;
            while (flag) {
                rb.delay(900); // Look on browser side! use delay method. Because Robot make actions before some window opens.
                rb.keyPress(KeyEvent.VK_DOWN); // keyPress will send an event that a key has been pressed down.
                rb.keyRelease(KeyEvent.VK_DOWN); // keyRelease will send the event that the key has been released.
                rb.delay(300);
                rb.keyPress(KeyEvent.VK_ENTER);
                rb.keyRelease(KeyEvent.VK_ENTER);
                flag = false;
                log.info("KeyPress Robot worked properly");
            }
        } catch (Exception ex){
            log.info("KeyPress Robot doesn't work ;(");
        }
    }

}
       /*

    - Check filters by Product and by Store
        выбрать какой-то определенный фильтр
        посмотреть появились ли айтемы с таким же фильтром
        Collect all text on page
        а также проверить нет ли другий айтемов помимо этого.

    --- Create a report (check that all data displayed is in report if possible)
        - check that report creates
        - need to switch to save window
        - click OK
        - open file (*csv) from saved place
        - parse the document
        - check that all data matchs data in CMS
    */