package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {
        // Prevent object creation
    }

    public static void initializeDriver() {

        String browser = ConfigReader.getBrowser().trim().toLowerCase();
        boolean headless = ConfigReader.isHeadless();

        switch (browser) {

            case "chrome":

                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();

                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }

                chromeOptions.addArguments("--remote-allow-origins=*");

                driver.set(new ChromeDriver(chromeOptions));
                break;

            case "edge":

                WebDriverManager.edgedriver().setup();

                EdgeOptions edgeOptions = new EdgeOptions();

                if (headless) {
                    edgeOptions.addArguments("--headless=new");
                }

                driver.set(new EdgeDriver(edgeOptions));
                break;

            case "firefox":

                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }

                driver.set(new FirefoxDriver(firefoxOptions));
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser : " + browser);
        }

        getDriver().manage().timeouts().implicitlyWait(
                Duration.ofSeconds(ConfigReader.getImplicitWait()));

        getDriver().manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));

        if (ConfigReader.isWindowMaximized()) {
            getDriver().manage().window().maximize();
        }

        getDriver().get(ConfigReader.getApplicationURL());
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {

            driver.get().quit();
            driver.remove();

        }
    }

}