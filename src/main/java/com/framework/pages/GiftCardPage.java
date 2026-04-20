package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GiftCardPage {

    WebDriver driver;

    public GiftCardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillInvalidGiftDetails() {
        driver.findElement(By.id("firstname")).sendKeys("Eswari");
        driver.findElement(By.id("lastname")).sendKeys("Konar");
        driver.findElement(By.name("email")).sendKeys("eswari@13");
    }

    public String getErrorMessage() {
        return driver.findElement(
            By.xpath("//div[contains(@class,'invalid-address')]")
        ).getText();
    }
}