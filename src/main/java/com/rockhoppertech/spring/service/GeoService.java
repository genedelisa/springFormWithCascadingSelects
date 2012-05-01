package com.rockhoppertech.spring.service;

import java.util.Set;

import com.rockhoppertech.spring.domain.City;
import com.rockhoppertech.spring.domain.State;

public interface GeoService {

	public Set<State> findAllStates();

	public Set<City> findCitiesForState(String state);

}
