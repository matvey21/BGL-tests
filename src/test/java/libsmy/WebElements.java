package libsmy;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;



public class WebElements{

    WebDriver driver;
    Wait<WebDriver> wait;
    Logger log;
    String parentWindowHandler;

    public String getParentWindowHandler(){
        return parentWindowHandler;
    }

    public WebElements (WebDriver driver){
            this.driver = driver;
            parentWindowHandler = driver.getWindowHandle();
            log = Logger.getLogger(WebElements.class);
            wait = new WebDriverWait(driver, 10).withMessage("Element was not found");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

//        	This method is used to open site URL
        public void openURL(String url){
            driver.get(url);
            //if Exception - comment next line (frame (0) is not present on every site)
            //driver.switchTo().frame(0);
            log.debug("URL was opened" + url);
        }

//        	This method is used to click on some button
        public void clickButton(By buttonLocator){
            WebElement button = null;  // driver.findElement(buttonLocator);  //= null;
            try {
                button = driver.findElement(buttonLocator);
            }
            catch (NoSuchElementException exception){
                Assert.fail("Some element was not found");
            }
            button.click();
            log.debug("Perform click on button with locator: " + buttonLocator);
        }

//        	This method is used to click on some link
        public void clickLink(By linkLocator){
            WebElement link= driver.findElement(linkLocator);
            link.click();
            log.debug("Link was clicked: " +linkLocator);
        }

//        	This method is used to clear textField
        public void clearTextField(By textFieldLocator){
            WebElement textField;
            //textField = driver.findElement(By.xpath(textFieldLocator)); in this case in parameters (String textfieldLocator)
            //textField = driver.findElement(ui(textFieldLocator)); in this case in parameters (String textfieldLocator) and ConfigData.java parse our paths in UIMapping.propperties
            //textField = driver.findElement(By.xpath(textFieldLocator)).clear(); We can wrote in one line.
            textField = driver.findElement(textFieldLocator);
            textField.click();
            textField.clear();
            log.debug("Text field was cleared");
        }

//        	This method is used to input some text into some textField
        public void inputText(By textFieldLocator, String text){
            WebElement textField = driver.findElement(textFieldLocator);
            clearTextField(textFieldLocator);
            textField.sendKeys(text);
            log.debug(text + " Inputted to field - with Locator: " + textFieldLocator);
        }

//        	This method is used to input some text into some textField and click ENTER key
        public void inputSomeTextAndClickEnterKey(By textFieldLocator, String text){
            inputText(textFieldLocator, text);
            log.debug(text + " Inputted to field - with Locator: " + textFieldLocator);
            driver.findElement(textFieldLocator).sendKeys(Keys.ENTER);
            log.debug("Enter Key was clicked");
        }

//        	This method is used to click on some radioButton
        public void selectRadioButton(By radioButtonLocator)  {
            WebElement radioButton = driver.findElement(radioButtonLocator);
            radioButton.click();
            log.debug("Radio Button was selected" + radioButtonLocator);
        }

        public void selectRadioButtonFromBlockByText(String blockLocator, String radioButtonName)  {
            String radioBtnXpath = blockLocator+"[contains(text(), '"+radioButtonName+"')]";
            WebElement radioButton = driver.findElement(By.xpath(radioBtnXpath));
            radioButton.click();
            log.debug("RadioButton From Block By Text was selected");
        }

        public void selectRadioButtonFromBlockByValue(String blockLocator, String radioButtonValue) {
            String radioBtnXpath = blockLocator+"[@value='"+radioButtonValue+"']";
            WebElement radioButton = driver.findElement(By.xpath(radioBtnXpath));
            radioButton.click();
            log.debug("RadioButton From Block By Value was selected");
        }

        /*
         * This method is used to select or deselect checkBox
         * checkBoxState should correspond to next format "YES/NO"
         *
         * Checkbox exist
         * if Y (do nothing)
         * if N (click)
         *
         * Checkbox not exist
         * if Y (click)
         * if N (do nothing)
         */
        public void selectCheckBox (String checkBoxLocator, String checkBoxState) {
            WebElement checkBox;
            checkBox=driver.findElement(By.xpath(checkBoxLocator));
            // checkBox=driver.findElement(ui(checkBoxLocator));
            if (checkBox.isSelected() && checkBoxState.equals("YES")){
                //wait.until(ExpectedConditions.elementToBeSelected(By.xpath("checkbox locator")))
                System.out.println("CheckBox is already selected");
                log.debug("Checkbox is already selected");
            }
            if (checkBox.isSelected() && checkBoxState.equals("No")){
                checkBox.click();
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("./xpathOfLoginButton")));
                wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeSelected(By.xpath(checkBoxLocator))));
                wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath(checkBoxLocator), false));
                log.debug("Checkbox is un-checked");
            }
            if (!checkBox.isSelected() && checkBoxState.equals("No")){
                checkBox.click();
                //wait.until(ExpectedConditions.elementToBeSelected(By.xpath("checkbox locator")));
                //wait.until(ExpectedConditions.elementToBeSelected(ui("checkbox locator")));
                log.debug("Checkbox is selected");
            }
            if (!checkBox.isSelected() && checkBoxState.equals("YES")){
                System.out.println("Checkbox is already deselected");
                log.debug("Checkbox is already deselected");
            }
        }

        public void focusOnElement(String elementLocator) {
            WebElement element = driver.findElement(By.xpath(elementLocator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
            log.debug("Element on focus: " + elementLocator);
        }

        public void selectItemFromDropDownListByVisibleText(By listLocator, String itemName) {
            WebElement list = driver.findElement(listLocator);
            list.click();
            Select select = new Select(list);
            select.selectByVisibleText(itemName);
            log.debug("Item: " +itemName+ " was selected from Drop Down List By Click");
        }

        public void selectItemFromDropDownListByValue(By listLocator, String itemName) {
            WebElement list = driver.findElement(listLocator);
            list.click();
            Select select = new Select(list);
            select.selectByValue(itemName);
            log.debug("Item: " +itemName+ " was selected from Drop Down List By Click");
        }
        public void selectItemFromDropDownListByIndex(By listLocator, Integer itemNumber) {
            WebElement list = driver.findElement(listLocator);
            list.click();
            Select select = new Select(list);
            select.selectByIndex(itemNumber);
            log.debug("Item: " +itemNumber+ " was selected from Drop Down List By Click");
        }

        public void focusItemFromDropDownListByVisibleText(String listLocator, String itemName)  {
            WebElement list = driver.findElement(By.xpath(listLocator));
            focusOnElement(listLocator);
            Select select = new Select(list);
            select.selectByVisibleText(itemName);
            log.debug("Item was selected from Drop Down List By Focus");
        }

        public boolean isElementPresent(By elementLocator){
            try{
                driver.findElement(elementLocator);
                log.debug("Element is Presented");
                return true;
            } catch (NoSuchElementException exception) {
                return false;
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

/*    public static void waitforElementDisplayed(By locator) {
        try {
            for (int second = 0; second<10; second++) {
                if (isElementPresent(locator)){
                    if (getDriver().findElement(locator).isDisplayed()){
                        break;
                    }
                }
                Thread.sleep(1000);
            }
        }
        catch (Exception e) {e.printStackTrace();}
    }*/


//        This method is used to refresh web page
        public void refreshPage() {
            //location.reload(true);
            driver.navigate().refresh();
            waitForPageLoaded(driver);
            log.debug("Page was refreshed");
        }

//    // Displays all the cookies available for the current URL
//    public void showCookies (){
//        Set<Cookie> allCookies = driver.manage().getCookies();
//        for (Cookie loadedCookie : allCookies) {
//            System.out.println(String.format("%s -> %s", loadedCookie.getName(), loadedCookie.getValue()));
//        }
//    }


//          This method is used to delete all cookies
        public void deleteAllCookies() {
            driver.manage().deleteAllCookies();
            log.debug("All Cookies was deleted");
        }


//          This method is used to delete named cookie
        public void deleteNamedCookie(String cookieName) {
            driver.manage().deleteCookieNamed(cookieName);
            log.debug("Named Cookies was deleted" + cookieName);
        }



        public void waitForPageLoaded(WebDriver driver) {
            ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                }
            };
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(pageLoadCondition);
            log.info("Page Loaded Successfully");
        }

    private void waitForAjaxElement() throws InterruptedException
    {
        while (true)
        {
            Boolean ajaxIsComplete = (Boolean) ((JavascriptExecutor)driver).executeScript("return jQuery.active == 0");
            if (ajaxIsComplete){
                break;
            }
            Thread.sleep(100);
        }
    }
    public void waitForAjaxOnPage() {
        try {
            waitForAjaxElement();
            log.info("wait for ajax");
        } catch (InterruptedException ex) {
            log.info("wait for ajax doesn't work");
        }
    }


/*    public boolean waitForJStoLoad() {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long)executeJavaScript("return jQuery.active") == 0);
                }
                catch (WebDriverException e) {
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return executeJavaScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }*/

    public void checkForErrorsFromLogs() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);  //output all console logs from browser
        int errorsCount = 0;
        for (LogEntry entry : logEntries) {
            if(entry.getLevel() == java.util.logging.Level.SEVERE) {  // allocating only errors level
                log.info(driver.getTitle() + " \r\n " + new Date(entry.getTimestamp()) + "___" + entry.getLevel() + "___" + entry.getMessage() + " \r\n -->> " + driver.getCurrentUrl());
                errorsCount++;
            }
        }
        Assert.assertTrue(errorsCount == 0, "Found Error");
//        if(errorsCount == 0)
//            log.info("No errors found");
    }

