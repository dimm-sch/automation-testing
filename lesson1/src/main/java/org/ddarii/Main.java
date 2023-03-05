package org.ddarii;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String driverLocation = "src/main/resources/geckodriver";
        System.setProperty("webdriver.gecko.driver", driverLocation);
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(driverLocation);
        WebDriver driver = new FirefoxDriver();
        driver.get("https://demoqa.com/automation-practice-form");

        FormPage formPage = new FormPage(driver);
        formPage.setUserFirstName("John Test");
        formPage.setUserLastName("Brown");
        formPage.setUserEmail("johnb@gmail.com");
        formPage.setUserGender(Gender.OTHER);
        formPage.setUserPhoneNumber("0069001122");
        formPage.setUserDateOfBirth(LocalDate.of(1995, Month.AUGUST, 7));
        formPage.setSubjects("Subject1, Subject2");
        formPage.setUserHobbies(Set.of(Hobby.MUSIC, Hobby.SPORTS));
        formPage.setUserPicture(new File("src/main/resources/penguin.jpg").getAbsolutePath());
        formPage.setUserCurrentAddress("address1");
        formPage.setUserState("NCR");
        formPage.setUserCity("Gurgaon");
        formPage.submit();

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
    }
}
