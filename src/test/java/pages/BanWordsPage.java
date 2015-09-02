package pages;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tests.BaseTest;

import java.awt.*;
import java.awt.event.KeyEvent;

public class BanWordsPage extends BasePage{

    public BanWordsPage (WebDriver driver) { super(driver);}

        private static By tab1 = By.xpath(".//*[@href='#sub-menu-tab-1']");
        private static By banWordsLink = By.xpath("(.//*[@href='/BackgammonBanWords'])[1]");
        private static By banWordsFile = By.xpath(".//*[@href='https://bg-static.come2play.com/backgammon/configs/banwords.json']");

    public void clickTab1(){
        elements.clickButton(tab1);
        log.info("Click_Tab -> Tab1");
    }

    public void clickBanWordsMenu () {
        elements.clickButton(banWordsLink);
        log.info("Click_Button -> BanWords Menu");
    }

    public void clickBanWordsLink(){
        if (elements.isElementPresent(banWordsFile)){;
            elements.clickLink(banWordsFile);
            log.info("BanWords File present and clicked");
        }
        else{
            log.info("There is no BanWords Link");
        }
    }

    public void uploadFile() {
        try {
            Robot rb = new Robot();
            rb.delay(1000);
            rb.keyPress(KeyEvent.VK_TAB);
            rb.keyRelease(KeyEvent.VK_TAB);
            rb.delay(300);
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);
            log.info("KeyPress Robot worked properly");
        } catch (Exception ex){
            log.info("KeyPress Robot doesn't work ;(");
        }
    }
}
