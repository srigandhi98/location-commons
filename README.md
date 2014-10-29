Location Commons : A simple Location utilities library which can be imported as a jar through a maven dependency.

Currently Implementation has the basic model for accommodating concepts like Location as well as more specific concepts like a City. Implementation also includes utilities to build a list of cities from given city data points (like decimalLatitudes, decimalLongitudes, names, populations) + utility to find the meanPopulationLocation for the given list of cities + utility to find the city which closely represents this population centre.(the city among the given list which is closest to the meanPopulationLocation)


Code Organization : 

org.gandhi.location.commons.model - Location & City

org.gandhi.location.commons.api - PopulationCenterCalcuclator - API definition to calculate the population center

org.gandhi.location.commons.impl - GeometricPopulationCenterCalculator - An Implementation of the above API which calculates the population center as on http://www.geomidpoint.com/example.html

org.gandhi.location.commons.util - CityUtil - A simple utility class to construct list of cities from the data points

org.gandhi.location.commons.impl.test - GeometricPopulationCenterCaluclatorImplTest - A jUnit test class testing 2 valid and successful cases + 2 failed cases which throws invalidArguments exceptions.



Testing / Running : 
1st Successful case contains 3 data points;

2nd Successful contains 10 data points;

This app can be tested with own test data by in any of the testMethods & then running it as jUnitTest or java application.


Time Complexity : 

Input  - n dataPoints (decimalLatitudes, decimalLongitudes, names, populations)

PreProcessing : build n city objects - O(n)

finding mean Location - calculating xSum, ySum, zSum, populationSum and then dividing - O(n)

finding closestCityToThisLocation - by calculating the cartesian distance between them - O(n)


Total = n + n + n = 3n steps approximately = O(n)