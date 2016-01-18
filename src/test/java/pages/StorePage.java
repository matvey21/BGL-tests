package pages;

import java.awt.*;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.Iterator;
import java.util.Set;


public class StorePage extends BasePage {

    public StorePage(WebDriver driver) {
        super(driver);
    }

    private static By menuStore = By.xpath("(.//*[@href='/BackgammonStore'])[1]");

//        coinsStoreTemplateTab LOCATORS
    private static By coinsStoreTemplate = By.xpath(".//*[@id='store-packages-table']//tbody/tr[1]/td[2]");
    private static By defaultButton = By.xpath("(.//*[@id='coins-store-template']//div[@class='ibutton-container']/input)[2]");
    private static By enabledButton = By.xpath("(.//*[@class='ibutton-handle-right'])[1]");
    private static By deviceDropDownList = By.xpath(".//*[@id='uniform-undefined']/select[@name='device-type']");
    private static By storeDefaultItem = By.xpath(".//*[@id='coins-store-template']//select[@name='store-def-item']");
    private static By segmentsList = By.xpath(".//*[@id='segments_chzn']//li[@class='search-field']/input[@value='Store Segments']");
    private static By searchChoiseClose = By.xpath(".//*[@class='search-choice-close']");
    private static By isSaleButton = By.xpath("(.//*[@id='coins-store-template']//div[@class='ibutton-container']/input)[3]");
    private static By coinsStoreSaveButton = By.xpath(".//*[@id='coins-store-template']//input[@name='btn-edit-save']");

//        coinsItemTab LOCATORS             //div[@class='ibutton-container']/input[@checked='checked']
    private static By tab_coinsItem = By.xpath(".//*[@href='#tab-coins-item']");
    private static By add_item = By.xpath(".//*[@id='add-coins-item-btn']");
    private static By field_coinsItunesId = By.xpath(".//input[@name='coins-item-itunes-id']");
    private static By field_coinsGooglePlayId = By.xpath("(.//input[@name='google-play-product-id'])[1]");
    private static By button_coinsSave = By.xpath(".//*[@name='btn-coins-item-edit-save']");
    private static By button_coinsEnable = By.xpath(".//*[@id='coins-item-template']//div[@class='ibutton-container']/input"); //[@checked='checked']
//    private static By button_coinsEnableOff = By.xpath(".//*[@id='coins-item-template']//div[@class='ibutton-container']");
    private static By coinsItem1636 = By.xpath(".//*[@id='coins-items-table']//td/a[text()= '1636']");

//        bundleItemTab LOCATORS
    private static By tab_bundleItem = By.xpath(".//*[@href='#tab-bundle-item']");
    private static By add_bundle = By.xpath(".//*[@id='add-bundle-item-btn']");
    private static By field_bundleItunesId = By.xpath(".//input[@name='bundle-item-itunes-id']");
    private static By field_bundleGooglePlayId = By.xpath("(.//input[@name='google-play-product-id'])[2]");
    private static By button_bundleSave = By.xpath(".//*[@name='btn-bundle-item-edit-save']");
    private static By button_bundleEnable = By.xpath(".//*[@id='bundle-item-template']//div[@class='ibutton-container']/input"); //[@checked='checked']
//    private static By button_bundleEnableOff = By.xpath(".//*[@id='bundle-item-template']//div[@class='ibutton-container']");
private static By bundleItem1647 = By.xpath(".//*[@id='bundle-items-table']//td/a[text()= '1647']");

//        sheduledSalesTab LOCATORS
    private static By sheduledSalesTab = By.xpath(".//*[@href='#tab-scheduled-sales']");
    private static By addSchedulerButton = By.xpath(".//*[@id='add-scheduled-sales-btn']");
    private static By startTimeField = By.xpath(".//*[@id='scheduler-item-starttime']");
    private static By endTimeField = By.xpath(".//*[@id='scheduler-item-endtime']");
    private static By calendarDoneButton = By.xpath(".//*[@type='button' and contains(text(), 'Done')]");
    private static By iDsField = By.xpath(".//*[@name='scheduler-item-ids']");
    private static By sheduledSalesSaveButton = By.xpath(".//*[@name='btn-scheduler-item-edit-save']");
    private static By checkTable = By.xpath(".//*/tbody/tr[1]/td[2][text()='Sep 09, 2015 00:00']");
    private static By actionRemove = By.xpath("(.//*[@class='tablectrl_small bDefault' and @title='Remove'])[1]");
    private static By exportToExcelLink = By.xpath("(.//*/a[text()='Export to Excel'])[1]");
    private static By removeYesButton = By.xpath(".//*[@class='buttonS bGreen' and text()='Yes']");

