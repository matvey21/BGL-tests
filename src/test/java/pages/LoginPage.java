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
    private static By backgammonBtn = By.xpath("(.//*[@href='/BackgammonActions'])[1]");
    private static By LogoutLink = By.xpath(".//*[contains(@href, 'LogOff')]");
    private static By userPictureDrop = By.xpath(".//*[@class='leftUserDrop']");


    // User login in to CMS
    public void userLogIn(final String login, final String pass){
        elements.inputText(loginTextField, login);
        elements.inputText(passTextField, pass);
        elements.clickButton(loginButton);
        elements.waitForPageLoaded(driver);
        log.info("User is Logged successfully");
    }

    public boolean isUserLoggedIn(){
        return elements.isElementPresent(LogoutLink);
    }

    public void userLogOut(){
        elements.clickButton(userPictureDrop);
        elements.clickLink(LogoutLink);
        log.debug("Click_Link -> 'Logout'");
    }

    public void backgammonBtn(){
        elements.clickButton(backgammonBtn);
        log.info("Click_Button -> 'Backgammon'");
    }
}

