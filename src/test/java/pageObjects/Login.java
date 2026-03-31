package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login extends BasePage{

	public Login(WebDriver driver) {
		super(driver);
	}
		// TODO Auto-generated constructor stub
		
		// Step 1 - Profile icon
	    @FindBy(xpath = "//span[text()='Profile']")
	    WebElement linkProfile;
	    
	    @FindBy(xpath = "//input[@autocomplete='new-password']")
	    WebElement txtPhoneField;
	    
	    

	    // Step 2 - Hover profile to open popup
	    public void hoverProfile() {
	        Actions actions = new Actions(driver);
	        actions.moveToElement(linkProfile).perform();
	    }

	    // Step 3 - Wait for popup and click Login/Signup
	    public void clickLoginSignup() {

	        // Wait until button appears in popup
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        WebElement loginBtn = wait.until(
	            ExpectedConditions.presenceOfElementLocated(
	                By.xpath("//a[@data-track='login']")));
	        loginBtn.click();	    }
	                
	       

	    // Step 4 - Call both together
	    public void openLoginPopup() {
	        hoverProfile();       // hover to open popup
	        clickLoginSignup();   // wait and click button
	    }
	
	
	    public boolean isPhoneFieldDisplayed(){

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOf(txtPhoneField));

	        return txtPhoneField.isDisplayed();
	    }

}
