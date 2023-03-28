package com.amazon.mini;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon_MiniProject {

	static WebDriver driver;
	static String expected = null;
	static String expected1 = null;
	static String firstProductText = null;
	static String productPriceText = null;
	static TakesScreenshot ts;

	@BeforeSuite
	private void browserLaunch() {

		List<String> l = new ArrayList<>();
		l.add("start-maximized");
		l.add("incognito");

		WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments(l);
		driver = new ChromeDriver(opt);
	}

	@BeforeTest
	private void urlLaunch() {
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 1, groups = "starting")
	@Parameters("dropDownValue")
	private void dropDown(String dropDownValue) {

		expected = dropDownValue;

		WebElement dropDown = driver.findElement(By.id("searchDropdownBox"));
		Select opt1 = new Select(dropDown);
		List<WebElement> options = opt1.getOptions();
		ListIterator<WebElement> dropdownOption = options.listIterator();

		while (dropdownOption.hasNext()) {
			WebElement drop = dropdownOption.next();
			String dropDownText = drop.getText();

			if (expected.equals(dropDownText)) {
				opt1.selectByVisibleText(dropDownText);
				break;
			}
		}
	}

	@Test(priority = 2, groups = "starting")
	@Parameters("searchValue")
	private void searchBox(String searchValue) throws InterruptedException {

		expected1 = searchValue;
		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys(expected1);
		Thread.sleep(2000);
		List<WebElement> searchOption = driver
				.findElements(By.xpath("//div[@class='autocomplete-results-container']/div"));
		for (int i = 1; i < searchOption.size(); i++) {

			WebElement expectedOption = driver
					.findElement(By.xpath("//div[@class='autocomplete-results-container']/div[" + i + "]/div"));
			String searchOptionText = expectedOption.getText();
			if (expected1.equals(searchOptionText)) {
				expectedOption.click();
				break;
			}
		}
	}

	@Test(priority = 3)
	private void firstProduct() {
		WebElement firstProduct = driver
				.findElement(By.xpath("//span[text()='RESULTS']/ancestor::div[4]/following-sibling::div[1]//h2"));
		firstProductText = firstProduct.getText();
		System.out.println(firstProductText);
		WebElement firstProductClick = driver
				.findElement(By.xpath("//span[text()='RESULTS']/ancestor::div[4]/following-sibling::div[1]//h2/a"));
		WebElement productPrice = driver.findElement(By.xpath(
				"//span[text()='RESULTS']/ancestor::div[4]/following-sibling::div[1]//span[@class='a-price a-text-price']"));
		productPriceText = productPrice.getText();
		System.out.println(productPriceText + '\n');
		firstProductClick.click();
	}

	@Test(priority = 4, groups = "following")
	private void windowSwitch() {
		String parent = driver.getWindowHandle();
		Set<String> childWindows = driver.getWindowHandles();
		for (String child : childWindows) {

			if (!parent.equals(child)) {
				driver.switchTo().window(child);
			}
		}
	}

	@Test(dependsOnMethods = "windowSwitch", groups = "following") // DEPENDS ON MEDHODS
	private void firstProductSecPage() {

		WebElement productTitle = driver.findElement(By.id("productTitle"));
		String producttitleText = productTitle.getText();
		System.out.println(producttitleText);
		WebElement SecPagePrice = driver.findElement(By.xpath(
				"//span[@id='productTitle']/ancestor::div[2]/following-sibling::div[9]//table[1]//span[@data-a-strike='true']"));
		String SecPagePriceText = SecPagePrice.getText();
		System.out.println(SecPagePriceText + '\n');

		if (firstProductText.contains(producttitleText) && productPriceText.equals(SecPagePriceText)) {
			System.out.println("Both Product Names And Prices are Same" + '\n');
			driver.findElement(By.id("add-to-cart-button")).click();

		} else {
			driver.quit();
		}
	}

	@Test(priority = 6)
	private void screenShot() throws IOException {

		ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File("/Users/karthikgunasekaran/eclipse-workspace/Amazon_Project/target//goToCart.png");
		FileHandler.copy(source, destination);
	}

	@Test(priority = 7)
	private void goToCart() throws IOException, InterruptedException {
		driver.findElement(By.xpath("(//a[normalize-space() ='Go to Cart'])[2]")).click();

	}

	@Test(priority = 8)
	
	private void compareToProduct() throws IOException, InterruptedException {

		WebElement goToCartProduct = driver.findElement(By.xpath(
				"//h1[normalize-space() ='Shopping Cart']/ancestor::div[2]/following-sibling::form//li[1]/span"));
		String lastPageProductText = goToCartProduct.getText();
		
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File("/Users/karthikgunasekaran/eclipse-workspace/Amazon_Project/target//addToCart.png");
		FileHandler.copy(source, destination);

		if (firstProductText.equals(lastPageProductText)) {
			System.out.println("PRODUCT NAMES ARE SAME" + '\n' + lastPageProductText + '\n');
			Thread.sleep(3000);
		}
	}

	@Test(priority = 9) // EXCEPTION ANNOTATION
	private void proceedToBuy() throws InterruptedException {
		driver.findElement(By.name("proceedToRetailCheckout")).click();

		driver.close();
		Thread.sleep(3000);
	}
	

	@AfterTest
	private void browserClose() {
		driver.quit();
	}
}
