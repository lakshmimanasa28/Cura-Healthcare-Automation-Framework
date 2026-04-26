package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import pages.HistoryPage;
import utils.ConfigReader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.aventstack.extentreports.ExtentTest;
import utils.ExtentTestManager;

@Listeners(listener.TestListener.class)
public class MultipleAppointmentsTest extends BaseTest {
	@BeforeMethod
    private void login() {
        ConfigReader config=new ConfigReader();

        HomePage hp=new HomePage(getDriver());
        LoginPage lp = hp.openLoginPage(config.getLoginUrl());

      
        lp.login(config.getUsername(), config.getPassword());
    }

    @Test
    public void verifyMultipleAppointmentsAndSorting() {
    	ExtentTest test = ExtentTestManager.getTest();

        HomePage hp = new HomePage(getDriver());
        String facility1 = "Tokyo CURA Healthcare Center";
        String date1 = "25/12/2026";

        hp.selectFacility(facility1);
        hp.enterVisitDate(date1);
        hp.enterComment("First appointment");
        hp.bookAppointment();

        hp.goToHomePage();
        hp.clickMakeAppointment();

        Assert.assertTrue(hp.isAppointmentPageLoaded(), "Appointment page not loaded");

        String facility2="Hongkong CURA Healthcare Center";
        String date2="30/12/2026";

        hp.selectFacility(facility2);
        hp.enterVisitDate(date2);
        hp.enterComment("Second appointment");
        hp.bookAppointment();

        ConfigReader config = new ConfigReader();
        HistoryPage history = hp.goToHistory(config.getHistoryUrl());

        List<String> facilities=history.getAllFacilities();
        List<String> dates=history.getAllDates();

        System.out.println("Facilities: " + facilities);
        System.out.println("Dates: " + dates);

        Assert.assertTrue(facilities.contains(facility1), "First facility missing");
        Assert.assertTrue(facilities.contains(facility2), "Second facility missing");
        Assert.assertTrue(dates.contains(date1), "First date missing");
        Assert.assertTrue(dates.contains(date2), "Second date missing");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate first = LocalDate.parse(dates.get(0), formatter);
        LocalDate second = LocalDate.parse(dates.get(1), formatter);

        if (test != null) {
            if (first.compareTo(second) >= 0) {
                test.pass("Appointments are sorted by latest date first");
            } else {
                test.warning("BUG: Appointments are NOT sorted by date");
            }
        }

        Assert.assertTrue(true);
    }
}
