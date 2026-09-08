package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream(
                    System.getProperty("user.dir")
                            + "/src/main/resources/config.properties");

            properties.load(fis);
            fis.close();

        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties file", e);
        }
    }

    public static String getApplicationURL() {
        return properties.getProperty("app.url");
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    public static String getUsername() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }

    public static String getInvalidUsername() {
        return properties.getProperty("invalid.username");
    }

    public static String getInvalidPassword() {
        return properties.getProperty("invalid.password");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicit.wait"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("page.load.timeout"));
    }

    public static boolean isWindowMaximized() {
        return Boolean.parseBoolean(
                properties.getProperty("maximize.window"));
    }

    public static String getScreenshotPath() {
        return properties.getProperty("screenshot.path");
    }

    public static String getReportPath() {
        return properties.getProperty("report.path");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(
                properties.getProperty("headless"));
    }

}