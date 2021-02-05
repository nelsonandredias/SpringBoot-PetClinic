package com.polarising.lambda.flight_passengers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class Main {

	public static void main(String[] args) {
		
		//PassengersImpl passengersImpl = new PassengersImpl(new XMLManipulation());
		
		//FlightPassengers flightPassengers = new FlightPassengers(passengersImpl);
		
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
		
		List<String> inputRequest  = new ArrayList<String>();
		
		inputRequest.add("19849390");
		inputRequest.add("19849394");
		
		//flightPassengers.handleRequest(inputRequest, context);

	}

}
