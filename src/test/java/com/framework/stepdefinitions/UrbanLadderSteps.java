package com.framework.stepdefinitions;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.base.DriverFactory;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UrbanLadderSteps {

    static WebDriver driver;

    @BeforeAll
    public static void setup() {
        driver = DriverFactory.initDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    // ========= Scenario 1 =========

    @Given("user launches Urban Ladder application")
    public void user_launches_urban_ladder_application() {
        driver.get("https://www.urbanladder.com/");
    }

    @When("user searches for bookshelves")
    public void user_searches_for_bookshelves() {
        driver.findElement(By.id("searchInput"))
              .sendKeys("Bookshelves", Keys.ENTER);
        
    }

    @Then("user prints first {int} bookshelves under {int}")
    public void user_prints_first_bookshelves_under(Integer count, Integer price) {
		driver.findElement(By.id("productsContainer")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Storage Type']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='Open Storage']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Price']"))).click();

        WebElement maxPrice =
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[contains(@aria-label,'Maximum')]")));
        maxPrice.clear();
        maxPrice.sendKeys(String.valueOf(price));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Apply Filter']"))).click();

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.xpath("//h3[contains(@class,'XxwSy')]"), count));

        List<WebElement> names =
                driver.findElements(By.xpath("//h3[contains(@class,'XxwSy')]"));
        List<WebElement> prices =
                driver.findElements(By.xpath("//div[contains(@class,'UYQNp')]"));

        for (int i = 0; i < count; i++) {
            System.out.println(
                    names.get(i).getText() + " - " + prices.get(i).getText());
        }
    }

    // ========= Scenario 2 =========

    @When("user captures all sofa and recliner options")
    public void user_captures_all_sofa_and_recliner_options() {
        Actions act = new Actions(driver);
        act.moveToElement(
                driver.findElement(By.xpath("//span[text()='Sofas & Recliners']"))
        ).perform();
    }

    @Then("sofa and recliner options should be displayed")
    public void sofa_and_recliner_options_should_be_displayed() {
        List<WebElement> sofas =
                driver.findElements(By.xpath("//a[contains(@class,'njdyQ')]"));
        for (WebElement sofa : sofas) {
            System.out.println(sofa.getText());
        }
    }

    // ========= Scenario 3 =========

    @When("user opens gift card page")
    public void user_opens_gift_card_page() {

        String parent = driver.getWindowHandle();
        driver.findElement(By.partialLinkText("Gift")).click();

        Set<String> windows = driver.getWindowHandles();
        for (String w : windows) {
            if (!w.equals(parent)) {
                driver.switchTo().window(w);
                break;
            }
        }
    }

    @Then("error message should be displayed for invalid email")
    public void error_message_should_be_displayed_for_invalid_email() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("firstname"))).sendKeys("Eswari");

        driver.findElement(By.id("lastname")).sendKeys("Konar");

        WebElement email =
                driver.findElement(By.name("email"));
        email.sendKeys("eswari@13");
        email.sendKeys(Keys.TAB);

        WebElement error =
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'invalid-address')]")));

        System.out.println("ERROR MESSAGE: " + error.getText());
    }
}
