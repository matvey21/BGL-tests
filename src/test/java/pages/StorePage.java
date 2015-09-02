package pages;


import libsmy.WebElements;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;


public class StorePage extends BasePage {

    public StorePage(WebDriver driver) {
        super(driver);
    }

    private static By backgammonBtn = By.xpath(".//*[@id='sidebar']/div[1]/ul/li[2]/a");
    private static By storeTemplate = By.xpath(".//*[@id='store-packages-table']/tbody/tr[1]/td[2]");
    private static By defaultBtn = By.xpath(".//*[@id='coins-store-template']/div[2]/div[2]/div[2]/div/div[1]/div/div");
    //    private static By EnabledBtn = By.xpath(".//*[@id='coins-store-template']/div[2]/div[1]/div[2]/div/div[1]/div/div);
    private static By deviceListAndroid = By.xpath(".//*[@id='uniform-undefined']/select[@name='device-type']");
    private static By deviceListWeb = By.xpath(".//*[@id='uniform-undefined']/select[@name='device-type']");
    private static By storeDefaultItem1 = By.xpath(".//*[@id='coins-store-template']//select[@name='store-def-item']");
    private static By storeDefaultItem2 = By.xpath(".//*[@id='coins-store-template']//select[@name='store-def-item']");
    private static By segments = By.xpath(".//*[@id='segments_chzn']/ul[@class='chzn-choices']");
    private static By segmentsList = By.xpath(".//*[@id='segments_chzn']//ul[@class='chzn-results']/li[@id='segments_chzn_o_43']");
    private static By closeSegmentsList = By.xpath(".//*[@id='segments_chzn_c_43']/a");
    private static By isSaleBtn = By.xpath(".//*[@id='coins-store-template']/div[2]/div[12]/div[2]/div/div[1]/div/div");
    private static By saveBtn = By.xpath(".//*[@id='coins-store-template']//input[@name=\"btn-edit-save\"]");


// *********** TestBGL1648a
    public void backgammonBtn() {
        elements.clickButton((backgammonBtn));
    }

    public void openStore() {
        elements.openURL("http://stage-cms.come2play.com/BackgammonStore");
    }

    public void StoreTemplate() {
        elements.clickLink((storeTemplate));
    }

    public boolean isStoreOpened() {
        try {
            driver.findElement(storeTemplate);
            return true;
        } catch (NoSuchElementException exception){
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

// *********** TestBGL1648b
    public void DisableDefaultBtn() {
        elements.clickButton(defaultBtn);
    }
    public void IsDefaultBtnDisabled() {
        try {
            driver.findElement(By.xpath(".//*[@id='coins-store-template']//div[@class='ibutton-label-off']"));
            log.info("DefaultBtn Disabled");
        } catch (NoSuchElementException ex){
            log.info("Test Failed DefaultBtn not Disabled");
        }
    }

    public void SelectDeviceAndroid() {
        elements.selectItemFromDropDownListByClick(deviceListAndroid, "Android");
    }

    public void SelectDeviceWeb() {
        elements.selectItemFromDropDownListByClick(deviceListWeb, "Web"); ///option[1]
    }

    public void SelectStoreDefaultItem1() {
        elements.selectItemFromDropDownListByClick(storeDefaultItem1, "1,010,000 coins + Flaming Dices - 99.99$");
    }

    public void SelectStoreDefaultItem2() {
        elements.selectItemFromDropDownListByClick(storeDefaultItem2, "72,500 coins - 9.99$");
    }

    public void SelectSegmentsList() {
        elements.clickButton(segments);
        elements.clickButton(segmentsList);
//        elements.selectItemFromDropDownListByClick(SegmentsList, "Level from 0 to 9");
    }


    public void CloseSelectSegmentsList() {
        elements.clickButton(closeSegmentsList);
    }

    public void EnableIsSaleBtn() {
        elements.clickButton(isSaleBtn);
    }

    public void ClickSaveBtn() {
        elements.clickButton(saveBtn);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='store-packages-table']/tbody/tr[1]/td[2]")));
    }


}