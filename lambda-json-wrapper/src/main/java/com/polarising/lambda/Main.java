package com.polarising.lambda;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.polarising.lambda.models.ReqDateRange;

public class Main {

	public static void main(String[] args) {
		
			Context context = new Context() {
			
			public int getRemainingTimeInMillis() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public int getMemoryLimitInMB() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public LambdaLogger getLogger() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getLogStreamName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getLogGroupName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getInvokedFunctionArn() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public CognitoIdentity getIdentity() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getFunctionVersion() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getFunctionName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public ClientContext getClientContext() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getAwsRequestId() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		ReqDateRange jsonRequest = new ReqDateRange();
		jsonRequest.setAc_owner("TUI");
		jsonRequest.setFlight_from_timestamp("2021-01-21T17:45:00");
		jsonRequest.setFlight_to_timestamp("2021-02-26T17:45:00");
		jsonRequest.setAf_date_modified("2021-02-26T17:45:00");
		
		DateRangeGeneralHandler dateRangeGeneralHandler = new DateRangeGeneralHandler();
		
		dateRangeGeneralHandler.handleRequest(jsonRequest, context);
		
	}

}
