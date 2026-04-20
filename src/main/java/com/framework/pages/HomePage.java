package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    // ✅ Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Locators
    private By searchBox = By.id("searchInput");
    private By sofaMenu = By.xpath("//span[text()='Sofas & Recliners']");
    private By giftLink = By.partialLinkText("Gift");

    // ✅ Actions
    public void openApplication() {
        driver.get("https://www.urbanladder.com/");
    }

    public void searchBookshelves() {
        driver.findElement(searchBox).sendKeys("Bookshelves", Keys.ENTER);
    }

    // ✅ THIS METHOD FIXES YOUR ERROR
    public By getSofaMenu() {
        return sofaMenu;
    }

    public void clickGiftLink() {
        driver.findElement(giftLink).click();
    }
}