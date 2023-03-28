package com.amazon.mini;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon_Mini {
	
	static String expected = "Baby";
	static String expected2 = "toys";
	
	public static void main(String[] args) throws Throwable {
		
		 List<String> l = new ArrayList<>();
		 l.add("start-maximized");
		 l.add("incognito");
		 
		WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments(l);
		WebDriver driver = new ChromeDriver(opt);
		
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement dropDown = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
		Select opt1 = new Select(dropDown);
		List<WebElement> options = opt1.getOptions();
		int size = options.size();

		List<String> r = new ArrayList<String>();

		for (int i = 0; i < size; i++) {
			String text = options.get(i).getText();
			r.add(text);
		}

		boolean contains = r.contains(expected);
		if (contains == true) {	
			System.out.println("Present in Dropdown : " + expected);
			opt1.selectByVisibleText(expected);

		} else {
			System.out.println("Not present in Dropdown : " + expected);
			driver.close();
		}
		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys(expected2);
		Thread.sleep(2000);

		WebElement tr = driver.findElement(By.cssSelector("[class='autocomplete-results-container']"));
		boolean contains2 = tr.getText().contains(expected2);
		if (contains2 == true) {
			System.out.println("Available : " + expected2);
			driver.findElement(By.xpath("(//div[text()='toys'])[1]")).click();

		} else {
			driver.close();
		}
		WebElement toyProduct = driver
				.findElement(By.xpath("//span[text()='RESULTS']/ancestor::div[4]/following-sibling::div//h2[1]"));
		String prodName = toyProduct.getText();
		System.out.println(prodName);
		

		WebElement price = driver.findElement(By.xpath(
				"//span[text()='RESULTS']/ancestor::div[4]/following-sibling::div//child::div/child::div/child::div//div[3]/child::div/child::div/following-sibling::div[1]//span[@data-a-size=\"b\"][1]"));
		String firstPrice = price.getText();
		System.out.println(firstPrice+'\n');
		toyProduct.click();
		

		String first = driver.getWindowHandle();

		Set<String> mul = driver.getWindowHandles();

		for (String st : mul) {
			if (!st.equals(first)) {
				driver.switchTo().window(st);
			}
		}
		WebElement pc = driver.findElement(By.id("productTitle"));
		String prodName1 = pc.getText();
		System.out.println(prodName1);

		WebElement price2 = driver.findElement(By.xpath(
				"//span[@id=\"productTitle\"]/ancestor::div[2]/following-sibling::div//span[@data-a-strike=\"true\"]"));
		String secPrice = price2.getText();
		System.out.println(secPrice+'\n');

		if (prodName.contains(prodName1) && firstPrice.contains(secPrice)) {
			System.out.println("BOTH PRODUCT NAMES AND SAME PRICES ARE SAME"+'\n');

		} else {
			driver.close();
		}
		driver.findElement(By.id("add-to-cart-button")).click();

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File("/Users/karthikgunasekaran/eclipse-workspace/MiniProject/ScreenShot//amazon.png");
		FileUtils.copyFile(source, destination);

		driver.findElement(By.xpath("//a[@data-csa-c-content-id='sw-gtc_CONTENT']")).click();

		WebElement pd = driver.findElement(By.xpath(
				"//h1/ancestor::div[2]/following-sibling::form/child::div[2]//span[@data-a-word-break='normal'][1]"));
		String prodName2 = pd.getText();
		System.out.println(prodName2+'\n');
		
		if (prodName2.contains(prodName)) {
			System.out.println("PRODUCT NAMES ARE SAME ");
			
			File source1 = ts.getScreenshotAs(OutputType.FILE);
			File destination2 = new File("/Users/karthikgunasekaran/eclipse-workspace/MiniProject/ScreenShot//amazon1.png");
			FileUtils.copyFile(source1, destination2);
			
			driver.close();
		}
		Thread.sleep(3000);
		driver.quit();
		

		
		
	}

}
