package com.polarising.lambda.models.json;

public class JSONRequest {

	private String ac_owner;
	private String flight_from_timestamp;
	private String flight_to_timestamp;
	
	public JSONRequest() {}
	
	public JSONRequest(String ac_owner, String flight_from_timestamp, String flight_to_timestamp) {
		this.ac_owner = ac_owner;
		this.flight_from_timestamp = flight_from_timestamp;
		this.flight_to_timestamp = flight_to_timestamp;
	}

	public String getAc_owner() {
		return ac_owner;
	}

	public void setAc_owner(String ac_owner) {
		this.ac_owner = ac_owner;
	}

	public String getFlight_from_timestamp() {
		return flight_from_timestamp;
	}

	public void setFlight_from_timestamp(String flight_from_timestamp) {
		this.flight_from_timestamp = flight_from_timestamp;
	}

	public String getFlight_to_timestamp() {
		return flight_to_timestamp;
	}

	public void setFlight_to_timestamp(String flight_to_timestamp) {
		this.flight_to_timestamp = flight_to_timestamp;
	}
	
	
	
	
}
