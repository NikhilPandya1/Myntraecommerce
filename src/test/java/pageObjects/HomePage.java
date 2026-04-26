package pageObjects;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@class,'desktop-main') and normalize-space()='Men']")
    WebElement menuMen;

    @FindBy(xpath = "//a[contains(@href,'/men-tshirts')]")
    WebElement linkTshirts;

    @FindBy(xpath = "//a[contains(@class,'desktop-main') and normalize-space()='Women']")
    WebElement menuWomen;

    @FindBy(xpath = "//a[contains(text(),'Ethnic Wear')]")
    WebElement linkEthnicWear;

    @FindBy(xpath = "//h4[@class='text-banner-title']")
    WebElement txtBanner;

    public void clickmensection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(menuMen));
        menuMen.click();
    }

    public void clickMenTshirts() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(menuMen));
        Actions act = new Actions(driver);
        act.moveToElement(menuMen).perform();
        wait.until(ExpectedConditions.elementToBeClickable(linkTshirts));
        linkTshirts.click();
    }

    public void clickWomenEthnicWear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(menuWomen));
        Actions act = new Actions(driver);
        act.moveToElement(menuWomen).perform();
        wait.until(ExpectedConditions.elementToBeClickable(linkEthnicWear));
        linkEthnicWear.click();
    }

    public boolean isBannerDisplayed() {
        return txtBanner.isDisplayed();
    }

    public String getBannerText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(txtBanner));
        return txtBanner.getText();
    }
}