    private static By errorMessage = By.xpath(".//*[@class='jGrowl-message']/b[contains(text(), 'ERROR')]");


//    coinsStoreTemplateTab METHODS

//  testStoreEditPageOpens  // Check if Store edit page is opened,

    public void openStoreMenu() {
        elements.clickButton(menuStore);
        elements.waitForPageLoaded(driver);
        log.info("Click_Menu -> 'Store'");
    }

    public void openCoinsStoreTemplates() {
        elements.clickLink((coinsStoreTemplate));
        elements.waitForAjaxOnPage();
        log.info("Click -> first template from the table");
    }

    public boolean isStoreEditPageOpens() {
        return elements.isElementPresent(coinsStoreSaveButton);
    }

//     *********** testSavingChangedParameters  // Change some parameters and save – check if they were saved

    public void enableDefaultButton() {
        String isDisabled = (driver.findElement(defaultButton).getAttribute("checked"));
        if (isDisabled==null || !isDisabled.isEmpty()){
            elements.clickButton(defaultButton);
            log.info("Button_'Default' : Enabled");
        }else{
            log.info("Button_'Default' - was already Enabled");
        }
    }

    public void disableDefaultButton() {
        String isDisabled = (driver.findElement(defaultButton).getAttribute("checked"));
        if (isDisabled==null || isDisabled.isEmpty()){   // isEmpty() content("checked") isEmpty()  isDisabled==null ||
            log.info("Button_'Default' - was already Disabled");
        }else{
            elements.clickButton(defaultButton);
            log.info("Button_'Default' : Disabled");
        }
    }

    public void selectDevice(final String device) {
        elements.selectItemFromDropDownListByVisibleText(deviceDropDownList, device);
        log.info("Select_Device -> " +device);
    }

    public void selectStoreDefaultItem(final String item) {
        elements.selectItemFromDropDownListByVisibleText(storeDefaultItem, item);
        log.info("Select Store Default Item -> " +item);
    }


    public void selectSegments(final String segment ) {
        elements.inputSomeTextAndClickEnterKey(segmentsList, segment);
        log.info("Select Segment -> " +segment);
    }

    public void clearSegments() {
        elements.clickButton(searchChoiseClose);
        log.info("Clear Segments");
    }

    public void enableIsSaleButton() {
        String isDisabled = (driver.findElement(isSaleButton).getAttribute("checked"));
        if (isDisabled==null || !isDisabled.isEmpty()){
            elements.clickButton(isSaleButton);
            log.info("Button_'Is Sale' : Enabled");
        }else{
            log.info("Button_'Is Sale' - was already Enabled");
        }
    }

    public void disableIsSaleButton() {
        String isDisabled = (driver.findElement(isSaleButton).getAttribute("checked"));
        if (isDisabled==null || isDisabled.isEmpty()){
            log.info("Button_'Is Sale' - was already Disabled");
        }else{
            elements.clickButton(isSaleButton);
            log.info("Button_'Is Sale' : Disabled");
        }
    }

    public void clickSaveButtonCoinsTemplate() {
        elements.clickButton(coinsStoreSaveButton);
        wait.until(ExpectedConditions.elementToBeClickable(coinsStoreTemplate));
        log.info("Click_Button -> 'Save'");
    }

