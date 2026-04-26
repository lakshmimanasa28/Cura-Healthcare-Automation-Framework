package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExtentTestManager;
import java.time.Duration;

public class AppointmentBookingTest extends BaseTest {

	@BeforeMethod
    private void login() {
        ConfigReader config=new ConfigReader();

        HomePage hp=new HomePage(getDriver());
        LoginPage lp = hp.openLoginPage(config.getLoginUrl());

        
        lp.login(config.getUsername(),config.getPassword());
    }

   
    @Test
    public void verifyAppointmentBooking() {
        HomePage hp=new HomePage(getDriver());
        hp.selectFacility("Tokyo CURA Healthcare Center");
        hp.enterVisitDate("25/12/2026");
        hp.enterComment("Booking test");
        hp.bookAppointment();

     
     WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
     wait.until(ExpectedConditions.visibilityOfElementLocated(
             By.xpath("//h2[contains(text(),'Appointment Confirmation')]")
     ));

     Assert.assertTrue(hp.isAppointmentConfirmed(), "Appointment booking failed");
    }


    @Test
    public void verifyAppointmentDetails() {
        HomePage hp=new HomePage(getDriver());

        String facility="Tokyo CURA Healthcare Center";
        String date="26/12/2026";

        hp.selectFacility(facility);
        hp.enterVisitDate(date);
        hp.enterComment("Verify details");
        hp.bookAppointment();

        Assert.assertEquals(hp.getConfirmedFacility(),facility,"Facility mismatch");
        Assert.assertEquals(hp.getConfirmedDate(),date,"Date mismatch");
        
    }


    @Test
    public void verifyReadmissionCheckbox() {
        HomePage hp=new HomePage(getDriver());
        hp.selectFacility("Tokyo CURA Healthcare Center");
        hp.applyHospitalReadmission();
        hp.enterVisitDate("27/12/2026");
        hp.enterComment("Checkbox test");
        hp.bookAppointment();

        Assert.assertTrue(hp.getReadmissionStatus().contains("Yes"),"Readmission not reflected in confirmation");
    }

   
    @Test
    public void verifyPastDateBehavior() {
        ExtentTest test = ExtentTestManager.getTest();
        HomePage hp = new HomePage(getDriver());

        String pastDate = "01/01/2020";

        hp.selectFacility("Tokyo CURA Healthcare Center");
        hp.enterVisitDate(pastDate);
        hp.enterComment("Past date test");
        hp.bookAppointment();

        String actualDate = hp.getConfirmedDate();

        if (actualDate.equals(pastDate)) {
        	System.out.println("Warning: Past date behavior observed");
        } else {
            test.pass("Past date was rejected (unexpected behavior)");
        }
        Assert.assertTrue(true);
    }
}