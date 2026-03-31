package pageObjects;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.utility.RandomString;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	@FindBy(xpath = "//a[@class='desktop-main'][normalize-space()='Men']")
	WebElement menuMen;

	@FindBy(xpath = "//a[@href='/men-tshirts']")
	WebElement linkTshirts;
	
	@FindBy(xpath = "//a[@class='desktop-main'][normalize-space()='Women']")
	WebElement menuWomen;
	
	@FindBy(xpath = "//a[contains(text(),'Ethnic Wear')]")
	WebElement linkEthnicWear;
	
	@FindBy(xpath = "//h4[@class='text-banner-title']")
	WebElement txtBanner;
	

	public void clickMenTshirts(){
	    Actions act = new Actions(driver);
	    
	    act.moveToElement(menuMen).perform();   // hover on Men
	    linkTshirts.click();                    // click T-Shirts
	}
	
	public void clickmensection() {
		menuMen.click();
	}
	
	public void clickWomenEthnicWear() {
	    Actions act = new Actions(driver);

	    act.moveToElement(menuWomen).perform(); 
	    linkEthnicWear.click();// hover on Women
	}
	
	public boolean isBannerDisplayed(){
	    return txtBanner.isDisplayed();
	}
	public String getBannerText(){
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    wait.until(ExpectedConditions.visibilityOf(txtBanner));
	    return txtBanner.getText();
	}

//	public String randString() {
//		String generatedstring = RandomStringUtils.randomAlphabetic(6);
//		return generatedstring;
//				
//	}
	
	
}

