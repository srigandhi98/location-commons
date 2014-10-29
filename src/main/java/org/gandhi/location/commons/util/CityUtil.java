package org.gandhi.location.commons.util;

import java.util.ArrayList;
import java.util.List;

import org.gandhi.location.commons.model.City;

public class CityUtil {
	public static List<City> buildCityListFromDecimalLatitudesAndLongitudes(double[] decimalLatitudes, double[] decimalLongitudes, String[] names, long[] populations){
		int sampleSize = names.length;
		if( sampleSize != 0 && decimalLatitudes.length == sampleSize && decimalLongitudes.length == sampleSize && populations.length == sampleSize){
			List<City> cities = new ArrayList<City>();
			for(int i=0;i<sampleSize;i++){
				City city = City.createCityFromDecimalMeasures(decimalLatitudes[i], decimalLongitudes[i], names[i], populations[i]);
				cities.add(city);
			}
			return cities;
		} else{
			throw new IllegalArgumentException("unEqual number of data points are passed");
		}
	}
}
