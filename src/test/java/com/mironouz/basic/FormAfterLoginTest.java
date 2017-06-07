package com.mironouz.basic;

import com.mironouz.utility.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dima on 6/7/2017.
 */
public class FormAfterLoginTest {
    private WebDriver driver;
    private WebElement form;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://dropbox.com");
        Utility.login("WebTestingElte@mail.ru", "Elte2016", driver);
        form = new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("search-bar__text-input")));
    }

    @Test
    public void testFileNotFound(){
        form.sendKeys("Trump");
        form.sendKeys(Keys.ENTER);
        WebElement text = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("u-pad-top-l")));
        assertEquals(text.getText(), "No results found");

    }

    @Test
    public void testFileWasFound(){
        form.sendKeys("Obama");
        form.sendKeys(Keys.ENTER);
        WebElement founded = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("search-results__info")));
        WebElement text = founded.findElement(By.className("o-grid__col--8-of-12"));
        assertEquals(text.getText(), "1 result in files, folders, Paper docs, and content.");

    }


    @After
    public void tearDown(){
        driver.close();
    }


}