package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitUtility;

public class CheckoutPage {

    private WebDriver driver;
    private WaitUtility wait;

    public CheckoutPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WaitUtility(driver);

        PageFactory.initElements(driver, this);

    }

    // ===========================
    // Checkout Information Page
    // ===========================

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "first-name")
    private WebElement txtFirstName;

    @FindBy(id = "last-name")
    private WebElement txtLastName;

    @FindBy(id = "postal-code")
    private WebElement txtPostalCode;

    @FindBy(id = "continue")
    private WebElement btnContinue;

    @FindBy(id = "cancel")
    private WebElement btnCancel;

    @FindBy(css = "h3[data-test='error']")
    private WebElement lblError;

    // ===========================
    // Checkout Overview Page
    // ===========================

    @FindBy(id = "finish")
    private WebElement btnFinish;

    @FindBy(id = "cancel")
    private WebElement btnCancelOverview;

    @FindBy(className = "summary_total_label")
    private WebElement lblTotalPrice;

    // ===========================
    // Checkout Complete Page
    // ===========================

    @FindBy(className = "complete-header")
    private WebElement lblOrderComplete;

    @FindBy(id = "back-to-products")
    private WebElement btnBackHome;

    // ===========================
    // Validation
    // ===========================

    public boolean isCheckoutPageDisplayed() {

        return wait.waitForVisibility(pageTitle)
                .getText()
                .equalsIgnoreCase("Checkout: Your Information");

    }

    public String getCheckoutTitle() {

        return wait.waitForVisibility(pageTitle).getText();

    }

    // ===========================
    // Enter Details
    // ===========================

    public void enterFirstName(String firstName) {

        WebElement first =
                wait.waitForVisibility(txtFirstName);

        first.clear();
        first.sendKeys(firstName);

    }

    public void enterLastName(String lastName) {

        WebElement last =
                wait.waitForVisibility(txtLastName);

        last.clear();
        last.sendKeys(lastName);

    }

    public void enterPostalCode(String postalCode) {

        WebElement zip =
                wait.waitForVisibility(txtPostalCode);

        zip.clear();
        zip.sendKeys(postalCode);

    }

    // ===========================
    // Complete Information
    // ===========================

    public void enterCheckoutInformation(
            String firstName,
            String lastName,
            String postalCode) {

        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);

    }

    // ===========================
    // Buttons
    // ===========================

    public void clickContinue() {

        wait.waitForClickability(btnContinue).click();

    }

    public void clickFinish() {

        wait.waitForClickability(btnFinish).click();

    }

    public void clickCancel() {

        wait.waitForClickability(btnCancel).click();

    }

    public void clickBackHome() {

        wait.waitForClickability(btnBackHome).click();

    }

    // ===========================
    // Error Message
    // ===========================

    public String getErrorMessage() {

        return wait.waitForVisibility(lblError).getText();

    }

    // ===========================
    // Order Complete
    // ===========================

    public boolean isOrderCompleted() {

        return wait.waitForVisibility(lblOrderComplete)
                .getText()
                .equalsIgnoreCase("Thank you for your order!");

    }

    public String getOrderCompleteMessage() {

        return wait.waitForVisibility(lblOrderComplete).getText();

    }

    // ===========================
    // Total Price
    // ===========================

    public String getTotalPrice() {

        return wait.waitForVisibility(lblTotalPrice).getText();

    }

}