package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;
import pages.LoginPage;
import utilities.ConfigReader;

public class LoginTest extends BaseClass {

    @Test(priority = 1,
            description = "Verify Login Page is Displayed")
    public void verifyLoginPageTest() {

        logger.info("Verifying Login Page");

        LoginPage loginPage = new LoginPage(driver);

        Assert.assertTrue(
                loginPage.isLoginLogoDisplayed(),
                "Login page is not displayed.");

        logger.info("Login Page Verified Successfully");

    }

    @Test(priority = 2,
            description = "Verify Page Title")
    public void verifyPageTitleTest() {

        logger.info("Verifying Page Title");

        LoginPage loginPage = new LoginPage(driver);

        Assert.assertEquals(
                loginPage.getPageTitle(),
                "Swag Labs");

        logger.info("Page Title Verified Successfully");

    }

    @Test(priority = 3,
            description = "Verify Valid Login")
    public void validLoginTest() {

        logger.info("Starting Valid Login Test");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.getUsername(),
                ConfigReader.getPassword());

        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(
                homePage.isInventoryPageDisplayed(),
                "Login Failed.");

        logger.info("Valid Login Successful");

    }

    @Test(priority = 4,
            description = "Verify Invalid Login")
    public void invalidLoginTest() {

        logger.info("Starting Invalid Login Test");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.getInvalidUsername(),
                ConfigReader.getInvalidPassword());

        String actualError =
                loginPage.getErrorMessage();

        String expectedError =
                "Epic sadface: Username and password do not match any user in this service";

        Assert.assertEquals(
                actualError,
                expectedError);

        logger.info("Invalid Login Error Verified");

    }

}