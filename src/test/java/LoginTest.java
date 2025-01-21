import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

	public static void main(String[] args) throws Exception {
		//set chromdriver path
		System.setProperty("webdriver.chrom.driver","C:\\Users\\sumis\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromdriver.exe");
		//create object for chromdriver
		WebDriver driver = new ChromeDriver();
		//open the application with url
		driver.get("https://admin-demo.nopcommerce.com/login");
		//minimize the browser
		driver.manage().window().maximize();
		//wait time
		Thread.sleep(5000);
		WebElement txt_username = driver.findElement(By.id("Email"));
		WebElement txt_password = driver.findElement(By.id("Password"));
		WebElement btn_login = driver.findElement(By.className("login-button"));
		txt_username.clear();
		txt_username.sendKeys("admin@yourstore.com");
		txt_password.clear();
		txt_password.sendKeys("admin");
		btn_login.click();
		Thread.sleep(5000);
		WebElement lnk_logout = driver.findElement(By.linkText("Logout"));
		//Add the assert if login successfully or not
	boolean isLogout_display = lnk_logout.isDisplayed();
	if(isLogout_display) {
		System.out.println("Login Successfully");
	}else {
		System.out.println("Login Unsuccessfully");
	}
	//forward and backward arrow click
	driver.navigate().back();
	Thread.sleep(2000);
	driver.navigate().forward();
	Thread.sleep(2000);
	//navigate to another URL
	driver.navigate().to("https://admin-demo.nopcommerce.com/Admin/Product/List");
	Thread.sleep(5000);
	//Refresh the page
	driver.navigate().refresh();
	Thread.sleep(5000);
	//close the application
	driver.quit();
	}

}
