package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.net.MalformedURLException;


public class FlashLogsPage extends BasePage{

  public FlashLogsPage(WebDriver driver){
      super(driver);
  }

    private static By tab2 = By.xpath(".//*[@href='#sub-menu-tab-2']");
    private static By flashLogsMenu = By.xpath("(.//a[@href='/BackgammonLogs/BGFlashLogs'])[1]");
//    private static By deviceChoose = By.xpath("(.//*[@id='uniform-undefined']/select[@name='device'])[1]");
    private static By deviceChoose = By.xpath("html/body/div[3]/div[3]/div/div/div[1]/div[1]/div[1]/div/select");
    private static By searchButton = By.xpath("(.//*[@class='buttonS bGreen' and @value='Search'])[1]");
    private static By logsTable_firstLog = By.xpath("(.//*[@id='logs-table']//td[@class='center sorting_1'])[1]");
    private static By logsDetailsTable_InfoRow = By.xpath(".//*[@id='uniform-undefined']/span[text()='Android']");
//    private static By  = By.xpath("");


    public void clickTab2 (){
        elements.clickButton(tab2);
        log.info("Click_Tab -> Tab2");
    }

    public void clickFlashLogs (){
        elements.clickButton(flashLogsMenu);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Click_Link -> BGFlashLogs");
    }

    public void choseDevice(final String device){
        elements.selectItemFromDropDownListByValue(deviceChoose, device);
        log.info("Select_Device -> " + device);
    }

    public void clickSearchButton(){
        elements.clickButton(searchButton);
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Click_Button -> 'Search'");
    }

    public boolean isFilteringByDevice(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(logsDetailsTable_InfoRow));
        if (!(driver.findElements(logsDetailsTable_InfoRow).isEmpty())) {
            log.info("Filtering by Device -> Android");
            return true;
        } else {
            log.info("element was not found");
            return false;
        }
    }

    public void clickFirstLogFromListIfNotEmpty() {
        if (!(driver.findElement(logsTable_firstLog) != null)) {
            elements.clickButton(logsTable_firstLog);
            log.info("Click -> First Log From List");
        } else {
            elements.isTextPresent("No matching records found");
            log.info("No matching records found");
        }
    }

    public  boolean isTextPresent(final String whatText) {
        try{
            driver.findElement(By.xpath(".//*[contains(.,'"+ whatText +"')]")).isDisplayed();
            log.debug(whatText+ " is Presented");
            return true;
        } catch (NoSuchElementException exception){
            return false;
        }
    }

    // FIXME: check that you filtering what you need by checking post request
    // errorsFilter.device 1 (mobile)
//    public boolean isFlashLogsPageLoaded() throws IOException {
//        String currentUrl = driver.getCurrentUrl();
//        elements.getResponseCode(currentUrl);
//
//    }


}
