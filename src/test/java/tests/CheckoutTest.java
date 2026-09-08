package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.ConfigReader;

public class CheckoutTest extends BaseClass {

    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod(alwaysRun = true)
    public void loginAndNavigateToCheckout() {

        loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.getUsername(),
                ConfigReader.getPassword());

        homePage = new HomePage(driver);

        homePage.addBackpackToCart();

        homePage.openShoppingCart();

        cartPage = new CartPage(driver);

        cartPage.clickCheckout();

        checkoutPage = new CheckoutPage(driver);

    }

    @Test(priority = 1,
            description = "Verify Checkout Page")
    public void verifyCheckoutPageTest() {

        logger.info("Verifying Checkout Page");

        Assert.assertTrue(
                checkoutPage.isCheckoutPageDisplayed());

        logger.info("Checkout Page Verified");

    }

    @Test(priority = 2,
            description = "Verify Checkout Information")
    public void enterCheckoutInformationTest() {

        logger.info("Entering Checkout Information");

        checkoutPage.enterCheckoutInformation(
                "John",
                "David",
                "560001");

        checkoutPage.clickContinue();

        Assert.assertTrue(
                checkoutPage.getTotalPrice().contains("Total"));

        logger.info("Checkout Information Entered");

    }

    @Test(priority = 3,
            description = "Verify Complete Order")
    public void completeOrderTest() {

        logger.info("Completing Order");

        checkoutPage.enterCheckoutInformation(
                "John",
                "David",
                "560001");

        checkoutPage.clickContinue();

        checkoutPage.clickFinish();

        Assert.assertTrue(
                checkoutPage.isOrderCompleted());

        Assert.assertEquals(
                checkoutPage.getOrderCompleteMessage(),
                "Thank you for your order!");

        logger.info("Order Completed Successfully");

    }

    @Test(priority = 4,
            description = "Verify Checkout Validation")
    public void checkoutValidationTest() {

        logger.info("Checking Validation Message");

        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                "Error: First Name is required");

        logger.info("Validation Verified");

    }

    @Test(priority = 5,
            description = "Verify Back Home")
    public void backHomeTest() {

        logger.info("Verifying Back Home");

        checkoutPage.enterCheckoutInformation(
                "John",
                "David",
                "560001");

        checkoutPage.clickContinue();

        checkoutPage.clickFinish();

        checkoutPage.clickBackHome();

        Assert.assertTrue(
                new HomePage(driver).isInventoryPageDisplayed());

        logger.info("Returned to Home Page");

    }

}