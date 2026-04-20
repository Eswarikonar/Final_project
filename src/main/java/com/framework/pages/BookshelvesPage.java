package com.framework.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BookshelvesPage {

    WebDriver driver;

    public BookshelvesPage(WebDriver driver) {
        this.driver = driver;
    }

    By firstThreeNames = By.xpath("//h3[contains(@class,'XxwSy')]");
    By firstThreePrices = By.xpath("//div[contains(@class,'UYQNp')]");

    public void printFirstThreeBookshelves() {
        List<WebElement> names = driver.findElements(firstThreeNames);
        List<WebElement> prices = driver.findElements(firstThreePrices);

        for (int i = 0; i < 3; i++) {
            System.out.println(names.get(i).getText() + " - " +
                               prices.get(i).getText());
        }
    }
}