    public boolean isAllChangesSaved() {
        if (driver.findElement(defaultButton).getAttribute("checked")==null)
        { log.info("Button_'Default'- was Disabled");}
        if (elements.isTextPresent("Android"))
        {  log.info("Displayed - 'Android'");}
        if (elements.isTextPresent("350,000 coins - 39.99$"))
        {   log.info("Displayed - '350,000 coins - 39.99$'");}
        if (elements.isTextPresent("Level from 0 to 9"))
        { log.info("Displayed - 'Level from 0 to 9'");}
        if (driver.findElement(isSaleButton).getAttribute("checked")!=null) {
            log.info("Button_'Is Sale' - was Enabled");
            return true;
        } else
            return false;
    }

    public boolean isAllChangesTurnedBack() {
        if (driver.findElement(defaultButton).getAttribute("checked")!=null)
        {  log.info("Button_'Default'- was Enabled");}
        if (elements.isTextPresent("Web"))
        {   log.info("Displayed - 'Web'");}
        if (elements.isTextPresent("72,500 coins - 9.99$"))
        {  log.info("Displayed - '72,500 coins - 9.99$'");}
        if (!elements.isElementPresent(By.xpath(".//*[@id='segments_chzn']//li/a")))
            log.info("Segments - Cleared");
        if (driver.findElement(isSaleButton).getAttribute("checked")==null)
        { log.info("Button_'Is Sale' - was Disabled");
            return true;
        }
        else
            return false;
    }


//  ***********  coinsItemTab METHODS  ***********

    public void clickTabCoinsItems(){
        elements.clickButton(tab_coinsItem);
        log.info("Click_Tab -> 'Coins items'");
    }

    public void clickAddItem(){
        elements.clickButton(add_item);
        log.info("Click -> 'Add Item'");
    }

    public void inputTextIntoFieldCoinsItunesId (final String id){
        elements.inputText(field_coinsItunesId, id);
        log.info("Inputted_Text -> into field Itunes ID : " +id);
    }

    public void inputTextIntoFieldCoinsGooglePlayId (final String id){
        elements.inputText(field_coinsGooglePlayId, id);
        log.info("Inputted_Text -> into field Google Play ID : " +id);
    }

    public void clickSaveCoinsItemButton () {
        elements.clickButton(button_coinsSave);
        log.info("Click_Button -> Save");
    }

    public void clickCoinsItem1636(){
        elements.clickButton(coinsItem1636);
        log.info("Click -> Coins Item 1636");
    }

    public void disableCoinsItem(){
        String isDisabled = (driver.findElement(button_coinsEnable).getAttribute("checked"));
        if (isDisabled==null || isDisabled.isEmpty()){
            log.info("Button was already Disabled");
        }else{
            elements.clickButton(button_coinsEnable);
            log.info("Button_coins: Disabled");
        }
    }

//  ***********  bundleItemTab METHODS  ***********

    public void clickTabBundleItems(){
        elements.clickButton(tab_bundleItem);
        log.info("Click_Tab -> 'Bundle items'");
    }

    public void clickAddBundle(){
        elements.clickButton(add_bundle);
        log.info("Click -> 'Add Bundle'");
    }

    public void inputTextIntoFieldBundleItunesId (final String id){
        elements.inputText(field_bundleItunesId, id);
        log.info("Inputted_Text -> into field Itunes ID : " + id);
    }

    public void inputTextIntoFieldBundleGooglePlayId (final String id){
        elements.inputText(field_bundleGooglePlayId, id);
        log.info("Inputted_Text -> into field Google Play ID : " +id);
    }

    public void clickSaveBundleItemButton () {
        elements.clickButton(button_bundleSave);
        log.info("Click_Button -> Save");
    }

    public void clickBundleItem1647(){
        elements.clickButton(bundleItem1647);
        log.info("Click -> Coins Item 1647");
    }

