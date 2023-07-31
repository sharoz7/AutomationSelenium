package project1;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/*Project 1 – Testing Website using Selenium WebDriver
Problem Statement:
Testing websites for various functionalities is a tedious task. Writing right test programs is
the first step in that direction. How can we test our own programs using demo websites?
Objective: To test the websites using Selenium webDriver
Requirements: Write Selenium scripts to:
 Test the login screen with username and password
 Test online widgets like date pickers, tabs, sliders
 Test interactive actions like draggable, resizable, selectable etc.
 Test the filling up of registration form and submission
 Test frames and windows
 Test drop-down menus, alert boxes
Prerequisites: Basic Java programming skill*/
public class Project_testWebsite {
	public WebDriver driver;

	@BeforeMethod
	public void set_up() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		// navigate to test website
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}

	@AfterMethod
	public void tear_down() {
		driver.close();
	}

	@Test(priority = 1)
	public void module_1() throws InterruptedException, AWTException {

		// to automate the signin page..it is inside the frame
		driver.switchTo().frame(0);
		driver.findElement(By.id("RESULT_TextField-1")).sendKeys("xxxx");
		Thread.sleep(3000);
		driver.findElement(By.id("RESULT_TextField-2")).sendKeys("yyy");
		Thread.sleep(3000);
		driver.findElement(By.id("RESULT_TextField-3")).sendKeys("123");
		Thread.sleep(3000);
		driver.findElement(By.id("RESULT_TextField-4")).sendKeys("rrrr");
		Thread.sleep(3000);
		driver.findElement(By.id("RESULT_TextField-5")).sendKeys("ffff");
		Thread.sleep(3000);
		driver.findElement(By.id("RESULT_TextField-6")).sendKeys("xxx@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[text()='Female']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='q15']/span/following::*[1]/tbody/tr[1]/td/label")).click();
		Select s = new Select(driver.findElement(By.id("RESULT_RadioButton-9")));
		s.selectByValue("Radio-1");
		Thread.sleep(3000);

