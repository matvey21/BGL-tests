package tests.highPriority;

import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.Date;


public class CheckErrorsOnPagesTest extends BaseTest{

    private static By[] links = /*new Tab[]{
        new Tab(By.xpath("./*//*[@href='#sub-menu-tab-0']"),
               */ new By[]{
                        By.xpath(".//*/div[@id='sub-menu-tab-0']//a[@href='/BackgammonActions']"),
//                        By.xpath("(.//*[@href='/BackgammonAds'])[1]"),
//   need to fix >>>   TypeError: context.createDocumentFragment is not a function
//                            By.xpath(".//*[@href='#tab-ad-campaigns']"),
//                            By.xpath(".//*[@href='#tab-mobile']"),
//                            By.xpath(".//*[@href='#tab-banners']"),
//                            By.xpath(".//*[@href='#tab-promotions']"),
                        By.xpath("//.//*/div[@id='sub-menu-tab-0']//a[@href='/BackgammonAi']"),
                            By.xpath(".//*[@href='#tab-characters']"),
                            By.xpath(".//*[@href='#tab-strength']"),
                            By.xpath(".//*[@href='#tab-wgr-rules']"),
                        By.xpath(".//*[@href='/BackgammonAnnouncements']"),
                            By.xpath(".//*[@id='announcements-table']//tr[1]/td[3]"),
                            By.xpath(".//*[@class='btn-cancel-edit buttonH bBlack' and text() ='Back']"),
                        By.xpath(".//*[@href='/BackgammonBetRanges']"),
                        By.xpath(".//*[@href='/BackgammonCoupons']"),
                            By.xpath("(.//*[@id='coupons-table']//a[@title='Edit'])[1]"),
                            By.xpath(".//*[@class='btn-cancel-edit btn-back buttonH bBlack']"),
                            By.xpath(".//*[@href='#tab-templates']"),
                            By.xpath("(.//*[@id='coupons-templates-table']//a[@title='Edit'])[1]"),
                            By.xpath("(.//*[@class='btn-cancel-edit btn-back buttonH bBlack'])[2]"),
                        By.xpath(".//*[@href='/BackgammonDouble']"),
                        By.xpath(".//*[@href='/BackgammonDoubleOrNothing']"),
                            By.xpath(".//*[@href='#recoup-rules-layout']"),
                        By.xpath(".//*[@href='/BackgammonDisputeOrders']"),
                            By.xpath(".//*[@href='#tab-unfulfilled']"),
                        By.xpath(".//*[@href='/BackgammonFreeGifts']"),
                        By.xpath(".//*[@href='/BackgammonGameBettings']"),
                        By.xpath(".//*[@href='/BackgammonItems']"),
                            By.xpath("(.//*[@id='virtual-items-table']//a[@title='Edit'])[1]"),
                            By.xpath(".//*[@class='btn-cancel-edit btn-back buttonH bBlack' and text() ='Back']"),
                        By.xpath(".//*/div[@id='sub-menu-tab-0']//a[@href='/BackgammonItemStoreSegments']"),
                            By.xpath("(.//*[@id='segments-table']//a[@title='Edit'])[1]"),
                            By.xpath(".//*[@class='btn-cancel-edit btn-back buttonH bBlack' and text() ='Back']"),
                        By.xpath(".//*/div[@id='sub-menu-tab-0']//a[@href='/BackgammonLevels']"),
                        By.xpath(".//*/div[@id='sub-menu-tab-0']//a[@href='/BackgammonLocations']"),
                            By.xpath("(.//*[@id='locations-table']//a[@title='Edit'])[1]"),
                            By.xpath(".//*[@class='btn-cancel-edit btn-back buttonH bBlack' and text() ='Back']"),
                        By.xpath(".//*/div[@id='sub-menu-tab-0']//a[@href='/BackgammonLocationStore']"),
                        By.xpath(".//*/div[@id='sub-menu-tab-0']//a[@href='/BackgammonMinigames']"),
                            By.xpath(".//*[@href='#levels-widget-black-jack']"),
                            By.xpath(".//*[@href='#levels-widget-slots']"),
                        By.xpath(".//*/div[@id='sub-menu-tab-0']//a[@href='/BackgammonPremiumClub']"),
//                        By.xpath(".//*/div[@id='sub-menu-tab-0']//a[@href='/BackgammonPromotion']"),
//                            By.xpath(".//*[@href='#tab-mobile']"),
//                            By.xpath(".//*[@href='#tab-ads']"),
//                            By.xpath(".//*[@href='#tab-supersonic']"),
//                            By.xpath(".//*[@href='#tab-banners']"),
//                            By.xpath(".//*[@href='#tab-promotions']"),
                        By.xpath(".//*[@href='/BackgammonRatePopup']"),
                        By.xpath(".//*[@href='/BackgammonSlotMachine']"),
                            By.xpath(".//*[@href='#tab-days-in-row']"),
                            By.xpath(".//*[@href='#tab-slot-items']"),
                            By.xpath("(.//*[@id='slot-item-table']//a[@title='Edit'])[1]"),
                            By.xpath(".//*[@class='btn-cancel-edit btn-back buttonH bBlack' and text() ='Back']"),
                        By.xpath(".//*[@href='/BackgammonSpecialSale']"),
                            By.xpath(".//*[@id='special-sale-packages-table']/tbody/tr[1]/td[2]"),
                            By.xpath(".//*[@class='btn-cancel-edit btn-back buttonH bBlack' and text() ='Back']"),
                        By.xpath(".//*[@href='/BackgammonStore']"),
                            By.xpath(".//*[@id='store-packages-table']/tbody/tr[1]/td[2]"),
                            By.xpath(".//*[@id='coins-store-template']//a[text() ='Back']"),
                            By.xpath(".//*[@href='#tab-coins-item']"),
                            By.xpath("(.//*[@id='coins-items-table']//a[@title='Edit'])[1]"),
                            By.xpath(".//*[@id='coins-item-template']//a[text() ='Back']"),
                            By.xpath(".//*[@href='#tab-bundle-item']"),
                            By.xpath("(.//*[@id='bundle-items-table']//a[@title='Edit'])[1]"),
                            By.xpath(".//*[@id='bundle-item-template']//a[text() ='Back']"),
                            By.xpath(".//*[@href='#tab-scheduled-sales']"),
                            By.xpath("(.//*[@id='scheduled-sales-table']//a[@title='Edit'])[1]"),
                            By.xpath(".//*[@id='scheduled-sales-template']//a[text() ='Back']"),
                        By.xpath(".//*[@href='/BackgammonTips']"),
                        By.xpath(".//*[@href='/BackgammonUsers']"),
                                By.xpath("(.//*[@id='user-search-table']//a[@class='tablectrl_small bDefault' and contains(@title, 'Edit')])[1]"),
                                By.xpath(".//*[@class='btn-edit-back buttonH bBlack']"),
                            By.xpath(".//*[@href='#tab-suspected']"),
                                By.xpath("(.//*[@id='suspected-users-table']//td[@class='center'])[1]"),
                                By.xpath(".//*[@class='btn-edit-back buttonH bBlack']"),
                            By.xpath(".//*[@href='#tab-fake-gifts-users']"),

                        By.xpath(".//*[@href='/BackgammonUsersFilters']"),
                            By.xpath(".//*[@class='center' and contains(text(),'atlantis_1')]"),
                            By.xpath(".//*[@class='btn-back buttonH bBlack']"),
                        By.xpath(".//*[@href='/BackgammonVip']"),
//*********************************************************************
                    By.xpath(".//*[@href='#sub-menu-tab-1']"),
                        By.xpath(".//*[@href='/BackgammonAdBanners']"),
                        By.xpath(".//*[@href='/BackgammonAiChat']"),
                            By.xpath(".//*[@href='#tab-answers']"),
                            By.xpath(".//*[@href='#tab-question']"),
                        By.xpath(".//*[@href='/BackgammonBanWords']"),
                        By.xpath(".//*[@href='/BackgammonBonuses']"),
                            By.xpath(".//*[@href='#tab-welcome-bonus']"),
                            By.xpath(".//*[@href='#tab-zero-coins-bonus']"),
                            By.xpath(".//*[@href='#tab-pwf-bonus']"),
                            By.xpath(".//*[@href='#tab-mega-bonus']"),
                            By.xpath(".//*[@href='#tab-fb-login-bonus']"),
                        By.xpath(".//*[@href='/BackgammonFbAudiences']"),
                        By.xpath(".//*[@href='/BackgammonFbNotifications']"),
                        By.xpath(".//*[@href='/BackgammonPwNotifications']"),
                        By.xpath(".//*[@href='/BackgammonNews']"),
                        By.xpath(".//*[@href='/BackgammonFlashModules']"),
                        By.xpath(".//*[@href='/BackgammonLeaderboards']"),
                            By.xpath(".//*[@href='#tab-prizes']"),
                        By.xpath(".//*[@href='/BackgammonI18n']"),
                            By.xpath(".//*[@href='#tab-images']"),
                            By.xpath(".//*[@href='#tab-terms']"),
                        By.xpath(".//*[@href='/BackgammonRooms']"),
                        By.xpath(".//*[@href='/BackgammonSettings']"),
                        By.xpath(".//*[@href='/BackgammonTaunts']"),
//*********************************************************************
                    By.xpath(".//*[@href='#sub-menu-tab-2']"),
                        By.xpath(".//*[@href='/BackgammonCpaEvents']"),
                            By.xpath(".//*[@href='#tab-appnext']"),
                            By.xpath(".//*[@href='#tab-ads']"),
                        By.xpath(".//*[@href='/BackgammonItunesReceipts']"),
                        By.xpath(".//*[@href='/BackgammonGooglePlayReceipts']"),
                        By.xpath(".//*[@href='/BackgammonLogs/BGFlashLogs']"),
                        By.xpath(".//*[@href='/BackgammonLogs/BGLogs']")
//                }
//            )
    };


