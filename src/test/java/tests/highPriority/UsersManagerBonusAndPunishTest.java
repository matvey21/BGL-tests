package tests.highPriority;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.UsersPage;
import tests.BaseTest;
import java.util.concurrent.TimeUnit;


public class UsersManagerBonusAndPunishTest extends BaseTest{

    @Test  // Check Manager Bonus feature of the user
    public void testUserManagerBonus () {
        UsersPage usersPage = new UsersPage(driver);
        usersPage.clickUsersMenu();
        usersPage.clickEditUser();
        usersPage.clickTransactions_Tab();
        usersPage.clickAddBonus_Button();
        usersPage.addDataIn_valueField("10500");
        usersPage.addDataIn_notesField("test-bonus");
        usersPage.clickSubmit_Button();
        elements.waitForAjaxOnPage();
        Assert.assertTrue(usersPage.isDeltaBonusPresent(),"Transaction 'Add bonus' Unsuccessful");
        log.info("TEST PASS >> Bonus Added to User");
    }

    @Test (dependsOnMethods = "testUserManagerBonus") // Check Manager Punishment feature of the user
    public void testUserManagerPunishment () {
        UsersPage usersPage = new UsersPage(driver);
        elements.waitForAjaxOnPage();
        usersPage.waitForPunishButton();
        usersPage.clickTransactions_Tab();  //if you remove this click it doesn't work for reason ??? can't click Punish_Button
        usersPage.clickPunish_Button();
        usersPage.addDataIn_valueField("10500");
        usersPage.addDataIn_notesField("test-punish");
        usersPage.clickSubmit_Button();
        Assert.assertTrue(usersPage.isDeltaPunishPresent(), "Transaction 'Punish' Unsuccessful");
        log.info("TEST PASS >> Punish User");
    }

}
