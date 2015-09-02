package pages;


import libsmy.WebElements;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private static By loginTextField = By.id("login");
    private static By passTextField = By.id("password");
    private static By loginButton = By.id("btn-login");


    public void openLogInPage() {
        elements.openURL("http://stage-cms.come2play.com/");
    }

    // User login in to CMS
    public void userLogIn(final String login, final String pass) {
        elements.inputText((loginTextField), login);
        elements.inputText((passTextField), pass);
        elements.clickButton((loginButton));
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@href='/BackgammonAccount/LogOff']")));
        elements.waitForPageLoaded(driver);
        log.info("User is logged succesfully");
    }

    private static By LogoutLink = By.xpath(".//*[@href='/BackgammonAccount/LogOff']");
    public boolean isUserLoggedIn (){
        try {
            driver.findElement(LogoutLink);
            return true;
        } catch (NoSuchElementException exception){
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }
}

