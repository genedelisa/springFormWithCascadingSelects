package com.rockhoppertech.spring.web.integration.htmlunit;

import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.rockhoppertech.spring.domain.City;
import com.rockhoppertech.spring.domain.State;

import static org.hamcrest.MatcherAssert.*;

import static org.hamcrest.Matchers.*;

/**
 * @author gene
 * @see http://htmlunit.sourceforge.net/
 */
public class RegisterTest {
	private static final Logger logger = LoggerFactory
			.getLogger(RegisterTest.class);
	public static final String SERVER_URL = "http://rocco:8080/springFormWithCascadingSelects";

	@Test
	public void homePage() throws Exception {
		final WebClient webClient = new WebClient();
		final HtmlPage page = webClient.getPage(SERVER_URL + "/register");
		assertThat("the page title is correct", page.getTitleText(),
				equalTo("Register"));

		final String pageAsXml = page.asXml();
		assertThat("", pageAsXml.contains("<body>"), equalTo(true));

		final String pageAsText = page.asText();
		assertThat("", pageAsText.contains("Register"), equalTo(true));

		webClient.closeAllWindows();
	}

	@Test
	public void states() throws Exception {
		final WebClient webClient = new WebClient();
		Page page = webClient.getPage(SERVER_URL + "/states");
		WebResponse response = page.getWebResponse();
		String json = null;
		if (response.getContentType().equals("application/json")) {
			json = response.getContentAsString();
		} else {
			throw new Exception("No JSON response");
		}

		logger.debug(SERVER_URL + "/states");

		// have Jackson deserialize the JSON
		ObjectMapper m = new ObjectMapper();
		Set<State> states = m.readValue(json, new TypeReference<Set<State>>() {
		});
		assertThat("the states collection is not null", states,
				is(not(nullValue())));

		assertThat("this correct number of states is returned", states.size(),
				is(equalTo(3)));

		assertThat("the states collection has the specified state: NJ", states,
				hasItem(new State("NJ")));

		// should be json
		// [{"cities":[{"name":"Cherry Hill","id":1307731812170},{"name":"Haddonfield","id":1307731812170},{"name":"Marlton","id":1307731812170}],"name":"NJ"},{"cities":[{"name":"Armpit","id":1307731812170},{"name":"Flushing","id":1307731812170},{"name":"Jerkville","id":1307731812170},{"name":"Moronica","id":1307731812170},{"name":"Sewer","id":1307731812170},{"name":"Shithole","id":1307731812170}],"name":"NY"},{"cities":[{"name":"Alabama","id":1307731812170},{"name":"Philadelphia","id":1307731812170},{"name":"Pittsburgh","id":1307731812170}],"name":"PA"}]
		logger.debug("States");
		logger.debug(json);

		webClient.closeAllWindows();
	}

	@Test
	public void cities() throws Exception {
		final WebClient webClient = new WebClient();
		webClient.setAjaxController(new NicelyResynchronizingAjaxController()); 
		
		Page page = webClient.getPage(SERVER_URL + "/cities?stateName=NJ");
		WebResponse response = page.getWebResponse();
		String json = null;
		if (response.getContentType().equals("application/json")) {
			json = response.getContentAsString();
		} else {
			throw new Exception("No JSON response");
		}

		logger.debug(SERVER_URL + "/cities?stateName=NJ");

		// should be json
		logger.debug("Cities in NJ");
		logger.debug(json);

		ObjectMapper m = new ObjectMapper();
		Set<City> cities = m.readValue(json, new TypeReference<Set<City>>() {
		});

		for (City c : cities) {
			logger.debug(c.toString());
		}
		assertThat("the cities in NJ are not null", cities,
				is(not(nullValue())));

		assertThat("this correct number of cities is returned", cities.size(),
				is(equalTo(3)));

		assertThat("the cities collection has the specified city", cities,
				hasItem(new City("Haddonfield")));
		
		webClient.closeAllWindows();

	}
}
