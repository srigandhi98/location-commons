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
		System.out.println("--------testCase Executed----------");
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
		System.out.println("Caluclated closestCity - "+calculatedClosestCity);
		System.out.println("Expected closestCity - "+expectedClosestCity);
		
	}
	
	//Valid testCase with a set of ten dataPoints - Everything should work fine and an answer should be returned
	@Test
	public void testCase2(){
		System.out.println("successTestCase2");

		String[] names = {"Hyderabad", "Bangalore", "Delhi", "Mumbai", "Chennai", "Etikoppaka", "NagarKarnool", "Newyork", "Houston", "Ahmedabad"};
		
		double[] decimalLatitudes = {17.366, 12.9667, 28.6139, 18.975, 13.0839, 17.5000, 16.4833, 40.7127, 29.7628, 23.0300};
		
		double[] decimalLongitudes = {78.4760, 77.5667, 77.2089, 72.8258, 80.2700, 82.7300, 78.3333, -74.0059, -95.3831, 72.5800};
		
		long [] populations = {7749334, 8499399, 21753486, 20998395, 8696010, 12000, 26801, 23484225 , 6313158, 6352254};
		
		List<City> cities= CityUtil.buildCityListFromDecimalLatitudesAndLongitudes(decimalLatitudes, decimalLongitudes, names, populations);		
		City calculatedClosestCity  = populationCenterCalculatorImpl.findCityCloseToThePopulationCentre(cities);
		City expectedClosestCity  = City.createCityFromDecimalMeasures(28.6139, 77.2089, "Delhi", 21753486);
		Assert.assertEquals(expectedClosestCity, calculatedClosestCity);
		System.out.println("Caluclated closestCity - "+calculatedClosestCity);
		System.out.println("Expected closestCity - "+expectedClosestCity);
		
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