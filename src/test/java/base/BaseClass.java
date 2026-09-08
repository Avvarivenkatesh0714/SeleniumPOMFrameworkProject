package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utilities.DriverFactory;

public class BaseClass {

    protected WebDriver driver;

    protected Logger logger;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        logger = LogManager.getLogger(this.getClass());

        logger.info("========================================");
        logger.info("Starting Test Execution");
        logger.info("========================================");

        DriverFactory.initializeDriver();

        driver = DriverFactory.getDriver();

        logger.info("Browser Launched Successfully");

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {

            logger.info("Closing Browser");

            DriverFactory.quitDriver();

            logger.info("Browser Closed Successfully");

        }

    }

}
