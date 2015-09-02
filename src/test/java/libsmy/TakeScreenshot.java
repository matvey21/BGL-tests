package libsmy;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;

import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TakeScreenshot {
    private final String screenshotFolder = "/home/dima/Downloads/-autotest-data/Element_Screenshots/";
    protected WebDriver driver;
    WebElement element;



    public TakeScreenshot(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenShotOfElementOnFailureSecond() throws IOException {
        //Get entire page screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = ImageIO.read(screenshot);
        //Get the location of element on the page
        Point point = element.getLocation();
        //Get width and height of the element
        int eleWidth = element.getSize().getWidth();
        int eleHeight = element.getSize().getHeight();
        //Crop the entire page screenshot to get only element screenshot
        BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
        ImageIO.write(eleScreenshot, "png", screenshot);
        //Copy the element screenshot to disk
        FileUtils.copyFile(screenshot, new File("/home/dima/Downloads/-autotest-data/Element_Screenshots/"));
    }


    public void takeScreenShotOfPageOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println("testResult.getStatus = " + testResult.getStatus() + " -> failure");
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String randomUUID = UUID.randomUUID().toString();
            String randomHEX = Long.toHexString(Double.doubleToLongBits(Math.random()));
            FileUtils.copyFile(scrFile,
                    new File("/home/dima/Downloads/-autotest-data/" + driver.getTitle() + randomHEX + ".jpg"));
        }
    }

    public void shoot(WebElement element) throws IOException {
//        try {
//            driver = new Augmenter().augment(driver);
//        } catch (Exception ignored) {
//        }
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        Point p = element.getLocation();
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();
        Rectangle rect = new Rectangle(width, height);
        BufferedImage img = null;
        img = ImageIO.read(screen);
        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
        ImageIO.write(dest, "png", screen);

        FileUtils.copyFile(screen, new File(screenshotFolder + System.nanoTime() + ".png"));
    }

/* // Using shoot method in Base Test
    public void takeScreenShotOfElementOnFailure() throws IOException {
        TakeScreenshot screenshot = new TakeScreenshot(driver);
        screenshot.shoot(element);
        element.submit();
        System.out.println("Page title is: " + driver.getTitle());*/


/*    public void randomNumber() {
        Random rand = new Random();
        char[] chars = new char[16];
        for (int i = 0; i < chars.length; i++)
            chars[i] = (char) rand.nextInt(12345);
        if (!Character.isValidCodePoint(chars[i]))
            i--;
        String s = new String(chars);
    }*/

   /* public void SessionIdentifierGenerator {
        SecureRandom random = new SecureRandom();
        String nextSessionId() {
            return new BigInteger(130, random).toString(32);
        }
    }*/


}