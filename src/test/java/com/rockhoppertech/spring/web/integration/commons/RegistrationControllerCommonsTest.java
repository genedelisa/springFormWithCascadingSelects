package com.rockhoppertech.spring.web.integration.commons;

import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rockhoppertech.spring.domain.City;
import com.rockhoppertech.spring.domain.State;
import static org.hamcrest.MatcherAssert.*;

import static org.hamcrest.Matchers.*;

/**
 * @author gene
 * @see http://hc.apache.org/httpcomponents-client-ga/
 */
public class RegistrationControllerCommonsTest {
	private static final Logger logger = LoggerFactory
			.getLogger(RegistrationControllerCommonsTest.class);

	// TODO: all of these tests have stopped working. Cannot get a connection.

	@Test
	@Ignore
	public void r() throws Exception {
		String response = CommonsClient.doGet("/register");
		// should be just the html for the view
		logger.debug(response);
	}

	@Ignore
	@Test
	public void states() throws Exception {
		String response = CommonsClient.doGet("/states");
		// have Jackson deserialize the JSON
		ObjectMapper m = new ObjectMapper();
		Set<State> states = m.readValue(response,
				new TypeReference<Set<State>>() {
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
		logger.debug(response);
	}

	@Ignore
	@Test
	public void cities() throws Exception {
		String response = CommonsClient.doGet("/cities?stateName=NJ");

		// should be json
		logger.debug("Cities in NJ");
		logger.debug(response);

		ObjectMapper m = new ObjectMapper();
		Set<City> cities = m.readValue(response,
				new TypeReference<Set<City>>() {
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

	}
}
