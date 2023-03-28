package com.Amazon.page.object.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Product_Buy_Page {

	public static WebDriver driver = null;

	@FindBy(xpath = "//h1[normalize-space() ='Shopping Cart']/ancestor::div[2]/following-sibling::form//li[1]/span")
	private WebElement gotoCartProduct;

	@FindBy(name = "proceedToRetailCheckout")
	private WebElement proceedToBuy;

	public WebElement getGotoCartProduct() {
		return gotoCartProduct;
	}

	public WebElement getProceedToBuy() {
		return proceedToBuy;
	}
	public Product_Buy_Page(WebDriver driver1) {
		this.driver = driver1;
		PageFactory.initElements(driver1, this);
		
	}

}
