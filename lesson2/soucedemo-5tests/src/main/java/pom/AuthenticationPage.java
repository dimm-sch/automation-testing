package pom;

import org.openqa.selenium.*;

public class AuthenticationPage {

    private static final By USERNAME_LOCATOR = By.id("user-name");
    private static final By PASSWORD_LOCATOR = By.id("password");
    private static final By LOGIN_BUTTON_LOCATOR = By.id("login-button");
    private static final String ERROR_TEXT_JAVASCRIPT =
            "return document.querySelector(\"h3[data-test='error']\").childNodes[1].nodeValue";
    private static final String CURRENT_PAGE_URL_JAVASCRIPT = "return document.location.href";
    public static final String WRONG_CREDENTIALS_ERROR_MESSAGE =
            "Epic sadface: Username and password do not match any user in this service";
    public static final String PASSWORD_REQUIRED_ERROR_MESSAGE = "Epic sadface: Password is required";
    public static final String USERNAME_REQUIRED_ERROR_MESSAGE = "Epic sadface: Username is required";
    public static final String SUCCESSFUL_LOGIN_REDIRECT_URL = "https://www.saucedemo.com/inventory.html";

    String s = "'[a='']'";

    private final WebDriver driver;

    public AuthenticationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String username) {
        setText(USERNAME_LOCATOR, username);
    }

    public void setPassword(String password) {
        setText(PASSWORD_LOCATOR, password);
    }

    public void submit() {
        WebElement element = driver.findElement(LOGIN_BUTTON_LOCATOR);
        element.submit();
    }

    public String getErrorMessage() {
        return executeJavascriptForText(ERROR_TEXT_JAVASCRIPT);
    }

    public String getCurrentPageURL() {
        return executeJavascriptForText(CURRENT_PAGE_URL_JAVASCRIPT);
    }

    private String executeJavascriptForText(String script) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        Object result = executor.executeScript(script);
        if (result != null) {
            return (String) result;
        }

        return null;
    }

    private void setText(By by, String value) {
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(value);
    }
}
