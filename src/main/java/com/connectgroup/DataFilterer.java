package com.connectgroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DataFilterer {
	public static Collection<?> filterByCountry(Reader source, String country) {
		BufferedReader br = new BufferedReader(source);
		List filteredLogDataList = new ArrayList<>();
		String st; 
		int i = 0;
		try{
			while ((st = br.readLine()) != null) {
				if(i == 0){
					i++;
					continue;
				}
				String[] dataValues = st.split(",");
				if(dataValues != null && dataValues.length == 3){
					if(dataValues[1].equalsIgnoreCase(country)){
						LogData logData = new LogData(dataValues[0], dataValues[1], Long.decode(dataValues[2]));
						filteredLogDataList.add(logData);
					}
				}else{
					System.out.println("Not a proper log data");
				}
			} 
		}
		catch(IOException ioEx){
			System.out.println("IOException occured == "+ioEx.getMessage());
		}
		catch(Exception e){
			System.out.println("Generic Exception occured == "+e.getMessage());
		}
		return filteredLogDataList;
	}

	public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit){
		BufferedReader br = new BufferedReader(source);
		List filteredLogDataList = new ArrayList<>();
		String st; 
		int i = 0;
		try{
			while ((st = br.readLine()) != null) {
				if(i == 0){
					i++;
					continue;
				}
				String[] dataValues = st.split(",");
				if(dataValues != null && dataValues.length == 3){
					if(dataValues[1] != null && dataValues[1].equalsIgnoreCase(country) && dataValues[2] != null && Long.decode(dataValues[2])> limit){
						LogData logData = new LogData(dataValues[0], dataValues[1], Long.decode(dataValues[2]));
						filteredLogDataList.add(logData);
					}
				}else{
					System.out.println("Not a proper log data");
				}
			} 
		}
		catch(IOException ioEx){
			System.out.println("IOException occured == "+ioEx.getMessage());
		}
		catch(Exception e){
			System.out.println("Generic Exception occured == "+e.getMessage());
		}
		return filteredLogDataList;
	}

	public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {
		BufferedReader br = new BufferedReader(source);
		List<Long> responseTimeList = null;
		List<LogData> logDataList = new ArrayList<LogData>();
		String st; 
		int i =0;
		try{
			while ((st = br.readLine()) != null) {
				if(i == 0){
					i++;
					continue;
				}
				String[] dataValues = st.split(",");
				if(dataValues != null && dataValues.length == 3){
					LogData logData = new LogData(dataValues[0], dataValues[1], Long.decode(dataValues[2]));
					logDataList.add(logData);
					if(responseTimeList == null){
						responseTimeList = new ArrayList<Long>();
					}
					responseTimeList.add(Long.decode(dataValues[2]));

				}else{
					System.out.println("Not a proper log data");
				}
			} 
		}
		catch(IOException ioEx){
			System.out.println("IOException occured == "+ioEx.getMessage());
		}
		catch(Exception e){
			System.out.println("Generic Exception occured == "+e.getMessage());
		}
		if(responseTimeList != null){
			Double average = responseTimeList.stream().mapToLong(n -> n).average().getAsDouble();
			return logDataList.stream().filter(o-> o.getResponseTime() > average).collect(Collectors.toList());
		}
		else{
			return logDataList;
		}
	}
}