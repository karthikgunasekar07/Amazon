package com.Amazon.page.object.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Search_Result_Page {
	
	public static WebDriver driver = null;
	
	@FindBy(xpath = "//span[text()='RESULTS']/ancestor::div[4]/following-sibling::div[1]//h2")
	private WebElement firstProductName;
	
	@FindBy(xpath = "//span[text()='RESULTS']/ancestor::div[4]/following-sibling::div[1]//span[@class='a-price a-text-price']")
	private WebElement firstProductPrice;
	
	@FindBy(xpath = "//span[text()='RESULTS']/ancestor::div[4]/following-sibling::div[1]//h2/a")
	private WebElement firstProductClick;

	public WebElement getFirstProductName() {
		return firstProductName;
	}

	public WebElement getFirstProductPrice() {
		return firstProductPrice;
	}

	public WebElement getFirstProductClick() {
		return firstProductClick;
	}
	
	public Search_Result_Page(WebDriver driver1) {
		this.driver = driver1;
		PageFactory.initElements(driver1, this);
	}
	

	
	
}
