package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class Baseclass {
public WebDriver driver;
	@BeforeClass
	public void setup() throws InterruptedException {
		
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
	}


//@AfterClass
//public void quiting() {
//	driver.quit();
//}
}