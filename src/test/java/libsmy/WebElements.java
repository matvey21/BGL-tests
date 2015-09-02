package libsmy;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.log4j.Logger;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class WebElements {

        WebDriver driver;
        WebDriverWait waitForCondition;
        Logger log;

        public WebElements (WebDriver driver){
            this.driver = driver;
            log = Logger.getLogger(WebElements.class);
            // explicitly wait
            waitForCondition = new WebDriverWait(driver, 5);
            // implicitly wait
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        }

        //	This method is used to open site URL
        public void openURL(String url) {
//            deleteAllCookies();
            driver.get(url);
            //if Exception - comment next line (frame (0) is not present on every site)
            //driver.switchTo().frame(0);
            log.info("URL was opened");
        }

        //	This method is used to click on some button
        public void clickButton(By buttonLocator)  {
            WebElement button = null;
            //button = driver.findElement(buttonLocator);
            //button = driver.findElement(By.xpath(buttonLocator)); in this case in parameters (String buttonLocator)
            //button = driver.findElement(ui(buttonLocator)); in this case in parameters (String buttonLocator) and ConfigData.java parse our paths in UIMapping.propperties
            try{button = driver.findElement(buttonLocator);}
            catch (NoSuchElementException exeption){
                Assert.fail("Some element was not found");}
            button.click();
            log.info("Button was clicked");
            //System.out.println("Perform click on button with locator: " + buttonLocator);
        }

        //	This method is used to click on some link
        public void clickLink(By linkLocator)  {
            WebElement link;
            //link = driver.findElement(By.linkText(linkLocator)); in this case in parameters (String linkLocator)
            //link = driver.findElement(ui(linkLocator)); in this case in parameters (String linkLocator) and ConfigData.java parse our paths in UIMapping.propperties
            link = driver.findElement(linkLocator);
            link.click();
            log.info("Link was clicked");
        }

        //	This method is used to input some text into some textField
        public void inputText(By fieldLocator, String text)  {
            WebElement textField;
            //textField = driver.findElement(By.xpath(fieldLocator));  in this case in parameters (String fieldLocator)
            //textField = driver.findElement(ui(fieldLocator)); in this case in parameters (String fieldLocator) and ConfigData.java parse our paths in UIMapping.propperties
            textField = driver.findElement(fieldLocator);
            textField.click();
            textField.clear();
            textField.sendKeys(text);
            log.info("Text was inputted");
            //System.out.println(text + " inputed to field with locator: " + fieldLocator);
        }

        //	This method is used to clear textField
        public void clearTextField(By textFieldLocator)  {
            WebElement textField;
            //textField = driver.findElement(By.xpath(textFieldLocator)); in parameters (String textfieldLocator)
            //textField = driver.findElement(ui(textFieldLocator)); in parameters (String textfieldLocator) and ConfigData.java parse our paths in UIMapping.propperties
            //textField = driver.findElement(By.xpath(textFieldLocator)).clear(); We can wrote in one line.
            textField = driver.findElement(textFieldLocator);
            textField.clear();
            log.info("Text field was cleared");
        }

        //	This method is used to input some text into some textField and click ENTER key
        public void inputSomeTextAndClickEnterKey(By textFieldLocator, String text)  {
            clearTextField(textFieldLocator);
            inputText(textFieldLocator, text);
            log.info("Some Text was inputted");
            driver.findElement(textFieldLocator).sendKeys(Keys.ENTER);
            //driver.findElement(ui(textFieldLocator)).sendKeys(Keys.ENTER);
            log.info("Enter Key was clicked");
        }

        //	This method is used to click on some radioButton
        public void selectRadioButton(By radioButtonLocator)  {
            WebElement radioButton;
            //radioButton = driver.findElement(By.xpath(radioButtonLocator));
            //radioButton = driver.findElement(ui(radioButtonLocator));
            radioButton = driver.findElement(radioButtonLocator);
            radioButton.click();
            log.debug("Radio Button was selected");
        }

        public void selectRadioButtonFromBlockByText(String blockLocator, String radioButtonName)  {
            //String radioBtnXpath = ".//input[contains(text(), 'Wine')]";
            String radioBtnXpath = blockLocator+"[contains(text(), '"+radioButtonName+"')]";
            WebElement radioButton;
            radioButton = driver.findElement(By.xpath(radioBtnXpath));
            //radioButton = driver.findElement(ui(radioBtnXpath));
            radioButton.click();
            log.info("RadioButton From Block By Text was selected");
        }

        public void selectRadioButtonFromBlockByValue(String blockLocator, String radioButtonValue) {
            String radioBtnXpath = blockLocator+"[@value='"+radioButtonValue+"']";
            WebElement radioButton;
            radioButton = driver.findElement(By.xpath(radioBtnXpath));
            //radioButton = driver.findElement(ui(radioBtnXpath));
            radioButton.click();
            log.info("RadioButton From Block By Value was selected");
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
                //waitForCondition.until(ExpectedConditions.elementToBeSelected(By.xpath("checkbox locator")))
                System.out.println("CheckBox is already selected");
                log.info("Checkbox is already selected");
            }
            if (checkBox.isSelected() && checkBoxState.equals("No")){
                checkBox.click();
                waitForCondition.until(ExpectedConditions.elementToBeClickable(By.xpath("./xpathOfLoginButton")));
                waitForCondition.until(ExpectedConditions.not(ExpectedConditions.elementToBeSelected(By.xpath(checkBoxLocator))));
                waitForCondition.until(ExpectedConditions.elementSelectionStateToBe(By.xpath(checkBoxLocator), false));
                log.info("Checkbox is un-checked");
            }
            if (!checkBox.isSelected() && checkBoxState.equals("No")){
                checkBox.click();
                //waitForCondition.until(ExpectedConditions.elementToBeSelected(By.xpath("checkbox locator")));
                //waitForCondition.until(ExpectedConditions.elementToBeSelected(ui("checkbox locator")));
                log.info("Checkbox is selected");
            }
            if (!checkBox.isSelected() && checkBoxState.equals("YES")){
                System.out.println("Checkbox is already deselected");
                log.info("Checkbox is already deselected");
            }
        }

        public void focusOnElement(String elementLocator) {
            WebElement element;
            element = driver.findElement(By.xpath(elementLocator));
            //element = driver.findElement(ui(elementLocator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
            log.info("Element on focus");
        }

        public void selectItemFromDropDownListByClick(By listLocator, String itemName) {
            WebElement list;
            list = driver.findElement(listLocator);
            //list = driver.findElement(ui(listLocator));
            list.click();

            Select select = new Select(list);
            select.selectByVisibleText(itemName);
            log.info("Item was selected from Drop Down List By Click");
        }

        public void selectItemFromDropDownListByFocus(String listLocator, String itemName)  {
            WebElement list = driver.findElement(By.xpath(listLocator));
            focusOnElement(listLocator);

            Select select = new Select(list);
            select.selectByVisibleText(itemName);
            log.info("Item was selected from Drop Down List By Focus");
        }

        public boolean isElementPresent(By elementLocator){
            try{
                driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                driver.findElement(elementLocator);
                log.info("Element is Presented");
                return true;
            } catch (NoSuchElementException exception) {
                return false;
            }finally {
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }

        }

