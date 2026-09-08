package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {

    private WebDriver driver;
    private WebDriverWait wait;

    public WaitUtility(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigReader.getExplicitWait()));

    }

    // Wait until element is visible
    public WebElement waitForElementToBeVisible(By locator) {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));

    }

    // Wait until WebElement is visible
    public WebElement waitForVisibility(WebElement element) {

        return wait.until(
                ExpectedConditions.visibilityOf(element));

    }

    // Wait until element is clickable
    public WebElement waitForElementToBeClickable(By locator) {

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator));

    }

    // Wait until WebElement is clickable
    public WebElement waitForClickability(WebElement element) {

        return wait.until(
                ExpectedConditions.elementToBeClickable(element));

    }

    // Wait until title contains text
    public boolean waitForTitleContains(String title) {

        return wait.until(
                ExpectedConditions.titleContains(title));

    }

    // Wait until URL contains text
    public boolean waitForUrlContains(String url) {

        return wait.until(
                ExpectedConditions.urlContains(url));

    }

    // Wait until element disappears
    public boolean waitForInvisibility(By locator) {

        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator));

    }

    // Wait until element is present in DOM
    public WebElement waitForPresence(By locator) {

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator));

    }

    // Wait until text is present
    public boolean waitForText(WebElement element, String text) {

        return wait.until(
                ExpectedConditions.textToBePresentInElement(element, text));

    }

}