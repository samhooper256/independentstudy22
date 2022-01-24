package day7;

import java.util.*;

public class State {

	private final Set<City> cities;
	private final String name;
	private final int population;
	
	public State(String name, int population, City... cities) {
		this.name = name;
		this.population = population;
		this.cities = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(cities)));
	}
	
	public String getName() {
		return name;
	}
	
	/** The returned set is unmodifiable. */
	public Set<City> getCities() {
		return cities;
	}
	
	public int getPopulation() {
		return population;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cities, name, population);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(!(obj instanceof State))
			return false;
		State other = (State) obj;
		return Objects.equals(cities, other.cities) && Objects.equals(name, other.name)
				&& population == other.population;
	}

	@Override
	public String toString() {
		return String.format("State[%s, %s]", name, cities);
	}
	
}
