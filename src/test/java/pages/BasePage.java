package pages;


import libsmy.WebElements;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
    WebDriver driver;
    WebElements elements;
    WebDriverWait wait;
    Logger log;
    //public static int MAX_TIMEOUT = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        elements = new WebElements(driver);
        wait = new WebDriverWait(driver, 10);
        log = Logger.getLogger(BasePage.class);
    }
}


