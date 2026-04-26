package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.curahealth.base.BasePage;

public class HistoryPage extends BasePage {

    // Page header
    private By historyHeader = By.xpath("//h2[normalize-space()='History']");

    // Multiple appointments (list view)
    private By allFacilities = By.xpath("//div[@class='panel-body']//p[1]");
    private By allDates = By.cssSelector(".panel-heading");

    // Single/latest appointment
    private By latestFacility = By.xpath("(//div[@class='panel-body']//p)[1]");
    private By latestDate = By.cssSelector(".panel-heading");

    public HistoryPage(WebDriver driver) {
        super(driver);
    }

    // ✅ Page load validation
    public boolean isHistoryPageLoaded() {
        waitForVisibility(historyHeader);
        return isDisplayed(historyHeader);
    }

    // ✅ Get latest facility
    public String getLatestFacility() {
        return getText(latestFacility);
    }

    // ✅ Get latest date
    public String getLatestDate() {
        return getText(latestDate);
    }

    // ✅ Get all facilities (for multiple appointments test)
    public List<String> getAllFacilities() {
        waitForVisibility(allFacilities);

        List<WebElement> elements = driver.findElements(allFacilities);
        List<String> facilities = new ArrayList<>();

        for (WebElement e : elements) {
            facilities.add(e.getText().trim());
        }

        return facilities;
    }

    // ✅ Get all dates
    public List<String> getAllDates() {
        waitForVisibility(allDates);

        List<WebElement> elements = driver.findElements(allDates);
        List<String> dates = new ArrayList<>();

        for (WebElement e : elements) {
            dates.add(e.getText().trim());
        }

        return dates;
    }

    // ✅ Generic check (safe fallback)
    public boolean containsText(String text) {
        return driver.getPageSource().contains(text);
    }
}