package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    // ❗ initialize once
    private ConfigReader config = new ConfigReader();

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][]{
                {"John Doe", "ThisIsNotAPassword", "valid"},
                {"wrongUser", "wrongPass", "invalid"}
        };
    }

    @Test(dataProvider = "loginData")
    public void verifyLogin(String username, String password, String expected) {

        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.openLoginPage(config.getLoginUrl());

        lp.login(username, password);

        if (expected.equalsIgnoreCase("valid")) {

            HomePage home = new HomePage(getDriver());

            Assert.assertTrue(home.isLoginSuccessful(),
                    "Expected successful login but failed");

        } else {

            Assert.assertTrue(lp.isLoginPageDisplayed(),
                    "Expected login failure but user logged in");
        }
    }
    @Test
    public void verifyLogoutRedirectsToHomePage() {

        ConfigReader config = new ConfigReader();

        HomePage hp = new HomePage(getDriver());

        LoginPage lp = hp.openLoginPage(config.getLoginUrl());
        lp.login(config.getUsername(), config.getPassword());

        LoginPage loginPage = hp.logout();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Logout failed - Login page not displayed");
    }

    @Test
    public void verifyProtectedPageRedirectToLogin() {

        HomePage hp = new HomePage(getDriver());
        hp.clickMakeAppointment();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("profile.php#login"),
                "User was not redirected to login page");
    }
    
}