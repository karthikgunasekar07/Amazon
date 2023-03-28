package com.Amazon.page.object.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddtoCart_Page {
	
	public static WebDriver driver = null;
	
	@FindBy(xpath = "(//a[normalize-space() ='Go to Cart'])[2]")
	private WebElement gotoCart;

	public WebElement getGotoCart() {
		return gotoCart;
	}
	
	public AddtoCart_Page(WebDriver driver1) {
		this.driver = driver1;
		PageFactory.initElements(driver1, this);
		
	}
	

}
