package libsmy;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

//import com.pack.sample.TestBase;

public class TestListener extends TestListenerAdapter {
    WebDriver driver;
    private static String fileSeparator = System.getProperty("file.separator");

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("***** Error " + result.getName() + " test has failed *****");

//        driver = TestBase.getDriver();

        String testClassName = getTestClassName(result.getInstanceName()).trim();

        String testMethodName = result.getName().toString().trim();
        String screenShotName = testMethodName + ".png";

        if (driver != null) {
            String imagePath = ".." + fileSeparator + "Screenshots"
                    + fileSeparator + "Results" + fileSeparator + testClassName
                    + fileSeparator
                    + takeScreenShot(driver, screenShotName, testClassName);
            System.out.println("Screenshot can be found : " + imagePath);
        }
    }

    public static String takeScreenShot(WebDriver driver,
                                        String screenShotName, String testName) {
        try {
            File file = new File("Screenshots" + fileSeparator + "Results");
            if (!file.exists()) {
                System.out.println("File created " + file);
                file.mkdir();
            }

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File targetFile = new File("Screenshots" + fileSeparator + "Results" + fileSeparator + testName, screenShotName);
            FileUtils.copyFile(screenshotFile, targetFile);

            return screenShotName;
        } catch (Exception e) {
            System.out.println("An exception occured while taking screenshot " + e.getCause());
            return null;
        }
    }

    public String getTestClassName(String testName) {
        String[] reqTestClassname = testName.split("\\.");
        int i = reqTestClassname.length - 1;
        System.out.println("Required Test Name : " + reqTestClassname[i]);
        return reqTestClassname[i];
    }

}