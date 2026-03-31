package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Login;

public class Userlogin extends Baseclass{

	@Test
	public void Profilesection () throws InterruptedException {
		Login LG = new Login(driver);
		LG.hoverProfile();
		LG.clickLoginSignup();
	    Assert.assertTrue(LG.isPhoneFieldDisplayed(), "❌ Login page not opened");

	}
}
