package com.BaseClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;

	public static WebDriver browser_Launch(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			
			List<String> l = new LinkedList<>();
			l.add("start-maximized");
			l.add("incognito");
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments(l);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(opt);
			
		} else if (browser.equalsIgnoreCase("safari")) {

			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();

		}
		return driver;
	}

	public static void getUrl(String value) {
		driver.get(value);

	}

	public static void maximize() {
		driver.manage().window().maximize();

	}

	public static void Wait() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public static void inputValue(WebElement value, String Email) {
		value.sendKeys(Email);

	}

	public static void password(WebElement Value, String password) {
		Value.sendKeys(password);
	}

	public static void click(WebElement Login) {
		Login.click();
	}

	public static void ScreenShot(String screen) throws Throwable {
		TakesScreenshot shot = (TakesScreenshot) driver;
		File source = shot.getScreenshotAs(OutputType.FILE);
		File destination = new File(screen);
		FileUtils.copyFile(source, destination);

	}

	public static void navigateTo(String Value) {
		driver.navigate().to(Value);
	}

	public static void navigateBack() {
		driver.navigate().back();
	}

	public static void navigateForward() {
		driver.navigate().forward();
	}

	public static void navigateRefresh() {
		driver.navigate().refresh();
	}

	public static void alert(String value) {
		if (value.equalsIgnoreCase("accept")) {
			driver.switchTo().alert().accept();
		} else if (value.equalsIgnoreCase("dismiss")) {

		}

	}

	public static void mouseMovement(WebDriver driver, WebElement element, String value) {
		Actions act = new Actions(driver);

		if (value.equalsIgnoreCase("click")) {
			act.click(element).perform();

		} else if (value.equalsIgnoreCase("right")) {
			act.contextClick(element).perform();
		} else if (value.equalsIgnoreCase("move")) {
			act.moveToElement(element).perform();
		} else if (value.equalsIgnoreCase("doubleClick")) {
			act.doubleClick(element).perform();
		}

	}

	public static void robot() throws AWTException {
		Robot rob = new Robot();
		rob.keyPress(KeyEvent.VK_DOWN);
		rob.keyRelease(KeyEvent.VK_DOWN);
		rob.keyPress(KeyEvent.VK_ENTER);
		rob.keyRelease(KeyEvent.VK_ENTER);

	}

	public static void windowHandle(String value) {
		if (value.equalsIgnoreCase("windowhandle")) {
			driver.getWindowHandle();
		} else if (value.equalsIgnoreCase("windhandles")) {
			driver.getWindowHandles();
		}

	}

	public static void Dropdown(WebElement value, String Select) {
		Select opt = new Select(value);
		opt.selectByVisibleText(Select);

	}

	public static void isEnabled(WebElement element) {
		boolean enabled = element.isEnabled();
		System.out.println(enabled);

	}

	public static void isDisplayed(WebElement element) {
		boolean displayed = element.isDisplayed();
		System.out.println(displayed);
	}

	public static void isSelected(WebElement element) {
		boolean selected = element.isSelected();
		System.out.println(selected);
	}

	public static void getText(WebElement value) {
		String text = value.getText();
		System.out.println(text);

	}

	public void getCurrentUrl(WebElement element) {
		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);

	}

	public static void getTitle() {
		String title = driver.getTitle();
		System.out.println(title);
	}

	public static void getAttribute(WebElement element, String Value) {
		String attribute = element.getAttribute(Value);
		System.out.println(attribute);

	}

	public static void Clear(WebElement action, String value) {
		action.clear();
		action.sendKeys(value);

	}

	public static void javaScriptExe(WebElement scroll_Down) {
		JavascriptExecutor rr = (JavascriptExecutor) driver;
		rr.executeScript("arguments[0].scrollIntoView();", scroll_Down);

	}

	public static void Close() {
		driver.close();

	}

	public static void quit() {
		driver.quit();

	}

	public static void Logout(WebElement value) {
		value.click();

	}

	public static void sleep(int Value) throws InterruptedException {
		Thread.sleep(Value);

	}

}
