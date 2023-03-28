package com.Amazon.Helper;

import java.io.IOException;

import com.Amazon.Reader.ConfigurationReader;

public class ConfigurationHelper {

	private ConfigurationHelper() {

	}

	public static ConfigurationReader getInstanceCR() throws IOException {

		ConfigurationReader reader = new ConfigurationReader();
		return reader;

	}

	public static ConfigurationHelper getInstance() {

		ConfigurationHelper helper = new ConfigurationHelper();
		return helper;
	}

}