/*   // OR ANOTHER WAY TO CHECK LOGS   **************************************************
        //We can insert the script execution
        String script = "window.collectedErrors = [];"
                + "window.onerror = function(errorMessage) { "
                + "window.collectedErrors[window.collectedErrors.length] = errorMessage;"
                + "}";

        String script = "window.onerror=function(msg){"
                + "$(\"body\").attr(\"JSError\",msg);"
                + "}";

        Object o = ((JavascriptExecutor) driver).executeScript(script);
        log.info(o);
    }

        List<JavaScriptError> jsErrors = JavaScriptError.readErrors(driver);
        System.out.println("###startlaying errors");
        for (int i = 0; i < jsErrors.size(); i++) {
            System.out.println(jsErrors.get(i).getErrorMessage());
            System.out.println(jsErrors.get(i).getLineNumber());
            System.out.println(jsErrors.get(i).getSourceName());
        }
        System.out.println("###startlaying errors");
    }

  // Use Library JSErrorCollector-0.6.jar  (Seems like thees class analyse js Scripts / not what we need)
  //Add to @BeforeTest
  FirefoxProfile ffProfile = new FirefoxProfile();
  JavaScriptError.addExtension(ffProfile);
  final WebDriver driver = new FirefoxDriver(ffProfile);
        //Capture all errors and store them In array.
        List<JavaScriptError> jsErrors = JavaScriptError.readErrors(driver);
//        Assert.assertTrue(jsErrors.isEmpty(), "JS errors occured: " + jsErrors);
        System.out.println("Total No Of JavaScript Errors : " + jsErrors.size());
        //Print Javascript Errors one by one from array.
        for (int i = 0; i < jsErrors.size(); i++) {

            System.out.println("Error Console : " + jsErrors.get(i).getConsole());
            System.out.println("Error Message : " + jsErrors.get(i).getErrorMessage());
            System.out.println("Error Line No : " + jsErrors.get(i).getLineNumber());
            System.out.println(jsErrors.get(i).getSourceName());
            System.out.println();
        }
*/


  //  method to switch to the required window as shown below
