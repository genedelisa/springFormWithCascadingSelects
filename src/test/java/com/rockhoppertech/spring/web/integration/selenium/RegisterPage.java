package com.rockhoppertech.spring.web.integration.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gene
 * @see http://code.google.com/p/selenium/wiki/PageObjects
 * @see http://code.google.com/p/selenium/wiki/LoadableComponent
 */
public class RegisterPage extends LoadableComponent<RegisterPage> {

	private WebDriver driver;
	private String baseURL;
	private String path = "/register";
	private String pageTItle = "Register";

	// By default the PageFactory will locate elements with the same name or id
	// as the field.
	// @CacheLookup
	private WebElement usStates;

	// you can change the name
	@FindBy(name = "city")
	@CacheLookup
	private WebElement cities;

	private WebElement output;

	// we don't need any additional annotations.
	private WebElement submit;

	private static final Logger logger = LoggerFactory
			.getLogger(RegisterPage.class);

	public RegisterPage(WebDriver driver, String baseURL) {
		this.driver = driver;
		this.baseURL = baseURL;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),
				this);
	}

	@Override
	protected void load() {
		logger.debug("loading register page");

		driver.get(this.baseURL + path);
	}

	@Override
	protected void isLoaded() throws Error {
		logger.debug("checking isloaded register page");
		String url = driver.getCurrentUrl();
		if (!url.endsWith(path)) {
			throw new Error("This is not the Register page URL: " + url);
		}

		String title = driver.getTitle();
		if (!this.pageTItle.equals(title)) {
			throw new Error("This is not the Register page title: " + title);
		}
	}

	public boolean shouldFindNJInStatesSelect() {

		WebElement nj = driver.findElement(By
				.xpath("//select[@id='usStates']/option[@value='NJ']"));

		// cannot do this:
		// usStates.findElement(By.xpath("./option[@value='NJ']"));

		logger.debug(String.format("found this state element '%s'",
				nj.getAttribute("value")));
		return nj.getAttribute("value").equals("NJ") ? true : false;
	}

	public boolean shouldPopulateNJCitiesIfNJIsSelected() {
		WebElement nj = driver.findElement(By
				.xpath("//select[@id='usStates']/option[@value='NJ']"));
		logger.debug(String.format("found this state element '%s'",
				nj.getAttribute("value")));
		nj.setSelected();
		waitForHaddonfieldOption();

		WebElement haddonfield = driver.findElement(By
				.xpath("//select[@id='city']/option[@value='Haddonfield']"));

		logger.debug(String.format("found this city element '%s'",
				haddonfield.getAttribute("value")));
		return haddonfield.getAttribute("value").equals("Haddonfield") ? true
				: false;
	}

	public boolean shouldDisplayOutputDivWhenCitySelected() {
		WebElement nj = driver.findElement(By
				.xpath("//select[@id='usStates']/option[@value='NJ']"));
		nj.setSelected();
		waitForHaddonfieldOption();

		WebElement haddonfield = driver.findElement(By
				.xpath("//select[@id='city']/option[@value='Haddonfield']"));
		haddonfield.setSelected();

		logger.debug(String.format("output div text is '%s'", output.getText()));

		return output.getText().equals("You selected City Haddonfield") ? true
				: false;

		// driver.findElement(By.id("submit")).click();
	}

	public AccountPage shouldNaviateToAccountPageOnSubmit() {
		submit.click();
		return new AccountPage(driver, baseURL);
	}

	private void waitForHaddonfieldOption() {
		// ajax wait
		ExpectedCondition<Boolean> cond = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					driver.findElement(By
							.xpath("//select[@id='city']/option[@value='Haddonfield']"));
					logger.debug("Found Haddonfield in wait");
				} catch (NoSuchElementException e) {
					logger.debug("Waiting for Haddonfield in wait");
					return false;
				}
				return true;
			}
		};
		long timeOutInSeconds = 15L;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(cond);
	}

}
