package pageObjects;

import java.time.Duration;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Search extends BasePage {

    public Search(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Search for products, brands and more']")
    WebElement searchBox;

    public void setSearch(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.click();
        searchBox.sendKeys(text);
        searchBox.sendKeys(Keys.ENTER);
    }
}