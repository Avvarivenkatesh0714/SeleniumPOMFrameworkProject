package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.ConfigReader;

public class CartTest extends BaseClass {

    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;

    @BeforeMethod(alwaysRun = true)
    public void loginToApplication() {

        loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.getUsername(),
                ConfigReader.getPassword());

        homePage = new HomePage(driver);

    }

    @Test(priority = 1,
            description = "Verify Add Product To Cart")
    public void addProductToCartTest() {

        logger.info("Adding Backpack to Cart");

        homePage.addBackpackToCart();

        Assert.assertTrue(
                homePage.isCartBadgeDisplayed(),
                "Cart badge is not displayed.");

        Assert.assertEquals(
                homePage.getCartCount(),
                "1");

        logger.info("Product Added Successfully");

    }

    @Test(priority = 2,
            description = "Verify Cart Page")
    public void verifyCartPageTest() {

        logger.info("Opening Shopping Cart");

        homePage.addBackpackToCart();

        homePage.openShoppingCart();

        cartPage = new CartPage(driver);

        Assert.assertTrue(
                cartPage.isCartPageDisplayed(),
                "Cart page is not displayed.");

        logger.info("Cart Page Verified");

    }

    @Test(priority = 3,
            description = "Verify Product Details")
    public void verifyProductDetailsTest() {

        logger.info("Verifying Product Details");

        homePage.addBackpackToCart();

        homePage.openShoppingCart();

        cartPage = new CartPage(driver);

        Assert.assertEquals(
                cartPage.getProductName(),
                "Sauce Labs Backpack");

        Assert.assertEquals(
                cartPage.getProductQuantity(),
                "1");

        Assert.assertEquals(
                cartPage.getProductPrice(),
                "$29.99");

        logger.info("Product Details Verified");

    }

    @Test(priority = 4,
            description = "Verify Remove Product")
    public void removeProductTest() {

        logger.info("Removing Product");

        homePage.addBackpackToCart();

        homePage.openShoppingCart();

        cartPage = new CartPage(driver);

        cartPage.removeProduct();

        Assert.assertFalse(
                cartPage.isProductDisplayed(),
                "Product still exists in cart.");

        logger.info("Product Removed Successfully");

    }

    @Test(priority = 5,
            description = "Verify Continue Shopping")
    public void continueShoppingTest() {

        logger.info("Verifying Continue Shopping");

        homePage.addBackpackToCart();

        homePage.openShoppingCart();

        cartPage = new CartPage(driver);

        cartPage.clickContinueShopping();

        Assert.assertTrue(
                homePage.isInventoryPageDisplayed());

        logger.info("Continue Shopping Successful");

    }

}