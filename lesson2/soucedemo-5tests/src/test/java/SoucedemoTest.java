import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pom.AuthenticationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pom.AuthenticationPage.*;
import static pom.AuthenticationPage.WRONG_CREDENTIALS_ERROR_MESSAGE;

public class SoucedemoTest {

    private static final String BASE_URL = "https://www.saucedemo.com/";
    private static WebDriver driver;

    private final AuthenticationPage authPage = new AuthenticationPage(driver);

    @BeforeAll
    static void setUp() {
        driver = WebDriverManager.firefoxdriver().create();
        driver.manage().window().maximize();
    }

    @BeforeEach
    void init() {
        driver.get(BASE_URL);
    }

    @Test
    void logInWithWrongUsernameAndPassword() {
        logIn("bob", "123");

        assertEquals(WRONG_CREDENTIALS_ERROR_MESSAGE, authPage.getErrorMessage());
    }

    @Test
    void logInWithExistingAccount() {
        logIn("standard_user", "secret_sauce");

        assertEquals(SUCCESSFUL_LOGIN_REDIRECT_URL, authPage.getCurrentPageURL());
    }

    @Test
    void logInWithWrongPassword() {
        logIn("standard_user", "wrong");

        assertEquals(WRONG_CREDENTIALS_ERROR_MESSAGE, authPage.getErrorMessage());
    }

    @Test
    void logInWithUsernameAndMissingPassword() {
        logIn("standard_user", "");

        assertEquals(PASSWORD_REQUIRED_ERROR_MESSAGE, authPage.getErrorMessage());
    }

    @Test
    void logInWithPasswordAndMissingUsername() {
        logIn("", "secret_sauce");

        assertEquals(USERNAME_REQUIRED_ERROR_MESSAGE, authPage.getErrorMessage());
    }

    private void logIn(String username, String password) {
        authPage.setUsername(username);
        authPage.setPassword(password);
        authPage.submit();
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