//    public static void waitforElementDisplayed(By locator) {
//        try {
//            for (int second = 0; second<10; second++) {
//                if (isElementPresent(locator)){
//                    if (getDriver().findElement(locator).isDisplayed()){
//                        break;
//                    }
//                }
//                Thread.sleep(1000);
//            }
//        }
//        catch (Exception e) {e.printStackTrace();}
//    }

        /*
        * This method is used to refresh web page
        */
        public void refreshPage() {
            //location.reload(true);
            driver.navigate().refresh();
            log.info("Page was refreshed");
        }

        /*
         * This method is used to delete all cookies
         */
        public void deleteAllCookies() {
            driver.manage().deleteAllCookies();
            log.info("All Cookies was deleted");
        }

        /*
         * This method is used to delete named cookie
         */
        public void deleteNamedCookie(String cookieName) {
            driver.manage().deleteCookieNamed(cookieName);
            log.info("Named Cookies was deleted");
        }

        public void waitForPageLoaded(WebDriver driver) {

            ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                }
            };
        }
//    	 Wait<WebDriver> wait = new WebDriverWait(driver,30);
//    	  try {
//    	          wait.until(expectation);
//    	  } catch(Throwable error) {
//   	          assertFalse("Timeout waiting for Page Load Request to complete.", true);
//    	  }
//    	}





    }
