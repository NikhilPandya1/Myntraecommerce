package testCases;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import pageObjects.Login;

public class Userlogin extends Baseclass{

    Login LG;  
    @BeforeClass(groups = "Sanity")
    public void pageSetup() {
        LG = new Login(driver);  // create once
    }
	
	@Test(priority = 1,groups = "Sanity")
	public void Profilesection ()  {
		LG.hoverProfile();
		LG.clickLoginSignup();
	    Assert.assertTrue(LG.isPhoneFieldDisplayed(), "❌ Login page not opened");
	}
	
	@Test(priority = 2)
	public void EntPhonenumber() {
		LG.clickfieldphonenumber();
		LG.enterMobileNumber("9021737995");

	}
	
	
	@Test(priority = 3)
	public void clickCheckbox() {
	    LG.clickConsentCheckbox();
	}
	
	
	@Test(priority = 5)
	public void verifyOtpPage() {
	    Assert.assertTrue(LG.isOtpFieldDisplayed(), 
	        "❌ OTP page not opened");
	}
	
	@Test(priority = 4)
	public void clickcontinuebtn() {
		LG.clickContinue();
		System.out.println("This button is clicked");
	}
}

