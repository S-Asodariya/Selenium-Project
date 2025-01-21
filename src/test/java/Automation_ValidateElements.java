import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Automation_ValidateElements {
	WebDriver driver;

	//This will excute only one time
	@BeforeTest
	public void LaunchBrowser() throws Exception {
		WebDriverManager.chromedriver().setup();  //chrome driver setup
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
	}
	
	@Test
	public void ClickOnRadiobutton () throws Exception {
		WebElement rbtn_radio = driver.findElement(By.xpath("//*[@value = 'radio1']")); //xpath selector
		rbtn_radio.click();
		WebDriverWait wb = new WebDriverWait(driver,Duration.ofSeconds(10)); //Explicit wait time
		wb.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@value = 'radio1']")));
		}
	
	@Test
	public void TypeIntoTextBox() throws Exception {
		WebElement txt_TxtBox = driver.findElement(By.cssSelector("[id='autocomplete']")); //css selector
		txt_TxtBox.sendKeys("Ram");
		Thread.sleep(3000);
	}
	
	@Test
	public void HandleWindows() throws Exception{
		WebElement btn_window = driver.findElement(By.id("openwindow"));
		btn_window.click();
		String parent = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		Iterator <String> it = set.iterator();
		while(it.hasNext()) {
			String childWindow = it.next();
			if(!(parent.equals(childWindow))) {
				driver.switchTo().window(childWindow);
				System.out.println(driver.switchTo().window(childWindow).getTitle());
				driver.close();
			}
		}
		Thread.sleep(5000);
		driver.switchTo().window(parent);
	}
	
	@Test
	public void HandleAlert()throws Exception {
		WebElement btn_alert = driver.findElement(By.id("alertbtn"));
		btn_alert.click();
		Thread.sleep(5000);
		Alert a = driver.switchTo().alert();
		String alertmessage = a.getText();
		System.out.println(alertmessage);
		a.accept();
		
		WebElement btn_confirm = driver.findElement(By.id("confirmbtn"));
		btn_confirm.click();
		Thread.sleep(2000);
		Alert a1 = driver.switchTo().alert();
		a1.dismiss();
		
		btn_confirm.click();
		Thread.sleep(2000);
		Alert a2 = driver.switchTo().alert();
		a2.accept();
		
	}
	
	
	@Test
	public void HandleTable() throws Exception {
		WebElement tabledata = driver.findElement(By.id("product"));
		List<WebElement> rows = tabledata.findElements(By.tagName("tr"));
		//add th data here to show table header
		
		for(WebElement row:rows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for(WebElement cell: cells) {
				System.out.println(cell.getText()+ "\t");
			}
			System.out.println();
		}
		WebElement specificdata = driver.findElement(By.xpath("//table[@name='courses']//tr[5]//td[2]"));
		System.out.println(specificdata.getText());
	}
	
	@Test
	public void HandleMouseHover() throws Exception{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700)", "");
		WebElement  btn_mousehover = driver.findElement(By.id("mousehover"));
		Actions action = new Actions(driver);
		action.moveToElement(btn_mousehover).perform();
		WebElement btn_top = driver.findElement(By.linkText("Top"));// using this Xpath("//a[text()='Top']")
		btn_top.click();
		Thread.sleep(3000);
		/*Need to use perfom method for every mouse operations*/
		//To double click on element is =  action.doubleClick("your element").perform();
		//To right click on element is = action.contextClick("your element")
		
		/*To drag and drop :  WebElemt sourceelement = driver.findby...
		To drop :  WebElemt target = driver.find....
		action.dragAnddrop(sourceelement, target).perform();
		*/
		
		/* Click and hold
		 * action.clickAndHold(yourelement).perform()
		 * action.release().perform();
	     */
		
	}
	
	@Test
	public void HandleKeyboardOperation()throws Exception{
		WebElement keyboard = driver.findElement(By.cssSelector("[id='autocomplete']")); //css selector
        keyboard.sendKeys(Keys.ENTER); //press enter keys to the text box
        keyboard.sendKeys(Keys.CONTROL + "C"); //for copy content
	}
	
	@Test
	public void HandleIframe() throws Exception{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1400)", "");
		driver.switchTo().frame("courses-iframe"); //add frame id or name
		//driver.switchTo().frame(0) need to add index;
		//driver.switchTo().frame(WebElement) need to take a webelement
		Thread.sleep(3000);
		WebElement lnk_home = driver.findElement(By.xpath("//@[text()='Home']"));
		lnk_home.click();
		driver.switchTo().defaultContent(); // Switch to main content
		
	}
	
	
	@Test
	public void CaptureScreenShot() throws Exception{
		WebElement keyboard = driver.findElement(By.cssSelector("[id='autocomplete']"));
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		String destinationpath ="D:\\QA\\Sample.png";
		FileUtils.copyFile(screenshot, new File(destinationpath));
	}
	
	
	@AfterTest
	public void CloseBrowser() throws Exception{
		Thread.sleep(3000);
		driver.quit();
	}
}
