package org.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HelloWorld {
    static final String BASE_URL = "https://demoqa.com/automation-practice-form";
    static private WebDriver driver;

    @BeforeClass
    static public void beforeClass1() {
        driver = WebDriverManager.firefoxdriver().create();
        driver.manage().window().maximize();
    }

    @Test
    public void helloWorldTest() {
        System.out.println("Start helloWorldTest");
        driver.get(BASE_URL);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(StringUtils.join(ExceptionUtils.getStackFrames(e), ",\n"));
            e.printStackTrace();
        }


        WebElement firstName = driver.findElement(By.xpath("//*[@id='firstName']"));
        firstName.clear();
        String firstNameValue = "Anatolie";
        firstName.sendKeys(firstNameValue);

        String actual = firstName.getAttribute("value");
        assertThat(actual, is(firstNameValue));

        System.out.println("Finish helloWorldTest");
    }

    @AfterClass
    static  public void tearDown() {
        driver.quit();
    }

}
