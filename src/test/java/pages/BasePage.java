package pages;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import libsmy.WebElements;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
    protected WebDriver driver;
    protected WebElements elements;
    protected WebDriverWait wait;
    protected Logger log;
    public static int MAX_TIMEOUT = 10;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        elements = new WebElements(driver);
        wait = new WebDriverWait(driver, 10);
        log = Logger.getLogger(this.getClass());

    }


}


