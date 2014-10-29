package org.gandhi.location.commons.model;

public class City extends Location{

	private final String name;
	
	private final long population;
	
	private City(Location location, String name, long population){
		super(location);
		this.name = name;
		this.population = population;
	}

	public static City createCityFromDecimalMeasures(double decimalLatitude, double decimalLongitude, String name, long population){
		return new City(Location.createLocationFromDecimalMeasures(decimalLatitude, decimalLongitude), name, population);
	}
	
	public static City createCityFromRadianMeasures(double radianLatitude, double radianLongitude, String name, long population){
		return new City(Location.createLocationFromRadianMeasures(radianLatitude, radianLongitude), name, population);
	}
	
	public static City createCityFromCartesianMeasures(double cartesianX, double cartesianY, double cartesianZ, String name, long population){
		return new City(Location.createLocationFromCartesianMeasures(cartesianX, cartesianY, cartesianZ), name, population);
	}
	
	public String getName() {
		return name;
	}

	public long getPopulation() {
		return population;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (population ^ (population >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (population != other.population)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "City [getName()=" + getName() + ", getPopulation()=" + getPopulation() + ", getDecimalLatitude()=" + getDecimalLatitude() + ", getDecimalLongitude()=" + getDecimalLongitude() + ", getRadianLatitude()=" + getRadianLatitude()
				+ ", getRadianLongitude()=" + getRadianLongitude() + ", getCartesianX()=" + getCartesianX() + ", getCartesianY()=" + getCartesianY() + ", getCartesianZ()=" + getCartesianZ() + "]";
	}
}