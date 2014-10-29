package org.gandhi.location.commons.model;

public class Location {

	private final double decimalLatitude;
	
	private final double decimalLongitude;
	
	private final double radianLatitude;
	
	private final double radianLongitude;
	
	private final double cartesianX;
	
	private final double cartesianY;
	
	private final double cartesianZ;
	
	public Location(Location location){
		this.decimalLatitude = location.decimalLatitude;
		this.decimalLongitude = location.decimalLongitude;
		
		this.radianLatitude = location.radianLatitude;
		this.radianLongitude = location.radianLongitude;
		
		this.cartesianX = location.cartesianX;
		this.cartesianY = location.cartesianY;
		this.cartesianZ = location.cartesianZ;	
	}
	
	private Location(double decimalLatitude, double decimalLongitude, double radianLatitude, double radianLongitude, double cartesianX, double cartesianY, double cartesianZ){
		this.decimalLatitude = decimalLatitude;
		this.decimalLongitude = decimalLongitude;
		
		this.radianLatitude = radianLatitude;
		this.radianLongitude = radianLongitude;
		
		this.cartesianX = cartesianX;
		this.cartesianY = cartesianY;
		this.cartesianZ = cartesianZ;	
	}
	
	public static Location createLocationFromDecimalMeasures(double decimalLatitude, double decimalLongitude){
		if(decimalLatitude < -90.0 || decimalLatitude > 90.0 || decimalLongitude < -180.0 || decimalLongitude > 180.0){
			throw new IllegalArgumentException("Invalid latitude and/or longitude provided");
		} else{
			double radianLatitude = calculateRadianFromDecimal(decimalLatitude);
			double radianLongitude = calculateRadianFromDecimal(decimalLongitude);
			double cartesianX = calculateCartesianXFromRadian(radianLatitude, radianLongitude);
			double cartesianY = calculateCartesianYFromRadian(radianLatitude, radianLongitude);
			double cartesianZ = calculateCartesianZFromRadian(radianLatitude, radianLongitude);
			Location location = new Location(decimalLatitude, decimalLongitude, radianLatitude, radianLongitude, cartesianX, cartesianY, cartesianZ);
			return location;
		}
	}
	
	public static Location createLocationFromRadianMeasures(double radianLatitude, double radianLongitude){
		if(radianLatitude < -1.571 || radianLatitude > 1.571 || radianLongitude < -3.142 || radianLongitude > 3.142){
			throw new IllegalArgumentException("Invalid latitude and/or longitude provided");
		} else{
			double decimalLatitude = calculateDecimalFromRadian(radianLatitude);
			double decimalLongitude = calculateDecimalFromRadian(radianLongitude);
			double cartesianX = calculateCartesianXFromRadian(radianLatitude, radianLongitude);
			double cartesianY = calculateCartesianYFromRadian(radianLatitude, radianLongitude);
			double cartesianZ = calculateCartesianZFromRadian(radianLatitude, radianLongitude);
			Location location = new Location(decimalLatitude, decimalLongitude, radianLatitude, radianLongitude, cartesianX, cartesianY, cartesianZ);
			return location;
		}
	}
	
	public static Location createLocationFromCartesianMeasures(double cartesianX, double cartesianY, double cartesianZ){
		if(cartesianX < -1.0 || cartesianX > 1.0 || cartesianY < -1.0 || cartesianY > 1.0 || cartesianZ < -1.0 || cartesianZ > 1.0 ){
			throw new IllegalArgumentException("Invalid latitude and/or longitude provided");
		} else{
			double radianLatitude = calculateRadianLatitudeFromCartesian(cartesianX, cartesianY, cartesianZ);
			double radianLongitude = calculateRadianLongitudeFromCartesian(cartesianX, cartesianY, cartesianZ);
			
			double decimalLatitude = calculateDecimalFromRadian(radianLatitude);
			double decimalLongitude = calculateDecimalFromRadian(radianLongitude);

			Location location = new Location(decimalLatitude, decimalLongitude, radianLatitude, radianLongitude, cartesianX, cartesianY, cartesianZ);
			return location;
		}
	}
	
	private static double calculateRadianLongitudeFromCartesian(double cartesianX, double cartesianY, double cartesianZ){
		return Math.atan2(cartesianY, cartesianX);
	}
	
	private static double calculateRadianLatitudeFromCartesian(double cartesianX, double cartesianY, double cartesianZ){
		double hypotnuse = Math.sqrt(cartesianX*cartesianX+cartesianY*cartesianY);
		return Math.atan2(cartesianZ, hypotnuse);
	}

	private static double calculateDecimalFromRadian(double radian){
		return (180*radian)/Math.PI;
	}
	
	private static double calculateRadianFromDecimal(double decimal){
		return (Math.PI*decimal)/180;
	}
	
	private static double calculateCartesianXFromRadian(double radianLatitude, double radianLongitude){
		return Math.cos(radianLatitude)*Math.cos(radianLongitude);
	}
	
	private static double calculateCartesianYFromRadian(double radianLatitude, double radianLongitude){
		return Math.cos(radianLatitude)*Math.sin(radianLongitude);
	}
	
	private static double calculateCartesianZFromRadian(double radianLatitude, double radianLongitude){
		return Math.sin(radianLatitude);
	}

	public double getDecimalLatitude() {
		return decimalLatitude;
	}

	public double getDecimalLongitude() {
		return decimalLongitude;
	}

	public double getRadianLatitude() {
		return radianLatitude;
	}

	public double getRadianLongitude() {
		return radianLongitude;
	}

	public double getCartesianX() {
		return cartesianX;
	}

	public double getCartesianY() {
		return cartesianY;
	}

	public double getCartesianZ() {
		return cartesianZ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(decimalLatitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(decimalLongitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (Double.doubleToLongBits(decimalLatitude) != Double.doubleToLongBits(other.decimalLatitude))
			return false;
		if (Double.doubleToLongBits(decimalLongitude) != Double.doubleToLongBits(other.decimalLongitude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Location [getDecimalLatitude()=" + getDecimalLatitude() + ", getDecimalLongitude()=" + getDecimalLongitude() + ", getRadianLatitude()=" + getRadianLatitude() + ", getRadianLongitude()=" + getRadianLongitude()
				+ ", getCartesianX()=" + getCartesianX() + ", getCartesianY()=" + getCartesianY() + ", getCartesianZ()=" + getCartesianZ() +"]";
	}
}