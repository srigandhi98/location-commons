package org.gandhi.location.commons.api;

import java.util.List;

import org.gandhi.location.commons.model.City;

public interface PopulationCenterCalculator {
	
	City findCityCloseToThePopulationCentre(List<City> cities);
	
}
