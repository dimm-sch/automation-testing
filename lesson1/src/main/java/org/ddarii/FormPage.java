package org.ddarii;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

public class FormPage {
    private final WebDriver driver;
    private final By userFirstName = By.xpath("//input[@id='firstName']");
    private final By userLastName = By.xpath("//input[@id='lastName']");
    private final By userEmail = By.xpath("//input[@id='userEmail']");
    private final By userGenderMale = By.xpath("//input[@id='gender-radio-1']");
    private final By userGenderFemale = By.xpath("//input[@id='gender-radio-2']");
    private final By userGenderOther = By.xpath("//input[@id='gender-radio-3']");
    private final By userPhoneNumber = By.xpath("//input[@id='userNumber']");
    private final By userDateOfBirth = By.xpath("//input[@id='dateOfBirthInput']");
    private final By subjects = By.xpath("//input[@id='subjectsInput']");
    private final By userHobbySports = By.xpath("//input[@id='hobbies-checkbox-1']");
    private final By userHobbyReading = By.xpath("//input[@id='hobbies-checkbox-2']");
    private final By userHobbyMusic = By.xpath("//input[@id='hobbies-checkbox-3']");
    private final By userPicture = By.xpath("//input[@id='uploadPicture']");
    private final By userCurrentAddress = By.xpath("//textarea[@id='currentAddress']");
    private final By userState = By.xpath("//input[@id='react-select-3-input']");
    private final By userCity = By.xpath("//input[@id='react-select-4-input']");
    private final By submitButton = By.xpath("//button[@id='submit']");

    public FormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUserFirstName(String firstName) {
        WebElement element = driver.findElement(userFirstName);
        element.sendKeys(firstName);
    }

    public void setUserLastName(String lastName) {
        WebElement element = driver.findElement(userLastName);
        element.sendKeys(lastName);
    }

    public void setUserEmail(String email) {
        WebElement element = driver.findElement(userEmail);
        element.sendKeys(email);
    }

    private WebElement getWebElementByGender(Gender gender) {
        WebElement element;
        switch (gender) {
            case MALE:
                element = driver.findElement(userGenderMale);
                break;
            case FEMALE:
                element = driver.findElement(userGenderFemale);
                break;
            case OTHER:
                element = driver.findElement(userGenderOther);
                break;
            default:
                throw new IllegalArgumentException("Invalid gender value: " + gender);
        }

        return element;
    }

    public void setUserGender(Gender gender) {
        Actions actions = new Actions(driver);
        WebElement element = getWebElementByGender(gender);
        actions.moveToElement(element)
                .click()
                .perform();
    }

    public void setUserPhoneNumber(String phoneNumber) {
        WebElement element = driver.findElement(userPhoneNumber);
        element.sendKeys(phoneNumber);
    }

    public void setUserDateOfBirth(LocalDate dateOfBirth) {
        String date = new SimpleDateFormat("dd MMM yyyy").format(
                Date.from(dateOfBirth.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        WebElement element = driver.findElement(userDateOfBirth);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.NULL));
        element.sendKeys(date);

        WebElement labelElement = driver.findElement(By.id("dateOfBirth-label"));
        labelElement.click();
    }

    public void setSubjects(String subjects) {
        WebElement element = driver.findElement(this.subjects);
        element.sendKeys(subjects);
    }

    private WebElement getWebElementByHobby(Hobby hobby) {
        WebElement element;
        switch (hobby) {
            case SPORTS:
                element = driver.findElement(userHobbySports);
                break;
            case READING:
                element = driver.findElement(userHobbyReading);
                break;
            case MUSIC:
                element = driver.findElement(userHobbyMusic);
                break;
            default:
                throw new IllegalArgumentException("Invalid hobby value: " + hobby);
        }

        return element;
    }

    public void setUserHobbies(Set<Hobby> hobbies) {
        for (Hobby hobby : hobbies) {
            Actions actions = new Actions(driver);
            WebElement element = getWebElementByHobby(hobby);
            actions.moveToElement(element)
                    .click()
                    .perform();
        }
    }

    public void setUserPicture(String picturePath) {
        WebElement element = driver.findElement(userPicture);
        element.sendKeys(picturePath);
    }

    public void setUserCurrentAddress(String address) {
        WebElement element = driver.findElement(userCurrentAddress);
        element.sendKeys(address);
    }

    public void setUserState(String state) {
        WebElement element = driver.findElement(userState);
        element.sendKeys(state);
        element.sendKeys(Keys.ENTER);
    }

    public void setUserCity(String city) {
        WebElement element = driver.findElement(userCity);
        element.sendKeys(city);
        element.sendKeys(Keys.ENTER);
    }

    public void submit() {
        WebElement element = driver.findElement(submitButton);
        element.submit();
    }
}
