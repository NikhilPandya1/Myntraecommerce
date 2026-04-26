package testCases;

//import java.lang.System.Logger;
//import java.time.Duration;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
//import org.testng.annotations.*;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import pageObjects.HomePage;
//import pageObjects.Search;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.Search;

public class Checkoverview extends Baseclass{

    HomePage HP;  // declare at top
    Search SC;    // add this

    @BeforeClass(alwaysRun = true)
    public void pageSetup() {
        HP = new HomePage(driver);
        SC = new Search(driver);  // add this
    }
	
	@Test(priority = 1)
	public void verifyClothes() throws Exception {
		logger.info("Starting Testcase for overview");
	HP.clickmensection();
	logger.info("Clicked on Men section");

//	HP.clickWomenEthnicWear();
	String actualText = HP.getBannerText();
    Assert.assertEquals(actualText, "BIGGEST DEALS ON TOP BRANDS");
	logger.info("Men section was got executed via assert");
	}
	
	@Test(priority = 2)
	public void Menswear() {
		HP.clickMenTshirts();
		String actualUrl = driver.getCurrentUrl();
	    String expectedUrl = "https://www.myntra.com/men-tshirts";
	    Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	@Test (priority = 3)
	public void womenswear() {
		HP.clickWomenEthnicWear();
		String actualUrl = driver.getCurrentUrl();
	    String expectedUrl = "https://www.myntra.com/women-ethnic-wear";
	    Assert.assertEquals(actualUrl, expectedUrl);
	}
	
    @Test(priority = 4)
    public void searchfunction() {
        SC.setSearch("Nikhil kurta");
        String actualUrl = driver.getCurrentUrl();
	    String expectedUrl = "https://www.jjjmyntra.com/nikhil-kurta?rawQuery=Nikhil%20kurta";
	    Assert.assertEquals(actualUrl, expectedUrl);
    }
}