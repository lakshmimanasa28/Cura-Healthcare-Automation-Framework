package utils;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String testName) {

        String dirPath=System.getProperty("user.dir") + "/screenshots/";
        String filePath=dirPath + testName + ".png";

        File dir=new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
