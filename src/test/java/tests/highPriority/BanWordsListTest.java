package tests.highPriority;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BanWordsPage;
import tests.BaseTest;

// BGL-no  CMS: Ban words list is available
public class BanWordsListTest extends BaseTest {

    @Test
    public void testBanWordsListPresent () {
        BanWordsPage banWordsPage = new BanWordsPage(driver);
        banWordsPage.clickTab1();
        banWordsPage.clickBanWordsMenu();
        banWordsPage.clickBanWordsLink();
        banWordsPage.uploadFile();
        log.info("TEST PASS >> Link to BanWordsList available and works");

// TODO: you need to open file >> and check that words is there! as we need.
// http://seleniumeasy.com/selenium-tutorials/verify-file-after-downloading-using-webdriver-java
    }

}
