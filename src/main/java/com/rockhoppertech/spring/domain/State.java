package com.rockhoppertech.spring.domain;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Very simple model of a US State. It has a sorted collection of City objects.
 *
 * The id is unused but is there to use in JPA or Hibernate.
 *
 * @author gene
 *
 */
public class State implements Comparable<State> {
	private Long id;
	private String name;
	private Set<City> cities = new TreeSet<City>();

	public State() {
		this.name = "unknown";
		this.id = System.currentTimeMillis();
	}

	public State(String name) {
		this.name = name;
		this.id = System.currentTimeMillis();
	}

	public State addCity(City city) {
		this.cities.add(city);
		city.setState(this);
		return this;
	}

	public State addCity(String name) {
		City city = new City(name);
		this.cities.add(city);
		return this;
	}

	public int compareTo(State o) {
		return this.name.compareTo(o.name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		State other = (State) obj;
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public Set<City> getCities() {
		return Collections.unmodifiableSet(this.cities);
	}

	public City getCity(String name) {
		City result = null;
		for (City c : this.cities) {
			if (c.getName().equals(name)) {
				return c;
			}
		}
		return result;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.name == null) ? 0 : this.name.hashCode());
		return result;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("State [ ");
		sb.append("id=").append(this.id).append(',');
		sb.append("name=").append(this.name).append(' ');
		sb.append("n cities=").append(this.cities.size()).append(' ');
		sb.append("]");
		return sb.toString();
	}

}
