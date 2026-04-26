package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HistoryPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class AppointmentHistoryTest extends BaseTest {

    private final ConfigReader config = new ConfigReader();
    private final String facility = "Tokyo CURA Healthcare Center";
    private final String date = "30/04/2026";
    private final String comment = "Test booking";

    private void loginAndBookAppointment() {
        HomePage home = new HomePage(BaseTest.getDriver());
        LoginPage loginPage = home.openLoginPage(config.getLoginUrl());
        loginPage.login(config.getUsername(), config.getPassword());

        home.selectFacility(facility);
        home.selectMedicaid();
        home.enterVisitDate(date);
        home.enterComment(comment);
        home.bookAppointment();
    }

    @Test
    public void verifyHistoryPageLoads() {
        loginAndBookAppointment();

        HomePage home = new HomePage(BaseTest.getDriver());
        HistoryPage history = home.goToHistory(config.getHistoryUrl());

        Assert.assertTrue(history.isHistoryPageLoaded(), "History page did not load");
    }

    @Test
    public void verifyHistoryContentDisplayed() {
        loginAndBookAppointment();

        HomePage home = new HomePage(BaseTest.getDriver());
        HistoryPage history = home.goToHistory(config.getHistoryUrl());

        Assert.assertTrue(history.containsText(facility), "Facility not found in history");
        Assert.assertTrue(history.containsText(date), "Date not found in history");
    }

    @Test
    public void verifyLatestAppointmentInHistory() {
        loginAndBookAppointment();

        HomePage home = new HomePage(BaseTest.getDriver());
        HistoryPage history = home.goToHistory(config.getHistoryUrl());

        Assert.assertTrue(history.containsText(facility), "Latest appointment facility not found");
        Assert.assertTrue(history.containsText(date), "Latest appointment date not found");
    }
}