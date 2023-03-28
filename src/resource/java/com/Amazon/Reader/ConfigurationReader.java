package com.Amazon.Reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

	public static Properties p;

	public ConfigurationReader() throws IOException {

		File f = new File(
				"/Users/karthikgunasekaran/eclipse-workspace/Amazon_Project/src/resource/java/Amazon.properties");

		FileInputStream fis = new FileInputStream(f);

		p = new Properties();
		p.load(fis);

	}

	public static String getBrowser() {
		String browser = p.getProperty("browser");
		return browser;
	}

	public static String getUrl() {
		String url = p.getProperty("url");
		return url;
	}

}
