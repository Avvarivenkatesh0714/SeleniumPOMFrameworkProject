package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitUtility;

public class HomePage {

    private WebDriver driver;
    private WaitUtility wait;

    public HomePage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WaitUtility(driver);

        PageFactory.initElements(driver, this);

    }

    // ===========================
    // Web Elements
    // ===========================

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackButton;

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeBackpackButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCart;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(id = "item_4_title_link")
    private WebElement backpackProduct;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    // ===========================
    // Actions
    // ===========================

    public String getPageTitleText() {

        return wait.waitForVisibility(pageTitle).getText();

    }

    public boolean isInventoryPageDisplayed() {

        return wait.waitForVisibility(pageTitle)
                .getText()
                .equalsIgnoreCase("Products");

    }

    public void addBackpackToCart() {

        wait.waitForClickability(addBackpackButton).click();

    }

    public void removeBackpackFromCart() {

        wait.waitForClickability(removeBackpackButton).click();

    }

    public void openShoppingCart() {

        wait.waitForClickability(shoppingCart).click();

    }

    public void openMenu() {

        wait.waitForClickability(menuButton).click();

    }

    public void logout() {

        openMenu();

        wait.waitForClickability(logoutButton).click();

    }

    public void openBackpackProduct() {

        wait.waitForClickability(backpackProduct).click();

    }

    public String getCartCount() {

        return wait.waitForVisibility(cartBadge).getText();

    }

    public boolean isCartBadgeDisplayed() {

        return cartBadge.isDisplayed();

    }

    public void sortByNameAZ() {

        org.openqa.selenium.support.ui.Select select =
                new org.openqa.selenium.support.ui.Select(
                        wait.waitForVisibility(sortDropdown));

        select.selectByVisibleText("Name (A to Z)");

    }

    public void sortByNameZA() {

        org.openqa.selenium.support.ui.Select select =
                new org.openqa.selenium.support.ui.Select(
                        wait.waitForVisibility(sortDropdown));

        select.selectByVisibleText("Name (Z to A)");

    }

    public void sortByPriceLowHigh() {

        org.openqa.selenium.support.ui.Select select =
                new org.openqa.selenium.support.ui.Select(
                        wait.waitForVisibility(sortDropdown));

        select.selectByVisibleText("Price (low to high)");

    }

    public void sortByPriceHighLow() {

        org.openqa.selenium.support.ui.Select select =
                new org.openqa.selenium.support.ui.Select(
                        wait.waitForVisibility(sortDropdown));

        select.selectByVisibleText("Price (high to low)");

    }

}