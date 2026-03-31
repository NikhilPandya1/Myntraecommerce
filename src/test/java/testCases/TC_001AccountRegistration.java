package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.Search;

public class TC_001AccountRegistration extends Baseclass{

	
	
	
	@Test(priority = 1)
	public void verifyClothes() throws Exception {
	HomePage HP = new HomePage(driver);
	HP.clickmensection();
//	HP.clickWomenEthnicWear();
	
	String actualText = HP.getBannerText();
    Assert.assertEquals(actualText, "BIGGEST DEALS ON TOP BRANDS");
	}
	
	@Test(priority = 2)
	public void Menswear() {
		HomePage HP = new HomePage(driver);
		HP.clickMenTshirts();

		String actualUrl = driver.getCurrentUrl();
	    String expectedUrl = "https://www.myntra.com/men-tshirts";

	    Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	@Test (priority = 3)
	public void womenswear() {
		HomePage HP = new HomePage(driver);
		HP.clickWomenEthnicWear();

		String actualUrl = driver.getCurrentUrl();
	    String expectedUrl = "https://www.myntra.com/women-ethnic-wear";

	    Assert.assertEquals(actualUrl, expectedUrl);

	}
    @Test(priority = 4)
    public void searchfunction() {
        Search newsearch = new Search(driver);
        newsearch.setSearch("Nikhil kurta");
        
        String actualUrl = driver.getCurrentUrl();
	    String expectedUrl = "https://www.myntra.com/nikhil-kurta?rawQuery=Nikhil%20kurta";

	    Assert.assertEquals(actualUrl, expectedUrl);
    }
    
    
}