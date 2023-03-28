package com.Amazon.page.object.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	public static WebDriver driver = null;
	
	@FindBy(id = "searchDropdownBox")
	private WebElement dropDown;
	
	@FindBy(id = "twotabsearchtextbox")
	private WebElement searchBox;
	
	@FindBy(xpath = "//div[@class='autocomplete-results-container']/div")
	private WebElement searchOption;

	public WebElement getDropDown() {
		return dropDown;
	}

	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getSearchOption() {
		return searchOption;
	}
	
	public HomePage(WebDriver driver1) {
		this.driver = driver1;
		PageFactory.initElements(driver1, this);
	}
	


}
