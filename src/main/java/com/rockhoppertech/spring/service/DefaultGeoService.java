package com.rockhoppertech.spring.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.rockhoppertech.spring.domain.City;
import com.rockhoppertech.spring.domain.State;

/**
 * Just a stub for testing.
 * 
 * @author gene
 *
 */
@Service
public class DefaultGeoService implements GeoService {
	private Map<String, State> states = new LinkedHashMap<String, State>();

	public DefaultGeoService() {
		State state = new State("NJ");
		state.addCity("Haddonfield").addCity("Cherry Hill").addCity("Marlton");
		this.states.put(state.getName(), state);

		state = new State("PA");
		// Carville's joke
		state.addCity("Philadelphia").addCity("Pittsburgh").addCity("Alabama");
		this.states.put(state.getName(), state);

		state = new State("NY");
		state.addCity("Sewer").addCity("Flushing").addCity("Armpit");
		state.addCity("Jerkville").addCity("Moronica").addCity("Shithole");
		this.states.put(state.getName(), state);
	}

	public Set<City> findCitiesForState(String stateName) {
		State state = this.states.get(stateName);
		return state.getCities();
	}

	public Set<State> findAllStates() {
		Set<State> set = new TreeSet<State>();
		set.addAll(this.states.values());
		return set;
	}

}
