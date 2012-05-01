package com.rockhoppertech.spring.web.integration.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gene
 * @see http://code.google.com/p/selenium/wiki/PageObjects
 */
public class HomePage {
	private WebDriver driver;
	private static final Logger logger = LoggerFactory
			.getLogger(HomePage.class);

	public HomePage(WebDriver driver, String baseURL) {
		this.driver = driver;
		driver.get(baseURL + "/");
		PageFactory
				.initElements(new AjaxElementLocatorFactory(driver, 5), this);

		if (!"Home".equals(driver.getTitle())) {
			throw new IllegalStateException("This is not the Home page");
		}
	}

	public RegisterPage shouldFindNJInStatesSelect() {
		WebElement a = driver.findElement(By.xpath("//a"));
		logger.debug(String.format("found this a element '%s'",
				a.getAttribute("url")));
		a.click();
		return PageFactory.initElements(driver, RegisterPage.class);
	}

}
