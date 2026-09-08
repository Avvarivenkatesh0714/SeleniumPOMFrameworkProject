package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    private ScreenshotUtil() {
        // Prevent object creation
    }

    /**
     * Capture screenshot
     *
     * @param driver
     * @param testName
     * @return Screenshot absolute path
     */
    public static String captureScreenshot(WebDriver driver, String testName) {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());

        Path screenshotDirectory = Paths.get(ConfigReader.getScreenshotPath());
        if (!screenshotDirectory.isAbsolute()) {
            screenshotDirectory = Paths.get(System.getProperty("user.dir"), ConfigReader.getScreenshotPath());
        }

        try {
            Files.createDirectories(screenshotDirectory);
        } catch (IOException e) {
            throw new RuntimeException("Unable to create screenshot directory: " + screenshotDirectory, e);
        }

        String destination = screenshotDirectory
                .resolve(testName + "_" + timeStamp + ".png")
                .toString();

        File sourceFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        File destinationFile = new File(destination);

        try {

            FileUtils.copyFile(sourceFile, destinationFile);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to save screenshot.", e);

        }

        return destination;

    }

}