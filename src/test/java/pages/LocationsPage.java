package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;


public class LocationsPage extends BasePage {

    public LocationsPage(WebDriver driver){
        super(driver);
    }

    private static By menuLocations = By.xpath(".//*[@href='/BackgammonLocations']");
    private static By buttonBack = By.xpath(".//*[@class='btn-cancel-edit btn-back buttonH bBlack']");
    private static By firstLocationFromTable = By.xpath("(.//*[@id='locations-table']//a[@title='Edit'])[1]");

    private static By fieldTips = By.xpath(".//*[@class='ui-spinner-box' and @name='tips']");
    private static By buttonSave = By.xpath(".//*[@name='btn-location-edit-save']");
    private static By button_OK_popup = By.xpath(".//*[@type='button' and text()='OK']");



    public void clickMenuLocations(){
        elements.clickButton(menuLocations);
        log.info("Click_Button -> 'Locations'");
    }

    public void clickOnLocationsInTableRandom(){
        List<WebElement> located_elements = driver.findElements(By.xpath(".//*[@id='locations-table']/tbody//a[@title='Edit']"));
        if (located_elements.size()>0){
            Random random = new Random();
            int index_element = random.nextInt(located_elements.size()-1);
            located_elements.get(index_element).click();
            log.info("Random element chosen : " +index_element);
        }
    }

    public void clickBackButton(){
        elements.clickButton(buttonBack);
        log.info("Click_Button -> 'Back'");
    }

    public void clickFirstLocationInTable(){
        elements.clickButton(firstLocationFromTable);
        log.info("Click -> first Location from Table");
    }

    public void inputValueInTipField(final String value){
        elements.inputText(fieldTips, value);
        log.info("Input_Text -> Tips field : " +value);
    }

    public void clickSaveButton(){
        elements.clickButton(buttonSave);
        log.info("Click_Button -> 'Save'");
    }

    public void clickOkButton(){
        wait.until(ExpectedConditions.elementToBeClickable(button_OK_popup));
        elements.clickButton(button_OK_popup);
        log.info("Click_Button -> 'OK'");
    }

}
