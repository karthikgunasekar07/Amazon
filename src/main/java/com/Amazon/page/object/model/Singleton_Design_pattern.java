package com.Amazon.page.object.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Singleton_Design_pattern {
	
	public static WebDriver driver = null;
	
	private static HomePage hp;
	private static Search_Result_Page srp;
	private static Select_Product_Page spp;
	private static AddtoCart_Page ap;
	private static Product_Buy_Page pbp;
	
	
	public static HomePage getHp() {
		hp = new HomePage(driver);
		return hp;
	}
	public static Search_Result_Page getSrp() {
		srp = new Search_Result_Page(driver);
		return srp;
	}
	public static Select_Product_Page getSpp() {
		spp = new Select_Product_Page(driver);
		return spp;
	}
	public static AddtoCart_Page getAp() {
		ap = new AddtoCart_Page(driver);
		return ap;
	}
	public static Product_Buy_Page getPbp() {
		pbp = new Product_Buy_Page(driver);
		return pbp;
	}
	
	public Singleton_Design_pattern(WebDriver driver1) {
		this.driver = driver1;
		PageFactory.initElements(driver1, this);
	}

}
