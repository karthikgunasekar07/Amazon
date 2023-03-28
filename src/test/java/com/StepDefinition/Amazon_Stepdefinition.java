package com.StepDefinition;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

import com.Amazon.Helper.ConfigurationHelper;
import com.Amazon.page.object.model.Singleton_Design_pattern;
import com.BaseClass.BaseClass;
import com.Runner.Amazon_Runner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Amazon_Stepdefinition extends BaseClass {

	public static WebDriver driver = Amazon_Runner.driver;

	public static Singleton_Design_pattern sdp = new Singleton_Design_pattern(driver);

	static String expected = null;
	static String expected1 = null;
	static String firstProductText = null;
	static String productPriceText = null;
	static String producttitleText = null;
	static String SecPagePriceText = null;
	static TakesScreenshot ts;

	@Given("user Launch the Url")
	public void user_launch_the_url() throws IOException {
		
		String url = ConfigurationHelper.getInstance().getInstanceCR().getUrl();
		getUrl(url);
		Wait();
	}

	@When("user Select {string} option from Dropdown")
	public void user_select_option_from_dropdown(String dropDownV) {

		expected = dropDownV;

		Select opt1 = new Select(sdp.getHp().getDropDown());
		List<WebElement> options = opt1.getOptions();
		ListIterator<WebElement> dropdownOption = options.listIterator();

		while (dropdownOption.hasNext()) {
			WebElement drop = dropdownOption.next();
			String dropDownText = drop.getText();

			if (expected.equals(dropDownText)) {
				opt1.selectByVisibleText(expected);
				break;
			}
		}
	}

	@When("user Enter {string} Product Name in Searchbox")
	public void user_enter_product_name_in_searchbox(String searchv) throws InterruptedException {
		expected1 = searchv;
		sdp.getHp().getSearchBox().sendKeys(expected1);
		Thread.sleep(2000);
	}

	@Then("select the searched product")
	public void select_the_searched_product() {

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

	@When("User get first product Name")
	public void user_get_first_product_name() {

		firstProductText = sdp.getSrp().getFirstProductName().getText();
		System.out.println(firstProductText);
	}

	@When("User get first product Original Price")
	public void user_get_first_product_original_price() {

		productPriceText = sdp.getSrp().getFirstProductPrice().getText();
		System.out.println(productPriceText + '\n');
	}

	@When("Click the first Product")
	public void click_the_first_product() {

		sdp.getSrp().getFirstProductClick().click();
	}

	@When("driver switch to the next window")
	public void driver_switch_to_the_next_window() {

		String parent = driver.getWindowHandle();
		Set<String> childWindows = driver.getWindowHandles();
		for (String child : childWindows) {

			if (!parent.equals(child)) {
				driver.switchTo().window(child);
			}
		}
	}

	@When("Compare selected product Name")
	public void compare_selected_product_name() {
		producttitleText = sdp.getSpp().getSelectedProductTitle().getText();
		System.out.println(producttitleText);
	}

	@When("Compare selected product Original Price")
	public void compare_selected_product_original_price() {
		SecPagePriceText = sdp.getSpp().getSelectedProductPrice().getText();
		System.out.println(SecPagePriceText + '\n');
	}

	@Then("Click the Add to Cart")
	public void click_the_add_to_cart() {
		if (firstProductText.contains(producttitleText) && productPriceText.equals(SecPagePriceText)) {
			System.out.println("Both Product Names And Prices are Same" + '\n');

			sdp.getSpp().getClickAddToCart().click();

		} else {
			driver.quit();
		}
	}

	@When("Take snap of AddToCart Page")
	public void take_snap_of_add_to_cart_page() throws IOException {
		ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(
				"/Users/karthikgunasekaran/eclipse-workspace/Amazon_Project/target//goToCart_cucu.png");
		FileHandler.copy(source, destination);

	}

	@Then("Click the GoToCart")
	public void click_the_go_to_cart() {

		sdp.getAp().getGotoCart().click();

	}

	@When("compare shipping cart product Name")
	public void compare_shipping_cart_product_name() throws InterruptedException {

		String lastPageProductText = sdp.getPbp().getGotoCartProduct().getText();

		if (firstProductText.equals(lastPageProductText)) {
			System.out.println("PRODUCT NAMES ARE SAME" + '\n' + lastPageProductText + '\n');
			Thread.sleep(3000);
		}
	}

	@When("Take a ScreenShot of Shipping Cart Page")
	public void take_a_screen_shot_of_shipping_cart_page() throws IOException {
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File("/Users/karthikgunasekaran/eclipse-workspace/Amazon_Project/target//addToCart.png");
		FileHandler.copy(source, destination);
	}

	@When("Click the Proceed to Buy")
	public void click_the_proceed_to_buy() throws InterruptedException {
		sdp.getPbp().getProceedToBuy().click();
		Thread.sleep(2000);
	}

	@Then("Close the current window")
	public void close_the_current_window() throws InterruptedException {
		driver.close();
		Thread.sleep(3000);
	}

}
