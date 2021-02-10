package com.polarising.lambda.models;

public class ReqDateRange {

	private String ac_owner;
	private String af_date_modified;
	private String flight_from_timestamp;
	private String flight_to_timestamp;
	
	public ReqDateRange() {}

	public ReqDateRange(String ac_owner, String af_date_modified, String flight_from_timestamp,
			String flight_to_timestamp) {
		super();
		this.ac_owner = ac_owner;
		this.af_date_modified = af_date_modified;
		this.flight_from_timestamp = flight_from_timestamp;
		this.flight_to_timestamp = flight_to_timestamp;
	}

	public String getAc_owner() {
		return ac_owner;
	}

	public void setAc_owner(String ac_owner) {
		this.ac_owner = ac_owner;
	}

	public String getAf_date_modified() {
		return af_date_modified;
	}

	public void setAf_date_modified(String af_date_modified) {
		this.af_date_modified = af_date_modified;
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
