import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * xpath relative = "//tagname[@attribute='value']"
 * 					"//*[@attribute = 'value']"
 * xpath absolute = /html/body/div/div/button
 * 
 * "//input[@value='radio1']"
 * "//input[@name='radiobutton1']"
 * 
 * write below code in console to check the xpath is correct or not
 * $x("//input[@value='radio1']")
 * */


/*
 * css selector = "tagname[attribute = 'value']"
 * write below code in console to check css selector
 * $$("input[value='radio2']")
 * 
 * */

/**
 * @Beforesuite
 * @BeforeClass
 * @BeforeTest
 * @BeforeMethod
 * @Test -1
 * @AfterMethod
 * @BeforeMethod
 * @Test-2
 * @AfterMethod
 * @AfterTest
 * @AfterClass
 * @AfterSuite
 * 
 * 
 */



public class FirstAutomation_Test {
	//Before all test  case
	@BeforeSuite
	public void ValidateBeforeSuit() {
		System.out.println("I am Before Suite");
	}
	
	
	@BeforeClass
	public void ValidateBeforeClass() {
		System.out.println("I am before class");
		}
	
WebDriver driver;

//This will excute only one time
@BeforeTest
public void LaunchBrowser() throws Exception {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get("https://rahulshettyacademy.com/AutomationPractice/");
	Thread.sleep(3000);
}

@BeforeMethod
public void RefreshPage() {
	driver.navigate().refresh();
}

@Test
public void ValidateHomePage() {
	WebElement btn_Home = driver.findElement(By.xpath("//button[text()='Home']"));
	btn_Home.click();
}

@Test
public void ValidateCoursePage() {
	WebElement link_Courses = driver.findElement(By.xpath("//a[text()='Courses']"));
	link_Courses.click();
}

@AfterMethod
public void PrintData() {
	System.out.println("I am AfterMethod");
}

@AfterTest
public void CloseBrowser() {
	driver.quit();
}

@AfterClass
public void ValidateAfterClass() {
	System.out.println("I am AfterClass");
}

@AfterSuite
public void ValidateAfterSuite() {
	System.out.println("I am AfterSuite");
}
}
