/**
 * File Name: EbayTest.java<br>
 * LastName, FirstName<br>
 * Java Boot Camp Exercise<br>
 * Instructor: Jean-francois Nepton<br>
 * Created: Nov 2, 2016
 */
package com.sqa.jf;

import java.util.concurrent.*;

import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.testng.annotations.*;

import com.sqa.jf.helpers.*;

/**
 * EbayTest //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 * 
 * @author LastName, FirstName
 * @version 1.0.0
 * @since 1.0
 */
public class BasicAutoTest {

	private String baseURL;

	private WebDriver driver;

	private Logger log = Logger.getLogger(BasicAutoTest.class);

	public BasicAutoTest(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getBaseURL() {
		return this.baseURL;
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public Logger getLog() {
		return this.log;
	}

	@BeforeClass
	public void setupTest() {
		// Setup test
		getLog().info("Setting up driver.");
		this.driver = new FirefoxDriver();
		getLog().trace("Setting implicit wait to 30 secconds.");
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getLog().trace("Setting window to fullscreen.");
		this.driver.manage().window().maximize();
		getLog().info("Going to baseURL.");
		this.driver.get(this.baseURL);
		getLog().debug("Clearing cookies.");
		this.driver.manage().deleteAllCookies();
	}

	public boolean takeScreenshot(String filename) {
		return AutoBasics.takeScreenshot(getDriver(), "screenshots/", filename, getLog());
	}

	@AfterClass
	public void tearDown() {
		// Close test
		getLog().info("Closing all driver windows.");
		this.driver.quit();
	}
}
