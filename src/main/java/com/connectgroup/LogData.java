package com.connectgroup;


public class LogData {
	private String timeStamp;
	private String country;
	private long responseTime;
	
	public LogData(String timeStamp, String country, long responseTime) {
		super();
		this.timeStamp = timeStamp;
		this.country = country;
		this.responseTime = responseTime;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
	@Override
	public String toString() {
		return "[" + timeStamp + "," + country
				+ "," + responseTime + "]";
	}
	



}
