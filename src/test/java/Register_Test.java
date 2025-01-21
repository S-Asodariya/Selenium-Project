import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
public class Register_Test {

	public static void main(String[] args) throws Exception {
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
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
    driver.quit();
	}

}
