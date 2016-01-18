package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UsersPage extends BasePage{

    public UsersPage (WebDriver driver){
        super(driver);
    }

        private static By usersLink = By.xpath("(.//*[@href='/BackgammonUsers'])[1]");
        private static By editFirstUser  = By.xpath("(.//*[@title='Edit'])[1]");

//        Transactions   #########################################
        private static By transactions_Tab = By.xpath(".//*[@id='btn-tab-transactions']");
        private static By addBonus_Button = By.xpath(".//*[@id='b-add-bonus']");
        private static By punish_Button = By.xpath(".//*[@id='b-punish']");
        private static By value_field = By.xpath(".//*[@id='coins-transaction-sum']");
        private static By notes_field = By.xpath(".//*[@id='coins-transaction-notes']");
        private static By submit_Button = By.xpath(".//*[@class='ui-dialog-buttonset']/button[contains(text(), 'Submit')]");
        private static By cancel_Button = By.xpath(".//*[@class='ui-dialog-buttonset']/button[contains(text(), 'Cancel')]");
        private static By deltaElementPresentBonus = By.xpath("(.//*[@class='center' and text()='10500'])[1]");
        private static By deltaElementPresentPunish = By.xpath("(.//*[@class='center' and text()='-10500'])[1]");



    public void clickUsersMenu(){
        elements.clickButton(usersLink);
        log.info("Click_Menu -> 'Users'");
    }

    // Click to first user from the list
    public void clickEditUser(){
        elements.clickButton(editFirstUser);
        log.info("Click_Button -> 'Edit' (1st User from the top)");
    }

//        Transactions   #########################################

    public void clickTransactions_Tab(){
        elements.clickButton(transactions_Tab);
        log.info("Click_Tab -> 'Transactions'");
    }

    public void clickAddBonus_Button(){
        elements.clickButton(addBonus_Button);
        log.info("Click_Button -> 'Add bonus'");
    }

    public void clickPunish_Button(){
        elements.clickButton(punish_Button);
        log.info("Click_Button -> 'Punish'");
    }

    public void addDataIn_valueField(final String value){
        elements.inputText(value_field, value);
        log.info("Input in the 'Value' field - Data (" +value+ ")");
    }

    public void addDataIn_notesField(final String note){
        elements.inputText(notes_field, note);
        log.info("Input in the 'Notes' field - Data (" +note+ ")");
    }

    public void clickSubmit_Button(){
        elements.clickButton(submit_Button);
        log.info("Click_Button -> 'Submit'");
    }

    public void waitForPunishButton(){
        wait.until(ExpectedConditions.elementToBeClickable(punish_Button));
    }

//     TODO: make sure that method is correct and check right
    public boolean isDeltaBonusPresent () {
        try {
            elements.isElementPresent(deltaElementPresentBonus);
            log.info("Bonus Delta '10500' present in Table");
            return true;
        } catch (NoSuchElementException exeption) {
           return false;
        }
    }

//     TODO: make sure that method is correct and check right
    public boolean isDeltaPunishPresent () {
        try {
            elements.isElementPresent(deltaElementPresentPunish);
            log.info("Punishment Delta '-10500' present in Table");
            return true;
        } catch (NoSuchElementException exeption) {
            return false;
        }
    }



}
