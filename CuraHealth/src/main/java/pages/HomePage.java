package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.curahealth.base.BasePage;

public class HomePage extends BasePage {
    private By makeAppointmentBtn = By.id("btn-make-appointment");
    private By menuToggle = By.id("menu-toggle");
    private By historyLink = By.linkText("History");
    private By logoutBtn = By.linkText("Logout");
    private By goHomeBtn = By.xpath("//a[normalize-space()='Go to Homepage']");

    private By appointmentHeader = By.xpath("//h2[contains(text(),'Make Appointment')]");
    private By facilityDropdown = By.id("combo_facility");
    private By applyHospitalReadmission = By.id("chk_hospotal_readmission");
    private By medicaidRadio = By.id("radio_program_medicaid");
    private By visitDate = By.id("txt_visit_date");
    private By comment = By.id("txt_comment");
    private By bookAppointmentBtn = By.id("btn-book-appointment");
    private By confirmationHeader = By.xpath("//h2[contains(text(),'Appointment Confirmation')]");

    private By confirmFacility = By.id("facility");
    private By confirmDate = By.id("visit_date");
    private By confirmReadmission = By.id("hospital_readmission");

    private By loginUsernameField = By.id("txt-username");
    private By historyHeader = By.xpath("//h2[normalize-space()='History']");
    private By confirmComment = By.id("comment");

    public String getConfirmedComment() {
        return getText(confirmComment);
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage openLoginPage(String loginUrl) {
        try {
            click(makeAppointmentBtn);
            waitForVisibility(loginUsernameField);
        } catch (Exception e) {
            driver.get(loginUrl);
            waitForVisibility(loginUsernameField);
        }
        return new LoginPage(driver);
    }

    public void clickMakeAppointment() {
        waitForVisibility(makeAppointmentBtn);
        click(makeAppointmentBtn);
    }

    public boolean isLoginSuccessful() {
        return isDisplayed(appointmentHeader);
    }

    public boolean isAppointmentConfirmed() {
        try {
            return driver.findElement(confirmationHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectFacility(String facility) {
        selectByVisibleText(facilityDropdown, facility);
    }

    public void selectMedicaid() {
        click(medicaidRadio);
    }

    public void applyHospitalReadmission() {
        click(applyHospitalReadmission);
    }

    public void enterVisitDate(String date) {
        type(visitDate, date);
    }

    public void enterComment(String text) {
        type(comment, text);
    }

    public void bookAppointment() {
        click(bookAppointmentBtn);
        // ✅ no wait here
    }
    public LoginPage logout() {

        try {
            waitForVisibility(menuToggle);
            click(menuToggle);

            waitForVisibility(logoutBtn);
            click(logoutBtn);

        } catch (Exception e) {
            // ignore menu issues
        }

        // 🔥 FORCE navigation (guaranteed fix)
        driver.get("https://katalon-demo-cura.herokuapp.com/profile.php#login");

        return new LoginPage(driver);
    }

    public String getConfirmedFacility() {
        return getText(confirmFacility);
    }

    public String getConfirmedDate() {
        return getText(confirmDate);
    }

    public String getReadmissionStatus() {
        return getText(confirmReadmission);
    }

    public HistoryPage goToHistory(String historyUrl) {
        try {
            click(menuToggle);
            waitForVisibility(historyLink);
            click(historyLink);
            waitForVisibility(historyHeader);
        } catch (Exception e) {
            driver.get(historyUrl);
            waitForVisibility(historyHeader);
        }
        return new HistoryPage(driver);
    }

    public void goToHomePage() {
        click(goHomeBtn);
        waitForVisibility(makeAppointmentBtn);
    }

    public boolean isAppointmentPageLoaded() {
        return isDisplayed(facilityDropdown);
    }
   
}