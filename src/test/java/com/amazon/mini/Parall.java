package com.amazon.mini;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Parall {

	
	
	@Test
	private void browserLaunch1() {
		List<String> l = new ArrayList<>();
		l.add("start-maximized");
		l.add("incognito");

		WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments(l);
	WebDriver	driver = new ChromeDriver(opt);
	}
	@Test
	private void browserLaunch2() {
		List<String> l = new ArrayList<>();
		l.add("start-maximized");
		l.add("incognito");

		WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments(l);
		WebDriver	driver = new ChromeDriver(opt);
	}

}
