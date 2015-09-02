package pages;


import org.apache.log4j.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Date;
import java.util.List;

public class ItemsPage extends BasePage{

    public ItemsPage(WebDriver driver){
        super(driver);
    }

        private static By itemsMenu = By.xpath("(.//*[@href='/BackgammonItems'])[1]");
        private static By firstItemFromTable = By.xpath("(.//*/a[@class='tablectrl_small bDefault' and @title='Edit'])[1]");
        private static By button_Back = By.xpath(".//*[@class='btn-cancel-edit btn-back buttonH bBlack']");
        private static By checkBox_BotCanUse = By.xpath("(.//*[@id='virtualItemFrm']//div[@class='ibutton-container']/input)[2]");
        private static By dropDown_ItemCategory = By.xpath(".//*[@name='virtual-item-category']");
        private static By dropDown_VirtualItemCategory = By.xpath(".//*[contains(@class, 'select_filter')] "); // /option[contains(text(),'Gifts')]
        private static By field_Position = By.xpath(".//*/input[@name='virtual-item-pos']");
        private static By field_LevelFrom = By.xpath(".//*/input[@name='virtual-item-level-from']");
        private static By field_Amount = By.xpath(".//*/input[@name='virtual-item-amount']");
        private static By field_Xp = By.xpath(".//*/input[@name='virtual-item-xp']");
        private static By button_Save = By.xpath(".//*[@name='btn-virtual-item-edit-save']");

    //    private static By  = By.xpath("");
    //    private static By  = By.xpath("");


    public void clickItemsMenu(){
        elements.clickButton(itemsMenu);
        log.info("Click_Menu -> 'Items'");
    }

    public void selectVirtualItemCategory(final String item){
//        try {
//            Thread.sleep(8000);
//            log.info(" ---> finish sleep 8sec");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        elements.selectItemFromDropDownListByVisibleText(dropDown_VirtualItemCategory, item);
        log.info("Select '" +item+ "' from dropdown list by Category");

    }

    public void chooseFirstItemFromTable(){
//        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//*/tbody")));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*/tbody")));
        wait.until(ExpectedConditions.elementToBeClickable(firstItemFromTable));
//        try {
//            Thread.sleep(8000);
//            log.info(" ---> finish sleep 8sec");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        wait span Gifts ,ill be = all gifts in table
        elements.clickButton(firstItemFromTable);
        log.info("Click_Button -> 'Edit'");
    }

    public void setPositionNumber(final String number){
        elements.inputText(field_Position, number);
        log.info("Input"+ number +"in field 'Position'");
    }

    public void setLevelFromNumber(final String number){
        elements.inputText(field_LevelFrom, number);
        log.info("Input"+ number +"in field 'Level From'");
    }

    public void setAmountNumber(final String number){
        elements.inputText(field_Amount, number);
        log.info("Input"+ number +"in field 'Amount'");
    }

    public void setXpNumber(final String number){
        elements.inputText(field_Xp, number);
        log.info("Input"+ number +"in field 'Xp'");
    }

    public void clickBackToAllItems(){
        elements.clickButton(button_Back);
        log.info("Click_Button -> 'Back'");
    }

    public void clickButtonSave(){
        elements.clickButton(button_Save);
        log.info("Click_Button -> 'Save'");
    }

    public void enableCheckBoxBotCanUse() {
        String isDisabled = (driver.findElement(checkBox_BotCanUse).getAttribute("checked"));
        if (isDisabled==null || !isDisabled.isEmpty()){
            elements.clickButton(checkBox_BotCanUse);
            log.info("checkBox_BotCanUse : Enabled");
        }else{
            log.info("checkBox_BotCanUse - was already Enabled");
        }
    }

    public void disableCheckBoxBotCanUse() {
        String isDisabled = (driver.findElement(checkBox_BotCanUse).getAttribute("checked"));
        if (isDisabled==null || isDisabled.isEmpty()){   // isEmpty() content("checked") isEmpty()  isDisabled==null ||
            log.info("checkBox_BotCanUse - was already Disabled");
        }else{
            elements.clickButton(checkBox_BotCanUse);
            log.info("checkBox_BotCanUse : Disabled");
        }
    }

    public boolean isAllChangesSaved() {
        if (driver.findElement(checkBox_BotCanUse).getAttribute("checked")==null)
        { log.info("Button_'Default'- was Disabled");}
        if (elements.isTextPresent("99"))
        {  log.info("Displayed - '99'");}
        if (elements.isTextPresent("5"))
        {   log.info("Displayed - '5'");}
        if (elements.isTextPresent("100"))
        { log.info("Displayed - '100'");}
        if (elements.isTextPresent("200"))
        { log.info("Displayed - '200'");
            return true;
        } else
            return false;
    }

    public boolean isAllChangesTurnedBack() {
        if (driver.findElement(checkBox_BotCanUse).getAttribute("checked")!=null)
        {  log.info("checkBox BotCanUse - was Enabled");}
        if (elements.isTextPresent("2"))
        {  log.info("Displayed - '2'");}
        if (elements.isTextPresent("0"))
        {   log.info("Displayed - '0'");}
        if (elements.isTextPresent("20"))
        { log.info("Displayed - '20'");}
        if (elements.isTextPresent("5"))
        { log.info("Displayed - '5'");
            return true;
        }
        else
            return false;
    }

}
