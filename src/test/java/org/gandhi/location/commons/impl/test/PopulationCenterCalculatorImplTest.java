package org.gandhi.location.commons.impl.test;

import java.util.List;

import org.gandhi.location.commons.impl.GeometricPopulationCenterCaluclatorImpl;
import org.gandhi.location.commons.model.City;
import org.gandhi.location.commons.util.CityUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class PopulationCenterCalculatorImplTest {
	
	public static void main(String args[]){
		PopulationCenterCalculatorImplTest populationCenterCalculatorImplTest = new PopulationCenterCalculatorImplTest();
		setUp();
		populationCenterCalculatorImplTest.testCase1();
		populationCenterCalculatorImplTest.testCase2();
		tearDown();
	}
	
	private static GeometricPopulationCenterCaluclatorImpl populationCenterCalculatorImpl;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	
	@BeforeClass
	public static void setUp(){
		populationCenterCalculatorImpl = new GeometricPopulationCenterCaluclatorImpl();
	}
	
	@AfterClass
	public static void tearDown(){
		populationCenterCalculatorImpl = null;
	}
	
	@Before
	public void logBeforeMethod(){
		System.out.println("--------testCase Starting----------");
	}
	
	@After
	public void logAfterMethod(){
		System.out.println("--------testCase Executed successfully----------");
		System.out.println();
	}
	
	//Valid testCase with a set of ten dataPoints - Everything should work fine and an answer should be returned
	@Test
	public void testCase1(){
		System.out.println("successTestCase1");

		String[] names = {"Newyork", "Chicago", "Atlanta"};
		
		double[] decimalLatitudes = {40.7143528, 41.8781136, 33.7489954};
		
		double[] decimalLongitudes = {-74.0059731, -87.6297982, -84.3879824};
		
		long [] populations = {1095, 730, 365};
		
		List<City> cities= CityUtil.buildCityListFromDecimalLatitudesAndLongitudes(decimalLatitudes, decimalLongitudes, names, populations);		
		City calculatedClosestCity  = populationCenterCalculatorImpl.findCityCloseToThePopulationCentre(cities);
		City expectedClosestCity  = City.createCityFromDecimalMeasures(40.7143528, -74.0059731, "Newyork", 1095);
		Assert.assertEquals(expectedClosestCity, calculatedClosestCity);
		
	}
	
	//Valid testCase with a set of ten dataPoints - Everything should work fine and an answer should be returned
	@Test
	public void testCase2(){
		System.out.println("successTestCase2");

		String[] names = {"Newyork", "Chicago", "Atlanta"};
		
		double[] decimalLatitudes = {40.7143528, 41.8781136, 33.7489954};
		
		double[] decimalLongitudes = {-74.0059731, -87.6297982, -84.3879824};
		
		long [] populations = {1095, 730, 365};
		
		List<City> cities= CityUtil.buildCityListFromDecimalLatitudesAndLongitudes(decimalLatitudes, decimalLongitudes, names, populations);		
		City calculatedClosestCity  = populationCenterCalculatorImpl.findCityCloseToThePopulationCentre(cities);
		City expectedClosestCity  = City.createCityFromDecimalMeasures(40.7143528, -74.0059731, "Newyork", 1095);
		Assert.assertEquals(expectedClosestCity, calculatedClosestCity);
		
	}
	//invalid number of data points
	@Test
	public void testCase3(){
		System.out.println("failTestCase1");
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("unEqual number of data points are passed");
		
		String[] names = {"Newyork"};
		
		double[] decimalLatitudes = {40.7143528, 23.9};
		
		double[] decimalLongitudes = {-74.0059731, 12.9};
		
		long [] populations = {1095};
		
		CityUtil.buildCityListFromDecimalLatitudesAndLongitudes(decimalLatitudes, decimalLongitudes, names, populations);		
	}
	
	//invalid latitude or longitude
	@Test
	public void testCase4(){		
		System.out.println("failTestCase2");
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Invalid latitude and/or longitude provided");
		
		String[] names = {"Newyork"};
		
		double[] decimalLatitudes = {91.7143528};
		
		double[] decimalLongitudes = {-74.0059731};
		
		long [] populations = {1095};
		
		CityUtil.buildCityListFromDecimalLatitudesAndLongitudes(decimalLatitudes, decimalLongitudes, names, populations);		
	}
}