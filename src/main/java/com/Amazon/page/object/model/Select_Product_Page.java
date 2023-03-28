package com.Amazon.page.object.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Select_Product_Page {
	
	public static WebDriver driver = null;
	
	@FindBy(id = "productTitle" )
	private WebElement selectedProductTitle;
	
	@FindBy(xpath = "//span[@id='productTitle']/ancestor::div[2]/following-sibling::div[9]//table[1]//span[@data-a-strike='true']")
	private WebElement selectedProductPrice;
	
	@FindBy(id = "add-to-cart-button")
	private WebElement clickAddToCart;

	public WebElement getSelectedProductTitle() {
		return selectedProductTitle;
	}

	public WebElement getSelectedProductPrice() {
		return selectedProductPrice;
	}

	public WebElement getClickAddToCart() {
		return clickAddToCart;
	}
	
	public Select_Product_Page(WebDriver driver1) {
		this.driver = driver1;
		PageFactory.initElements(driver1, this);
		
	}

	
}
