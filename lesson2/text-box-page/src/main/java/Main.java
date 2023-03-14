import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Main {

    public static void main(String[] args) {
        String driverLocation = "src/main/resources/geckodriver";
        System.setProperty("webdriver.gecko.driver", driverLocation);
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(driverLocation);
        WebDriver driver = new FirefoxDriver();
        driver.get("https://demoqa.com/text-box");

        TextBoxPage page = new TextBoxPage(driver);
        page.setFullName("John Lastname");
        page.setEmail("john@email.com");
        page.setCurrentAddress("street Alpha 1");
        page.setPermanentAddress("street 2");
        page.submit();

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
    }
}
