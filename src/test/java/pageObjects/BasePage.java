package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    WebDriverWait wait;  
	
	WebDriver driver;
	public BasePage (WebDriver driver) {
		this.driver=driver;
		
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15)); // ADD THIS

		PageFactory.initElements(driver, this);
		
	}
}
