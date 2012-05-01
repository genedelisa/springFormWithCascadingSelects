package com.rockhoppertech.spring.web.integration.selenium;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.MatcherAssert.*;

import static org.hamcrest.Matchers.*;

/**
 * @author gene
 * 
 */
public class RegisterPageTest {

	private static WebDriver driver;
	private RegisterPage page;
	private static final String BASE_URL = "http://localhost:8080/springFormWithCascadingSelects";

	//@BeforeClass
	public static void initWebDriver() {
		// driver = new HtmlUnitDriver();
		driver = new FirefoxDriver();
	}

	//@AfterClass
	public static void quitDriver() {
		driver.quit();
	}

	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		this.page = new RegisterPage(driver, BASE_URL);
		this.page.get();
	}

	@After
	public void tearDown() {
		this.page = null;
		driver.quit();
	}

	@Test
	public void shouldFindNJInStateSelectOptions() {
		assertThat("the page has NJ in the select options",
				page.shouldFindNJInStatesSelect(), equalTo(true));
	}

	@Test
	public void shouldFindNJCitiesIfNJIsSelected() {
		assertThat("the page has Haddonfield in the NJ select options",
				page.shouldPopulateNJCitiesIfNJIsSelected(), equalTo(true));
	}

	@Test
	public void shouldDisplayOutputDivWhenCityIsSelected() {
		assertThat("the output div has been populated",
				page.shouldDisplayOutputDivWhenCitySelected(), equalTo(true));
	}

	@Test
	public void shouldNaviateToAccountPageOnSubmit() {
		AccountPage ap = page.shouldNaviateToAccountPageOnSubmit();

		assertThat("the returned page has the correct title",
				ap.shouldHaveCorrectTitle(), equalTo(true));
	}

}