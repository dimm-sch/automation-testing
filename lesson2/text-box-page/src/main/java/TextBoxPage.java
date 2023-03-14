import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextBoxPage {

    private static final By FULL_NAME_LOCATOR = By.id("userName");
    private static final By EMAIL_LOCATOR = By.id("userEmail");
    private static final By CURRENT_ADDRESS_LOCATOR = By.id("currentAddress");
    private static final By PERMANENT_ADDRESS_LOCATOR = By.id("permanentAddress");
    private static final By SUBMIT_BUTTON_LOCATOR = By.id("submit");

    private final WebDriver driver;

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setFullName(String fullName) {
        WebElement element = driver.findElement(FULL_NAME_LOCATOR);
        element.sendKeys(fullName);
    }

    public void setEmail(String email) {
        WebElement element = driver.findElement(EMAIL_LOCATOR);
        element.sendKeys(email);
    }

    public void setCurrentAddress(String currentAddress) {
        WebElement element = driver.findElement(CURRENT_ADDRESS_LOCATOR);
        element.sendKeys(currentAddress);
    }

    public void setPermanentAddress(String permanentAddress) {
        WebElement element = driver.findElement(PERMANENT_ADDRESS_LOCATOR);
        element.sendKeys(permanentAddress);
    }

    public void submit() {
        WebElement element = driver.findElement(SUBMIT_BUTTON_LOCATOR);
        element.click();
    }
}
