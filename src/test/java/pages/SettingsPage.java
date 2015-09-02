package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SettingsPage extends BasePage{

   public SettingsPage(WebDriver driver){
        super(driver);
    }

    private static By tab1 = By.xpath(".//*[@href='#sub-menu-tab-1']");
    private static By settingsLink  = By.xpath("(.//*[@href='/BackgammonSettings'])[1]");
    private static By bgAndroidVersion  = By.xpath(".//*/a[text()='BG_Android_Version']");
    private static By fieldValue  = By.xpath(".//*/input[@name='value']");
    private static By fieldDescription  = By.xpath(".//*/input[@name='description']");
    private static By buttonSubmit  = By.xpath(".//*/button[text()='Submit']");
    private static By textAndroidVersion1  = By.xpath(".//*/td[contains(text(), '1.2.0')]");
    private static By textAndroidVersion2  = By.xpath(".//*/td[contains(text(), '1.0.0')]");
//    private static By   = By.xpath("");
//    private static By   = By.xpath("");
//    private static By   = By.xpath("");
//    private static By   = By.xpath("");
//    private static By   = By.xpath("");


    public void clickTab1(){
        elements.clickButton(tab1);
        log.info("Click_Tab -> Tab1");
    }

    public void clickSettingsMenu () {
        elements.clickButton(settingsLink);
        log.info("Click_Menu -> 'Settings'");
    }

    public void clickBgAndroidVersion(){
        elements.clickButton(bgAndroidVersion);
        log.info("Click -> 'BG_Android_Version'");
    }

    public void inputValue(final String value) {
        elements.inputText(fieldValue, value);
        log.info("Input_value (" +value+")");
    }

    public void inputDescription(final String description){
        elements.inputText(fieldDescription, description);
        log.info("Input_description (" +description+")");
    }

    public void clickButtonSubmit() {
        elements.clickButton(buttonSubmit);
        log.info("Click_Button -> 'Submit'");
    }

    public boolean isNewAndroidVersionPresent() {
        try {
            elements.isElementPresent(textAndroidVersion1);
            log.info("Android version changed");
            return true;
        } catch (Exception ex) {
            log.info("Android version does not changed");
            return false;
        }
    }

    public boolean isOldAndroidVersionPresent() {
        try {
            elements.isElementPresent(textAndroidVersion2);
            log.info("Android version changed");
            return true;
        } catch (Exception ex) {
            log.info("Android version does not changed");
            return false;
        }
    }



}
