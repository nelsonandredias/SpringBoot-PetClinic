package com.polarising.lambda.flight.models.requests;

public class FlightDateRangeRequest {
	
	private String aC_OWNER_Key;
	private String aC_OWNER_Value;
	private String aC_OWNER_Alias;
	private String aF_DAT_Key;
	private String aF_DAT_Min;
	private String aF_DAT_Max;
	
	public FlightDateRangeRequest() {
	}
	
	public FlightDateRangeRequest(String aC_OWNER_Key, String aC_OWNER_Value, String aC_OWNER_Alias, String aF_DAT_Key,
			String aF_DAT_Min, String aF_DAT_Max) {
		super();
		this.aC_OWNER_Key = aC_OWNER_Key;
		this.aC_OWNER_Value = aC_OWNER_Value;
		this.aC_OWNER_Alias = aC_OWNER_Alias;
		this.aF_DAT_Key = aF_DAT_Key;
		this.aF_DAT_Min = aF_DAT_Min;
		this.aF_DAT_Max = aF_DAT_Max;
	}

	public String getaC_OWNER_Key() {
		return aC_OWNER_Key;
	}

	public void setaC_OWNER_Key(String aC_OWNER_Key) {
		this.aC_OWNER_Key = aC_OWNER_Key;
	}

	public String getaC_OWNER_Value() {
		return aC_OWNER_Value;
	}

	public void setaC_OWNER_Value(String aC_OWNER_Value) {
		this.aC_OWNER_Value = aC_OWNER_Value;
	}

	public String getaC_OWNER_Alias() {
		return aC_OWNER_Alias;
	}

	public void setaC_OWNER_Alias(String aC_OWNER_Alias) {
		this.aC_OWNER_Alias = aC_OWNER_Alias;
	}

	public String getaF_DAT_Key() {
		return aF_DAT_Key;
	}

	public void setaF_DAT_Key(String aF_DAT_Key) {
		this.aF_DAT_Key = aF_DAT_Key;
	}

	public String getaF_DAT_Min() {
		return aF_DAT_Min;
	}

	public void setaF_DAT_Min(String aF_DAT_Min) {
		this.aF_DAT_Min = aF_DAT_Min;
	}

	public String getaF_DAT_Max() {
		return aF_DAT_Max;
	}

	public void setaF_DAT_Max(String aF_DAT_Max) {
		this.aF_DAT_Max = aF_DAT_Max;
	}
	
	
	
}

