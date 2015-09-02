package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LevelsPage extends BasePage{

    public LevelsPage(WebDriver driver){
        super(driver);
    }

    private static By levelsLink = By.xpath("(.//*[@href='/BackgammonLevels'])[1]");
    private static By levelsDropdownList = By.xpath(".//*/select[@name='levels-table_length']");
    private static By countOfLevels = By.xpath("count(.//*/tbody/tr)");


    public void clickLevelsMenu(){
        elements.clickButton(levelsLink);
        log.info("Click_Menu -> Levels");
    }

    public void selectEntriesInDropDownList(){
        elements.selectItemFromDropDownListByVisibleText(levelsDropdownList, "All");
        log.info("Select_item 'All' from dropdown list");
    }

//      Take the TR Count
    public boolean isAllLevelsPresence(){
        List<WebElement> located_elements = driver.findElements(By.xpath(".//*/tbody/tr"));
        int count = located_elements.size();

        if (count == 36){
            log.info("Count is right -> " +count+" = 36");
            return true;
        } else {
            log.info("Count is not 36");
            return false;
        }
    }

    public void clickSearchFieldToProvokeElementsShown(){
        elements.clickButton(By.xpath(".//*/input[@placeholder='type to filter...']"));
        log.info("Click -> Search Field");
    }

}
