package com.rockhoppertech.spring.web.integration.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementPresent implements ExpectedCondition<WebElement> {
	private static final Logger logger = LoggerFactory
			.getLogger(ElementPresent.class);

	private final By locator;

	public ElementPresent(By locator) {
		this.locator = locator;
	}

	public WebElement apply(WebDriver driver) {
		logger.debug("searching for " + this.locator);
		return driver.findElement(this.locator);
	}

}