package com.connectgroup.coding_test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.connectgroup.DataFilterer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        
        File fileMultiLine = new File(classLoader.getResource("multi-lines").getFile());
        File fileEmpty = new File(classLoader.getResource("empty").getFile());
        File fileSingleLine = new File(classLoader.getResource("single-line").getFile());
        try {
        	System.out.println("===========Multi Line File Results============");
			System.out.println(DataFilterer.filterByCountry(new FileReader(fileMultiLine), "US"));
			System.out.println(DataFilterer.filterByCountryWithResponseTimeAboveLimit(new FileReader(fileMultiLine), "US", 780));
			System.out.println(DataFilterer.filterByResponseTimeAboveAverage(new FileReader(fileMultiLine)));
			
			System.out.println("===========Empty File Results============");
			System.out.println(DataFilterer.filterByCountry(new FileReader(fileEmpty), "US"));
			System.out.println(DataFilterer.filterByCountryWithResponseTimeAboveLimit(new FileReader(fileEmpty), "US", 780));
			System.out.println(DataFilterer.filterByResponseTimeAboveAverage(new FileReader(fileEmpty)));
			
			System.out.println("===========Single Line File Results============");
			System.out.println(DataFilterer.filterByCountry(new FileReader(fileSingleLine), "GB"));
			System.out.println(DataFilterer.filterByCountryWithResponseTimeAboveLimit(new FileReader(fileSingleLine), "US", 780));
			System.out.println(DataFilterer.filterByResponseTimeAboveAverage(new FileReader(fileSingleLine)));
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
    }
}
