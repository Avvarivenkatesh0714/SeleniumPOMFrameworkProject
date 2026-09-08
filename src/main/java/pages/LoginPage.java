package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitUtility;

public class LoginPage {

    private WebDriver driver;
    private WaitUtility wait;

    // Constructor
    public LoginPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WaitUtility(driver);

        PageFactory.initElements(driver, this);

    }

    // ===========================
    // Web Elements
    // ===========================

    @FindBy(id = "user-name")
    private WebElement txtUsername;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(id = "login-button")
    private WebElement btnLogin;

    @FindBy(css = "h3[data-test='error']")
    private WebElement lblErrorMessage;

    @FindBy(className = "login_logo")
    private WebElement lblLoginLogo;

    // ===========================
    // Page Actions
    // ===========================

    /**
     * Enter Username
     */
    public void enterUsername(String username) {

        wait.waitForVisibility(txtUsername).clear();
        txtUsername.sendKeys(username);

    }

    /**
     * Enter Password
     */
    public void enterPassword(String password) {

        wait.waitForVisibility(txtPassword).clear();
        txtPassword.sendKeys(password);

    }

    /**
     * Click Login Button
     */
    public void clickLoginButton() {

        wait.waitForClickability(btnLogin).click();

    }

    /**
     * Perform Login
     */
    public void login(String username, String password) {

        enterUsername(username);
        enterPassword(password);
        clickLoginButton();

    }

    /**
     * Get Error Message
     */
    public String getErrorMessage() {

        return wait.waitForVisibility(lblErrorMessage).getText();

    }

    /**
     * Check Login Logo Displayed
     */
    public boolean isLoginLogoDisplayed() {

        return wait.waitForVisibility(lblLoginLogo).isDisplayed();

    }

    /**
     * Get Page Title
     */
    public String getPageTitle() {

        return driver.getTitle();

    }

    /**
     * Get Current URL
     */
    public String getCurrentURL() {

        return driver.getCurrentUrl();

    }

}