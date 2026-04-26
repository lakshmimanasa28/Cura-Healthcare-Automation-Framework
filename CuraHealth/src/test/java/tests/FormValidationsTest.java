package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class FormValidationsTest extends BaseTest {

    @Test
    public void verifyEmptyLoginValidation() {
        ConfigReader config = new ConfigReader();
        HomePage hp = new HomePage(getDriver());

        LoginPage lp = hp.openLoginPage(config.getLoginUrl());
        lp.login("", "");

        Assert.assertTrue(lp.isLoginPageDisplayed(),
                "Login should not succeed with empty credentials");
    }

    @Test
    public void verifyEmptyDateValidation() {
        ConfigReader config = new ConfigReader();
        HomePage hp = new HomePage(getDriver());

        LoginPage lp = hp.openLoginPage(config.getLoginUrl());
        lp.login(config.getUsername(), config.getPassword());

        Assert.assertTrue(hp.isAppointmentPageLoaded(), "Appointment page not loaded");

        hp.selectFacility("Tokyo CURA Healthcare Center");
        hp.enterComment("Test");
        hp.enterVisitDate("");   // empty date
        hp.bookAppointment();

        Assert.assertFalse(hp.isAppointmentConfirmed(),
                "Appointment should not be confirmed when date is empty");
    }

    @Test
    public void verifyLongCommentAccepted() {
        ConfigReader config = new ConfigReader();
        HomePage hp = new HomePage(getDriver());

        LoginPage lp = hp.openLoginPage(config.getLoginUrl());
        lp.login(config.getUsername(), config.getPassword());

        String longComment =
                "This is a very long comment for testing whether the application saves the entire text without truncation. "
              + "It should remain visible on the confirmation page.";

        hp.selectFacility("Tokyo CURA Healthcare Center");
        hp.enterVisitDate("25/12/2026");
        hp.enterComment(longComment);
        hp.bookAppointment();

        String savedComment = hp.getConfirmedComment().replaceAll("\\s+", " ").trim();
        String expected = longComment.replaceAll("\\s+", " ").trim();

        Assert.assertEquals(savedComment, expected, "Comment not saved properly");
    }
}