/*    public class Utility
    {
        public static WebDriver getHandleToWindow(String title){

            //parentWindowHandle = WebDriverInitialize.getDriver().getWindowHandle(); // save the current window handle.
            WebDriver popup = null;
            Set<String> windowIterator = WebDriverInitialize.getDriver().getWindowHandles();
            System.err.println("No of windows :  " + windowIterator.size());
            for (String s : windowIterator) {
                String windowHandle = s;
                popup = WebDriverInitialize.getDriver().switchTo().window(windowHandle);
                System.out.println("Window Title : " + popup.getTitle());
                System.out.println("Window Url : " + popup.getCurrentUrl());
                if (popup.getTitle().equals(title) ){
                    System.out.println("Selected Window Title : " + popup.getTitle());
                    return popup;
                }

            }
            System.out.println("Window Title :" + popup.getTitle());
            System.out.println();
            return popup;
        }
    }*/

  public void switchToPopupWindow() {
      try {
          String subWindowHandler = null;

          Set<String> handles = driver.getWindowHandles(); // get all window handles
          Iterator<String> iterator = handles.iterator();
          while (iterator.hasNext()){
              subWindowHandler = iterator.next();
          }
          driver.switchTo().window(subWindowHandler); // switch to popup window
          log.info("Dialog Window is opens" + driver.getTitle());
          
      } catch (Exception e) { //exception handling
          log.info("Dialog Window is not present");
      }
  }

    public void switchToParentWindow() {
        driver.switchTo().window(getParentWindowHandler());  // switch back to parent window
        log.info("pass switch");
    }

// Extra methods *******************************************************************************************************

    // Highlight elements. This method will slow down your tests a bit, but sometimes it is useful for debugging purpose.
    // Find Xpath of an element and Draw Border around it,
    public void highlightElement(By element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 2; i++) {
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 2px solid yellow;");
            js.executeScript("arguments[0].setAttribute('style','border: solid 2px red')", element);
        }
    }

    // ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 4px solid yellow;");

/*    // it gives a webpage status
    String url = "http://www.google.com/";
    WebClient webClient = new WebClient();
    HtmlPage htmlPage = webClient.getPage(url);
    try{
        //verify response
        Assert.assertEquals(200,htmlPage.getWebResponse().getStatusCode());
        System.out.println(true);
    }*/

    // We have a utility class using HttpURLConnection to get GET response codes,
    // but you could use that same approach to get other methods using the setRequestMethod method.
    // Here's how we get the GET responses:
    public static int getResponseCode(String url) throws MalformedURLException, IOException {
        HttpURLConnection.setFollowRedirects(true);
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setConnectTimeout(30);
        con.setRequestMethod("POST");
        int responseCode = con.getResponseCode();
        if(con != null){
            con.disconnect();
        }
        return responseCode;
    }

    /**
     * Method: load
     * Overidden method from the LoadableComponent class.
     * @return  void
     * @throws  null
     */
//    @Override
    protected void load(By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>( driver )
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring( StaleElementReferenceException.class ) ;
        wait.until( ExpectedConditions.presenceOfElementLocated(locator) );
    }
}
