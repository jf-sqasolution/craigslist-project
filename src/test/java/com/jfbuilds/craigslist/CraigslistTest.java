/**
 * A class which extends BasicAutoTest to demonstrate a test method which searches
 * Craigslist for a set of keywords
 * <p>
 * Core fields are not present but exist in super class such as baseURL, log, and
 * driver - accessed via getters
 * <p>
 * Core methods specifically for a Craigslist test such as goToJobPageAndSearch 
 * which goes to the JOb's sections and searches for keywords. If using a Page
 * Object Model Approach this would be a good contender for one of it's methods.
 * 
 * @author Nepton, Jean-francois
 * @version 1.0.0
 * @since 1.0
 */

package com.jfbuilds.craigslist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jfbuilds.jf.BasicAutoTest;

public class CraigslistTest extends BasicAutoTest {

	public CraigslistTest() {
		// Set baseURL super class variable to Craigslist for the Bay Area (San
		// Francisco)
		super("http://sfbay.craigslist.org");
	}

	@Test
	public void testCL() {
		// Create a String type variable and set it to the key words to search
		// for "qa automation"
		String keywords = "qa automation";
		// Create a String type variable and set it to the title of the expected
		// page I should land on once I do a search
		String expectedTitle = "SF bay area jobs \"" + keywords + "\" - craigslist";
		// Call the private method, which will go to job section and search for
		// keywords specified in keywords variable
		goToJobPageAndSearch(keywords);
		// Create a String variable and set it the the number of results
		// displayed on the first page
		String numOfResults = getDriver().findElement(By.className("rangeTo")).getText();
		// Call the super class logger variable to log the number of results
		// found to a log file
		getLog().info("Number of results on first page for " + keywords + ": " + numOfResults);
		// Create a String variable to hold what the actual page's title is
		// which I land on after I do a search
		String actualTitle = getDriver().getTitle();
		// Assert that a test has reach the expected title versus the actual
		// title that was present
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	private void goToJobPageAndSearch(String keywords) {
		// Go to the baseURL + the directory to search for a job
		getDriver().get(getBaseURL() + "/search/jjj");
		// Create a variable to represent an element of a page, and set it the
		// search field text input box
		WebElement searchField = getDriver().findElement(By.id("query"));
		// Clear the search field box, in case the browser has an auto-complete
		// feature
		searchField.clear();
		// Send the keywords to search for to the search field
		searchField.sendKeys(keywords);
		// Submit the query to the search field
		searchField.submit();
	}
}
