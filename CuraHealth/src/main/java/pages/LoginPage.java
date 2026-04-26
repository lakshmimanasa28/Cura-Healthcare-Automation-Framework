package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.curahealth.base.BasePage;

public class LoginPage extends BasePage {

    private By usernameField = By.id("txt-username");
    private By passwordField = By.id("txt-password");
    private By loginBtn = By.id("btn-login");

    private By appointmentPageHeader = By.id("combo_facility"); // success
    private By errorMsg = By.xpath("//p[@class='lead text-danger']"); // failure

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // 🔥 FIXED METHOD
    public void login(String username, String password) {
        waitForVisibility(usernameField);
        type(usernameField, username);
        type(passwordField, password);
        click(loginBtn);

        // ❌ DO NOT wait for success here
    }

    // ✅ for successful login
    public boolean isLoginSuccessful() {
        try {
            return driver.findElement(appointmentPageHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginPageDisplayed() {
        try {
            waitForVisibility(By.id("txt-username"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public String getErrorMessage() {
        try {
            return getText(errorMsg);
        } catch (Exception e) {
            return "";
        }
    }
   
}