    @Test
    public void testPagesOnErrors(){
//  cycle in witch every page will be checked on Errors
//        for (int i=0;i<tabs.length;i++) {
//            Tab currentTab = tabs[i];
            for (int j=0; j<links.length;j++) {
                elements.clickButton(links[j]);
                elements.checkForErrorsFromLogs();
            }
        log.info("No_Errors_found_on_Pages");

//        }
    }

}




/*package tests;

import org.openqa.selenium.By;

public class Tab {
    public By TabLink;
    public By[] Pages;

    public Tab(By tabLink, By[] pages)
    {
        TabLink  = tabLink;
        Pages = pages;
    }
}*/



//    href='#sub-menu-tab-0'
//    private static By menuActions = By.xpath(".//*[@href='/BackgammonActions']");
//    private static By menuAds = By.xpath(".//*[@href='/BackgammonAds']");
//    private static By menuAi = By.xpath(".//*[@href='/BackgammonAi']");
//    private static By menuAnnouncements = By.xpath(".//*[@href='/BackgammonAnnouncements']");
//    private static By menuBetRanges = By.xpath(".//*[@href='/BackgammonBetRanges']");
//    private static By menuCoupons = By.xpath(".//*[@href='/BackgammonCoupons']");
//    private static By menuDouble = By.xpath(".//*[@href='/BackgammonDouble']");
//    private static By menuDoubleOrNothing = By.xpath(".//*[@href='/BackgammonDoubleOrNothing']");
//    private static By menuDisputeOrders = By.xpath(".//*[@href='/BackgammonDisputeOrders']");
//    private static By menuFreeGifts = By.xpath(".//*[@href='/BackgammonFreeGifts']");
//    private static By menuGameBettings = By.xpath(".//*[@href='/BackgammonGameBettings']");
//    private static By menuItems = By.xpath(".//*[@href='/BackgammonItems']");
//    private static By menuItemStoreSegments = By.xpath(".//*[@href='/BackgammonItemStoreSegments']");
//    private static By menuLevels = By.xpath(".//*[@href='/BackgammonLevels']");
//    private static By menuLocations = By.xpath(".//*[@href='/BackgammonLocations']");
//    private static By menuLocationStore = By.xpath(".//*[@href='/BackgammonLocationStore']");
//    private static By menuMiniGames = By.xpath(".//*[@href='/BackgammonMinigames']");
//    private static By menuPremiumClub = By.xpath(".//*[@href='/BackgammonPremiumClub']");
//    private static By menuRatePopup = By.xpath(".//*[@href='/BackgammonRatePopup']");
//    private static By menuSlotMachine = By.xpath(".//*[@href='/BackgammonSlotMachine']");
//    private static By menuSpecialSale = By.xpath(".//*[@href='/BackgammonSpecialSale']");
//    private static By menuStore = By.xpath(".//*[@href='/BackgammonStore']");
//    private static By menuTips = By.xpath(".//*[@href='/BackgammonTips']");
//    private static By menuUsers = By.xpath(".//*[@href='/BackgammonUsers']");
//    private static By menuUsersSegments = By.xpath(".//*[@href='/BackgammonUsersFilters']");
//    private static By menuVip = By.xpath(".//*[@href='/BackgammonVip']");
//
//    href='#sub-menu-tab-1'
//    private static By menuAdBanners = By.xpath(".//*[@href='/BackgammonAdBanners']");
//    private static By menuAiChat = By.xpath(".//*[@href='/BackgammonAiChat']");
//    private static By menuBanWords = By.xpath(".//*[@href='/BackgammonBanWords']");
//    private static By menuBonuses = By.xpath(".//*[@href='/BackgammonBonuses']");
//    private static By menuFacebookAudiences = By.xpath(".//*[@href='/BackgammonFbAudiences']");
//    private static By menuFacebookNotifications = By.xpath(".//*[@href='/BackgammonFbNotifications']");
//    private static By menuPushwooshNotifications = By.xpath(".//*[@href='/BackgammonPwNotifications']");
//    private static By menuFAQ = By.xpath(".//*[@href='/BackgammonNews']");
//    private static By menuFlashModules = By.xpath(".//*[@href='/BackgammonFlashModules']");
//    private static By menuLeaderboards = By.xpath(".//*[@href='/BackgammonLeaderboards']");
//    private static By menuLocalization = By.xpath(".//*[@href='/BackgammonI18n']");
//    private static By menuRooms = By.xpath(".//*[@href='/BackgammonRooms']");
//    private static By menuSettings = By.xpath(".//*[@href='/BackgammonSettings']");
//    private static By menuTaunts = By.xpath(".//*[@href='/BackgammonTaunts']");
//
//    href='#sub-menu-tab-2'
//    private static By menuCPAEvents = By.xpath(".//*[@href='/BackgammonCpaEvents']");
//    private static By menuItunesReceipts = By.xpath(".//*[@href='/BackgammonItunesReceipts']");
//    private static By menuGooglePlayReceipts = By.xpath(".//*[@href='/BackgammonGooglePlayReceipts']");
//    private static By menuClientLogs = By.xpath(".//*[@href='/BackgammonLogs/BGFlashLogs']");
//    private static By menuServerLogs = By.xpath(".//*[@href='/BackgammonLogs/BGLogs']");