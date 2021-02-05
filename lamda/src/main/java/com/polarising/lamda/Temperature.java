package com.polarising.lamda;

public class Temperature {
	
	String city;
	double temperature;
	
	public Temperature() {
	}

	public Temperature(String city, double temperature) {
		super();
		this.city = city;
		this.temperature = temperature;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return "Temperature [city=" + city + ", temperature=" + temperature + "]";
	}
	
	

}
