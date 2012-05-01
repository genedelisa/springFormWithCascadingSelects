package com.rockhoppertech.spring.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rockhoppertech.spring.domain.City;
import com.rockhoppertech.spring.domain.State;

/**
 * @author gene
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class GeoServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(GeoServiceTest.class);

	@Inject
	private GeoService service;

	@Test
	public void shouldFindAllStates() {
		Set<State> states = service.findAllStates();
		assertThat("the states are not null", states, is(not(nullValue())));
		for (State s : states) {
			logger.debug(s.toString());
		}
		assertThat("this correct number of states is returned", states.size(),
				is(equalTo(3)));
	}

	@Test
	public void shouldContainSpecifiedStateInFindAllStates() {
		Set<State> states = service.findAllStates();
		for (State s : states) {
			logger.debug(s.toString());
		}

		assertThat("the state collection has the specified state", states,
				hasItem(new State("NJ")));
	}

	@Test
	public void shouldFindSpecifiedCity() {
		Set<City> cities = service.findCitiesForState("NJ");
		assertThat("the cities in NJ are not null", cities,
				is(not(nullValue())));
		for (City c : cities) {
			logger.debug(c.toString());
		}
		assertThat("the cities collection has the specified city", cities,
				hasItem(new City("Haddonfield")));
	}

}
