package com.rockhoppertech.spring.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gene
 * 
 */
public class StateTest {
	private static final Logger logger = LoggerFactory
			.getLogger(StateTest.class);

	@Test
	public void shouldFindCity() {
		State nj = new State("NJ");
		nj.addCity("Haddonfield").addCity("Cherry Hill").addCity("Marlton");

		City city = null;

		city = nj.getCity("Haddonfield");
		assertThat("this correct city is returned", city.getName(),
				is(equalTo("Haddonfield")));

	}

	@Test
	public void shouldNotFindCity() {
		State nj = new State("NJ");
		nj.addCity("Haddonfield").addCity("Cherry Hill").addCity("Marlton");

		City city = null;

		city = nj.getCity("Camden");
		assertThat("this city is not found and null is returned", city,
				is(nullValue()));

	}

	@Test
	public void shouldHaveCorrectNumberOfCiies() {
		State nj = new State("NJ");
		nj.addCity("Haddonfield").addCity("Cherry Hill").addCity("Marlton");

		Set<City> cities = null;

		cities = nj.getCities();
		assertThat("the state's cities contains the given city", cities,
				hasItem(new City("Haddonfield")));

		assertThat("this correct number of cities is returned", cities.size(),
				is(equalTo(3)));

		for (City c : cities) {
			logger.debug(c.toString());
		}

	}

}
