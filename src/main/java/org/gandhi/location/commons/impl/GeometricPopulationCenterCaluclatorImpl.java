package org.gandhi.location.commons.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.gandhi.location.commons.api.PopulationCenterCalculator;
import org.gandhi.location.commons.model.City;
import org.gandhi.location.commons.model.Location;

public class GeometricPopulationCenterCaluclatorImpl implements PopulationCenterCalculator {

	public Location calculatePopulationCentre(List<City> cities) {

		double populationCenterCartesianX;

		double populationCenterCartesianY;

		double populationCenterCartesianZ;

		BigDecimal cartesianXSum = BigDecimal.ZERO;

		BigDecimal cartesianYSum = BigDecimal.ZERO;

		BigDecimal cartesianZSum = BigDecimal.ZERO;

		BigDecimal populationSum = BigDecimal.ZERO;

		for (City c : cities) {
			BigDecimal thisCityPopulation = BigDecimal.valueOf(c.getPopulation());
			BigDecimal thisCityCartesianX = BigDecimal.valueOf(c.getCartesianX());
			BigDecimal thisCityCartesianY = BigDecimal.valueOf(c.getCartesianY());
			BigDecimal thisCityCartesianZ = BigDecimal.valueOf(c.getCartesianZ());

			populationSum = populationSum.add(thisCityPopulation);
			cartesianXSum = cartesianXSum.add(thisCityCartesianX.multiply(thisCityPopulation));
			cartesianYSum = cartesianYSum.add(thisCityCartesianY.multiply(thisCityPopulation));
			cartesianZSum = cartesianZSum.add(thisCityCartesianZ.multiply(thisCityPopulation));
		}

		if (!populationSum.equals(BigDecimal.ZERO)) {
			populationCenterCartesianX = cartesianXSum.divide(populationSum, 10, RoundingMode.HALF_UP).doubleValue();
			populationCenterCartesianY = cartesianYSum.divide(populationSum, 10, RoundingMode.HALF_UP).doubleValue();
			populationCenterCartesianZ = cartesianZSum.divide(populationSum, 10, RoundingMode.HALF_UP).doubleValue();
		} else {
			populationCenterCartesianX = 0;
			populationCenterCartesianY = 0;
			populationCenterCartesianZ = 0;
		}
		return Location.createLocationFromCartesianMeasures(populationCenterCartesianX, populationCenterCartesianY, populationCenterCartesianZ);
	}

	public City findCityCloseToThePopulationCentre(List<City> cities) {
		City closestCityToPopulationCenter = null;
		
		if (cities != null && !cities.isEmpty()) {
			Location populationCentre = calculatePopulationCentre(cities);
			double maxDistance = Double.MAX_VALUE;
			for (City city : cities) {
				double cartesianXDifferenceSquare = Math.pow(city.getCartesianX() - populationCentre.getCartesianX(), 2);
				double cartesianYDifferenceSquare = Math.pow(city.getCartesianY() - populationCentre.getCartesianY(), 2);
				double cartesianZDifferenceSquare = Math.pow(city.getCartesianZ() - populationCentre.getCartesianZ(), 2);
				double distance = Math.sqrt(cartesianXDifferenceSquare + cartesianYDifferenceSquare + cartesianZDifferenceSquare);

				if (distance < maxDistance) {
					maxDistance = distance;
					closestCityToPopulationCenter = city;
				}
			}
			return closestCityToPopulationCenter;
		} else {
			throw new IllegalArgumentException("null/empty citiesList passed");
		}
	}
}