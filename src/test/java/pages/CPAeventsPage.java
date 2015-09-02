package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;

public class CPAeventsPage extends BasePage {

    public CPAeventsPage (WebDriver driver){
        super(driver);
    }

    private static By tab2 = By.xpath(".//*[@href='#sub-menu-tab-2']");
    private static By cpaEvent = By.xpath(".//*[@href='/BackgammonCpaEvents']");
    private static By timeRangeFromDate = By.id(".//*[@id='from-date']");
    private static By timeRangeToDate = By.id(".//*[@id='to-date']");
    private static By paymentsType = By.xpath(".//*[@class='chzn-single']");
    private static By paymentsTypeAll = By.id(".//*[@id='payment-type_chzn_o_0']");
    private static By applyFilterBtn = By.id(".//*[@id='btn-update-cpa-events']");

   //

    public void clickTab2 (){
        elements.clickButton(tab2);
    }

    public void clickCPAevents (){
        elements.clickButton(cpaEvent);
    }

    public void setTimeRangeFromDate (final String fromDate) {
        elements.inputText(timeRangeFromDate, fromDate);
    }
    public void setTimeRangeToDate (final String toDate) {
        elements.inputText(timeRangeToDate, toDate);
    }

    public void clickPaymentsType () {
        elements.clickButton(paymentsType);
    }

    public void clickPaymentsType_All (){
        elements.clickButton(paymentsTypeAll);
    }

//    TODO: Why this method doesn't work ? Can't find text "(All)"
//    public void paymentsTypeAll(){
//        elements.selectItemFromDropDownListByClick(paymentsType, "(All)");
//    }

    public void clickApplyFilterButton () {
        elements.clickButton(applyFilterBtn);
    }


   /* Apply some filter (time range)
   use Payment type “All”, check what was filtered

    Check that commission is not 0


    Check filters by Product and by Store
    Create a report (check that all data displayed is in report if possible)*/
}