    public void disableBundleItem(){
        String isDisabled = (driver.findElement(button_bundleEnable).getAttribute("checked"));
        if (isDisabled==null || isDisabled.isEmpty()){
            log.info("Button was already Disabled");
        }else{
            elements.clickButton(button_bundleEnable);
            log.info("Button_bundle: Disabled");
        }
    }

//  ***********  StoreScheduledSalesTest METHODS  ***********

    public void openScheduledSalesTab() {
        wait.until(ExpectedConditions.presenceOfElementLocated(sheduledSalesTab));
        elements.clickButton(sheduledSalesTab);
        log.info("Click_Tab -> Scheduled Sales");
    }

    public void addScheduler() {
        wait.until(ExpectedConditions.presenceOfElementLocated(addSchedulerButton));
        elements.clickButton(addSchedulerButton);
        log.info("Click_Tab -> Add Scheduler");
    }

    public void inputStartTimeDate(final String startTimeDate) {
        elements.inputText(startTimeField, startTimeDate);
        log.info("Inputted -> Start Time Date '" + startTimeDate + "'");
    }

    public void inputEndTimeDate(final String endTimeDate) {
        elements.inputText(endTimeField, endTimeDate);
        log.info("Inputted -> End Time Date '" + endTimeDate + "'");
    }

    public void clickDoneButtonOnCalendar() {
        wait.until(ExpectedConditions.presenceOfElementLocated(calendarDoneButton));
        elements.clickButton(calendarDoneButton);
        log.info("Click_Button -> Done on Calendar");
    }

    public void inputIDs(final String ids) {
        elements.inputText(iDsField, ids);
        log.info("Inputted -> IDs:(" + ids + ") in the field");
    }

    public void clickSaveButtonSheduledSales() {
        elements.clickButton(sheduledSalesSaveButton);
        log.info("Click_Button -> 'Save'");
    }

    public boolean isErrorMessagePresent() {
        try {
            elements.isElementPresent(errorMessage);
            log.info("Error Message is Present");
            return true;
        } catch (NoSuchElementException exception) {
            log.info("Error Message is NOT Present");
            return false;
        }
    }

    public boolean isSchedulerCreated() {
        try {
            elements.isElementPresent(checkTable);
            log.info("New Created Scheduler Present");
            return true;
        } catch (NoSuchElementException exception) {
            log.info("There is NO Scheduler Present");
            return false;
        }
    }

    public void exportCreatedScheduler() {
        if (elements.isElementPresent(checkTable)) {
            elements.clickButton(exportToExcelLink);
            log.info("Click_Link -> 'export To Excel'");
        } else {
            log.info("There is no Scheduler presented");
        }
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

   /* // Another way how to deal with OS level windows (But works for MS Windows because use *.dll)
      public static void uploadFile(String path, String browser) {
        AutoItX x = new AutoItX();
        if (browser.equalsIgnoreCase("firefox")) {

            if (x.winWaitActive("File Upload", "", 10)) {
                if (x.winExists("File Upload")) {
                    x.sleep(500);
                    x.send(path);
                    x.controlClick("File Upload", "", "Button2");

                }
            }
        }
    }

    public void test() {
        //Click on the Select button of the file upload
        uploadFile("/home/dima/Downloads", "firefox");
    }*/

    public void clickRemoveScheduler() {
        if (elements.isElementPresent(checkTable)) {
            elements.clickButton(actionRemove);
            log.info("Dialog Window is present");
        } else {
            log.info("There is no Scheduler presented");
        }
    }

    public void clickRemoveYesButtonOnPopup() {
        try {
            String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
            String subWindowHandler = null;

            Set<String> handles = driver.getWindowHandles(); // get all window handles
            Iterator<String> iterator = handles.iterator();
            while (iterator.hasNext()){
                subWindowHandler = iterator.next();
            }
            driver.switchTo().window(subWindowHandler); // switch to popup window
            log.info("Dialog Window is opens" + driver.getTitle());
            elements.clickButton(removeYesButton); // perform operations on popup

            driver.switchTo().window(parentWindowHandler);  // switch back to parent window

        } catch (Exception e) { //exception handling
            log.info("Dialog Window is not present");
        }
    }


}