package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitUtility;

public class CartPage {

    private WebDriver driver;
    private WaitUtility wait;

    public CartPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WaitUtility(driver);

        PageFactory.initElements(driver, this);

    }

    // ===================================
    // Web Elements
    // ===================================

    @FindBy(className = "title")
    private WebElement cartTitle;

    @FindBy(className = "inventory_item_name")
    private WebElement productName;

    @FindBy(className = "inventory_item_price")
    private WebElement productPrice;

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(className = "cart_quantity")
    private WebElement quantity;

    // ===================================
    // Page Validation
    // ===================================

    public boolean isCartPageDisplayed() {

        return wait.waitForVisibility(cartTitle)
                .getText()
                .equalsIgnoreCase("Your Cart");

    }

    public String getCartTitle() {

        return wait.waitForVisibility(cartTitle).getText();

    }

    // ===================================
    // Product Details
    // ===================================

    public String getProductName() {

        return wait.waitForVisibility(productName).getText();

    }

    public String getProductPrice() {

        return wait.waitForVisibility(productPrice).getText();

    }

    public String getProductQuantity() {

        return wait.waitForVisibility(quantity).getText();

    }

    // ===================================
    // Cart Actions
    // ===================================

    public void removeProduct() {

        wait.waitForClickability(removeButton).click();

    }

    public void clickContinueShopping() {

        wait.waitForClickability(continueShoppingButton).click();

    }

    public void clickCheckout() {

        wait.waitForClickability(checkoutButton).click();

    }

    // ===================================
    // Validation Methods
    // ===================================

    public boolean isCheckoutButtonDisplayed() {

        return wait.waitForVisibility(checkoutButton).isDisplayed();

    }

    public boolean isContinueShoppingDisplayed() {

        return wait.waitForVisibility(continueShoppingButton).isDisplayed();

    }

    public boolean isProductDisplayed() {

        return wait.waitForVisibility(productName).isDisplayed();

    }

}