//click on choose file
		WebElement file = driver.findElement(By.xpath("//input[@name='RESULT_FileUpload-10']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", file);
		Thread.sleep(5000);
		// automate window alert using Java Robot
		Robot r = new Robot();
		// copy the download location to keyboard
		StringSelection st = new StringSelection("C:\\Selenium_training\\upload.docx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(st, null);
		Thread.sleep(5000);
		// press ctrl+v and enter
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Home")).click();
		Thread.sleep(5000);

	}

	@Test(priority = 2)
	public void module2() throws InterruptedException {

		// search
		driver.findElement(By.id("Wikipedia1_wikipedia-search-input")).sendKeys("selenium");
		Thread.sleep(3000);
		driver.findElement(By.className("wikipedia-search-button")).click();
		Thread.sleep(3000);
		List<WebElement> searchRes = driver.findElements(By.xpath("//div[@id='wikipedia-search-result-link']"));
		System.out.println(searchRes.size());
		for (int i = 0; i < searchRes.size(); i++) {
			System.out.println(searchRes.get(i).getText());
		}
		String text = driver.findElement(By.xpath("//div[@class='widget-content']/table/tbody/tr[6]/td[3]")).getText();
		System.out.println(text);

		// alert
		driver.findElement(By.xpath("//button[text()='Click Me']")).click();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		String alertresponse = driver.findElement(By.xpath("//p[@id='demo']")).getText();
		System.out.println(alertresponse);

		// date picker
		driver.findElement(By.id("datepicker")).click();
		Thread.sleep(3000);
		for (int i = 1; i <= 4; i++) {
			driver.findElement(By.xpath("//span[text()='Prev']")).click();
			Thread.sleep(3000);
		}
		driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']/tbody//tr[3]/td[5]")).click();
		Thread.sleep(3000);
		WebElement date = driver.findElement(By.id("datepicker"));
		System.out.println(date.getAttribute("value"));

		// Select menu
		// select speed
		Select speed = new Select(driver.findElement(By.id("speed")));
		speed.selectByIndex(1);

		// select files
		Select file = new Select(driver.findElement(By.id("files")));
		file.selectByValue("3");
		Thread.sleep(3000);

		// select number
		Select number = new Select(driver.findElement(By.id("number")));
		number.selectByVisibleText("1");
		Thread.sleep(3000);

		// select products
		Select product = new Select(driver.findElement(By.id("products")));
		product.selectByValue("Apple");
		Thread.sleep(3000);

		// select products
		Select animal = new Select(driver.findElement(By.id("animals")));
		animal.selectByValue("babycat");
		Thread.sleep(3000);

		// Text labels
		String text_label = driver.findElement(By.xpath("(//div[@class='widget-content'])[4]/div[3]/span")).getText();
		System.out.println("Text label is " + text_label);
		Thread.sleep(3000);

		// xpathAxes
		String empid = driver.findElement(By.xpath("(//div[@class='widget-content'])[5]/empinfo/employee[1]/empid"))
				.getText();
		System.out.println("Employee id is " + empid);

		String emp_Name = driver.findElement(By.xpath("(//div[@class='widget-content'])[5]/empinfo/employee[1]/name"))
				.getText();
		System.out.println("Employee id is " + emp_Name);

		String emp_Designation = driver
				.findElement(By.xpath("(//div[@class='widget-content'])[5]/empinfo/employee[1]/designation")).getText();
		System.out.println("Employee id is " + emp_Designation);

		// link
		driver.switchTo().frame(0);
		WebElement link = driver.findElement(By.linkText("Software Testing Tutorials"));
		System.out.println(link.getAttribute("href"));
		driver.get(link.getAttribute("href"));
		Thread.sleep(5000);
		driver.navigate().back();
	}

	@Test(priority = 3)
	public void module3() throws InterruptedException {

		// double click
		WebElement button = driver.findElement(By.xpath("//button[text()='Copy Text']"));
		Actions a = new Actions(driver);
		a.doubleClick(button).build().perform();
		System.out.println(driver.findElement(By.id("field2")).getAttribute("value"));
		Thread.sleep(5000);

		// drag and drop
		WebElement drag = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement drop = driver.findElement(By.xpath("//div[@id='droppable']"));
		a.dragAndDrop(drag, drop).build().perform();
		Thread.sleep(5000);
		System.out.println(driver.findElement(By.xpath("//div[@id='droppable']/p")).getText());
		WebElement drag1 = driver.findElement(By.xpath("//h5[text()='Mr John']//following::*[1]"));
		WebElement drag2 = driver.findElement(By.xpath("//h5[text()='Mary']//following::*[1]"));
		WebElement drop1 = driver.findElement(By.id("trash"));
		a.dragAndDrop(drag1, drop1).build().perform();
		a.dragAndDrop(drag2, drop1).build().perform();
		Thread.sleep(5000);

		// slider
		WebElement slider = driver.findElement(By.id("slider"));
		a.dragAndDropBy(slider, 50, 0).build().perform();
		Thread.sleep(5000);

		// resizable
		WebElement resize = driver.findElement(By.xpath("//div[@id='resizable']/child::*[4]"));
		a.clickAndHold(resize).moveByOffset(5, 5).release().build().perform();
		Thread.sleep(5000);
	}

	@Test(priority = 4)
	public void module4() throws InterruptedException, IOException {
		// table[@name='BookTable']/tbody/tr[6]/td[2]
		// HTML Table

		String bookname = driver.findElement(By.xpath("//table[@name='BookTable']/tbody/tr[6]/td[1]")).getText();
		System.out.println("Bookname is " + bookname);
		String author = driver.findElement(By.xpath("//table[@name='BookTable']/tbody/tr[6]/td[2]")).getText();
		System.out.println("Author is " + author);
		String subject = driver.findElement(By.xpath("//table[@name='BookTable']/tbody/tr[6]/td[3]")).getText();
		System.out.println("Subject is " + subject);
		String price = driver.findElement(By.xpath("//table[@name='BookTable']/tbody/tr[6]/td[4]")).getText();
		System.out.println("Price is " + price);

		// tooltips
		WebElement age = driver.findElement(By.id("age"));
		Actions a = new Actions(driver);
		a.moveToElement(age).build().perform();
		Thread.sleep(3000);
		age.sendKeys("31");
		Thread.sleep(5000);
		System.out.println("age entered is " + age.getAttribute("value"));

		// Barcodes
		File src = driver.findElement(By.xpath("//h2[text()='Bar Codes']/following::*[1]/img[1]"))
				.getScreenshotAs(OutputType.FILE);
		File target = new File("C:\\Selenium_training\\test\\src\\test\\resources\\" + "barcode1.default.jpeg");
		FileUtils.copyFile(src, target);

		File src1 = driver.findElement(By.xpath("//h2[text()='Bar Codes']/following::*[1]/img[2]"))
				.getScreenshotAs(OutputType.FILE);
		File target1 = new File("C:\\Selenium_training\\test\\src\\test\\resources\\" + "barcode2.default.jpeg");
		FileUtils.copyFile(src1, target1);

		// QR code
		File src3 = driver.findElement(By.xpath("//h2[text()='QR Code']/following::*[1]/img"))
				.getScreenshotAs(OutputType.FILE);
		File target3 = new File("C:\\Selenium_training\\test\\src\\test\\resources\\" + "QRCode.default.jpeg");
		FileUtils.copyFile(src3, target3);

	}
}
