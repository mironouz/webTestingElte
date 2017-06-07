package com.mironouz.basic;

import com.mironouz.utility.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Dima on 6/7/2017.
 */
public class LogoutTest {
    private WebDriver driver;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://dropbox.com");
    }

    @Test
    public void testLogout(){
        Utility.login("WebTestingElte@mail.ru", "Elte2016", driver);
        WebElement smile = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("mc-avatar-image")));
        smile.click();
        driver.findElement(By.xpath("//a[@href='https://www.dropbox.com/logout']")).click();
        assertTrue(driver.getCurrentUrl().endsWith("logout"));
    }

    @After
    public void tearDown(){
        driver.close();
    }
}