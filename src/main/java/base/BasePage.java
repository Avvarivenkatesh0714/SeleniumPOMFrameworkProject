package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitUtility;

public class BasePage {

    protected WebDriver driver;
    protected WaitUtility wait;

    public BasePage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WaitUtility(driver);

        PageFactory.initElements(driver, this);

    }

    /**
     * Click Element
     */
    protected void click(WebElement element) {

        wait.waitForClickability(element).click();

    }

    /**
     * Enter Text
     */
    protected void type(WebElement element, String value) {

        WebElement webElement = wait.waitForVisibility(element);

        webElement.clear();

        webElement.sendKeys(value);

    }

    /**
     * Read Text
     */
    protected String getText(WebElement element) {

        return wait.waitForVisibility(element).getText();

    }

    /**
     * Check Visibility
     */
    protected boolean isDisplayed(WebElement element) {

        return wait.waitForVisibility(element).isDisplayed();

    }

    /**
     * Get Current URL
     */
    protected String getCurrentURL() {

        return driver.getCurrentUrl();

    }

    /**
     * Get Page Title
     */
    protected String getPageTitle() {

        return driver.getTitle();

    }

}