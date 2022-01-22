package day7;

import java.util.Objects;

public class City {

	private final String name;
	private final int population;
	
	public City(String name, int population) {
		this.name = name;
		this.population = population;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPopulation() {
		return population;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, population);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(!(obj instanceof City))
			return false;
		City other = (City) obj;
		return Objects.equals(name, other.name) && population == other.population;
	}
	
	@Override
	public String toString() {
		return String.format("City[%s, %d]", getName(), getPopulation());
	}
	
}