package com.rockhoppertech.spring.web.integration.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gene
 * @see http://code.google.com/p/selenium/wiki/PageObjects
 */
public class AccountPage {
	private WebDriver driver;
	private static final Logger logger = LoggerFactory
			.getLogger(AccountPage.class);
	private String title = "Account";

	public AccountPage(WebDriver driver, String baseURL) {
		this.driver = driver;
		driver.get(baseURL + "/account");
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15),
				this);

		if (!this.title.equals(driver.getTitle())) {
			throw new IllegalStateException("This is not the correct page");
		}
	}

	//
	// public RegisterPage shouldFindNJInStatesSelect() {
	// WebElement a = driver.findElement(By.xpath("//a"));
	// logger.debug(String.format("found this a element '%s'",
	// a.getAttribute("url")));
	// a.click();
	// return PageFactory.initElements(driver, RegisterPage.class);
	// }

	public boolean shouldHaveCorrectTitle() {
		String dtitle = this.driver.getTitle();
		logger.debug(String.format("checking title'%s' against '%s'", dtitle,
				this.title));
		return dtitle.equals(this.title);
	}

}
