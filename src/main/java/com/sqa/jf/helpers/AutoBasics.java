/**
 * File Name: AutoBasics.java<br>
 * LastName, FirstName<br>
 * Java Boot Camp Exercise<br>
 * Instructor: Jean-francois Nepton<br>
 * Created: Nov 2, 2016
 */
package com.sqa.jf.helpers;

import java.io.*;
import java.util.*;

import org.apache.commons.io.*;
import org.apache.log4j.*;
import org.openqa.selenium.*;

/**
 * AutoBasics //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 * 
 * @author LastName, FirstName
 * @version 1.0.0
 * @since 1.0
 */
public class AutoBasics {

	public static final String DEFAULT_SCREENSHOT_FILENAME = "screenshot";

	public static final String DEFAULT_SCREENSHOT_SAVE_LOCATION = "screenshots/";

	public static final String FILE_EXTENSION = ".jpg";

	public static List<WebElement> getByTagName(WebDriver driver, String tagName) {
		List<WebElement> elements = driver.findElements(By.tagName(tagName));
		return elements;
	}

	public static List<WebElement> getCSSPropertyBasedElements(WebDriver driver, By locator, String prop,
			String value) {
		List<WebElement> elements = driver.findElements(locator);
		List<WebElement> matchingElements = new ArrayList<WebElement>();
		for (int i = 0; i < elements.size(); i++) {
			String elementValue = elements.get(i).getCssValue(prop);
			if (elementValue.equalsIgnoreCase(value)) {
				matchingElements.add(elements.get(i));
			}
		}
		return elements;
	}

	public static List<WebElement> getLinks(WebDriver driver) {
		List<WebElement> elements = getByTagName(driver, "a");
		return elements;
	}

	public static List<WebElement> getPictures(WebDriver driver) {
		List<WebElement> elements = getByTagName(driver, "img");
		return elements;
	}

	public static List<String> getTextContents(WebDriver driver, By locator) {
		List<WebElement> elements = driver.findElements(locator);
		List<String> elementTexts = new ArrayList<String>();
		for (int i = 0; i < elements.size(); i++) {
			String text = elements.get(i).getText();
			if (!text.equals("")) {
				elementTexts.add(text);
			}
		}
		return elementTexts;
	}

	public static boolean takeScreenshot(WebDriver driver) {
		return takeScreenshot(driver, DEFAULT_SCREENSHOT_SAVE_LOCATION, DEFAULT_SCREENSHOT_FILENAME, null);
	}

	public static boolean takeScreenshot(WebDriver driver, String location, String filename) {
		return takeScreenshot(driver, location, filename, null);
	}

	public static boolean takeScreenshot(WebDriver driver, String location, String filename, Logger logger) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(location + filename + FILE_EXTENSION));
		} catch (IOException e) {
			if (logger != null) {
				logger.error("Failed to save screenshot at " + location + filename + FILE_EXTENSION);
			}
			return false;
		}
		return true;
	}
}
