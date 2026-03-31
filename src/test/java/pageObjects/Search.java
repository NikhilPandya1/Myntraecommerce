package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Search extends BasePage{

	public Search(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		

	}
	
	@FindBy(xpath = "//input[@placeholder='Search for products, brands and more']")
	WebElement searchBox;


	public void setSearch(String text){
	    searchBox.click();
	    searchBox.sendKeys(text);
	    searchBox.sendKeys(Keys.ENTER);
	
	}
}