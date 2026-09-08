package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;
import pages.LoginPage;
import utilities.ConfigReader;

public class LogoutTest extends BaseClass {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void loginBeforeLogout() {

        logger.info("Logging into application");

        loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.getUsername(),
                ConfigReader.getPassword());

        homePage = new HomePage(driver);

        Assert.assertTrue(
                homePage.isInventoryPageDisplayed(),
                "Login failed. Unable to continue logout test.");

    }

    @Test(priority = 1,
            description = "Verify Successful Logout")
    public void logoutTest() {

        logger.info("Performing Logout");

        homePage.logout();

        loginPage = new LoginPage(driver);

        Assert.assertTrue(
                loginPage.isLoginLogoDisplayed(),
                "Login page is not displayed after logout.");

        logger.info("Logout Successful");

    }

    @Test(priority = 2,
            description = "Verify Login URL After Logout")
    public void verifyLogoutURLTest() {

        logger.info("Verifying Logout URL");

        homePage.logout();

        loginPage = new LoginPage(driver);

        Assert.assertEquals(
                loginPage.getCurrentURL(),
                ConfigReader.getApplicationURL());

        logger.info("Logout URL Verified");

    }

    @Test(priority = 3,
            description = "Verify Login Logo After Logout")
    public void verifyLoginLogoAfterLogoutTest() {

        logger.info("Verifying Login Logo");

        homePage.logout();

        loginPage = new LoginPage(driver);

        Assert.assertTrue(
                loginPage.isLoginLogoDisplayed());

        logger.info("Login Logo Verified");

    }

    @Test(priority = 4,
            description = "Verify Login Page Title After Logout")
    public void verifyLoginTitleAfterLogoutTest() {

        logger.info("Verifying Login Page Title");

        homePage.logout();

        loginPage = new LoginPage(driver);

        Assert.assertEquals(
                loginPage.getPageTitle(),
                "Swag Labs");

        logger.info("Login Page Title Verified");

    }

}