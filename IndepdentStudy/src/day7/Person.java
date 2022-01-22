package day7;

import java.util.Objects;

public class Person {

	private final String firstName, lastName;
	private final City city;
	
	public Person(String firstName, String lastName, City city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public City getCity() {
		return city;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(!(obj instanceof Person))
			return false;
		Person other = (Person) obj;
		return Objects.equals(city, other.city) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName);
	}
	
	@Override
	public String toString() {
		return String.format("Person[%s %s, city=%s]", getFirstName(), getLastName(), getCity());
	}
	
}
