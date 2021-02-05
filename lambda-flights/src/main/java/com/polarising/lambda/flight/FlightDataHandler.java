package com.polarising.lambda.flight;


import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.polarising.lambda.flight.dynamoDB.OperationsDynamoDBImpl;
import com.polarising.lambda.flight.models.FlightItems;


public class FlightDataHandler {
	
	// enable pretty print JSON output
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	private OperationsDynamoDBImpl operationsDynamoDB = new OperationsDynamoDBImpl();
	
	public String addSingleFlightDate(Object input, Context context) {
		
		LambdaLogger logger = context.getLogger();
		
		System.out.println("Start handling the request 'addSingleFlightDate' ...");
		
		operationsDynamoDB.initDynamoDbClient();
		
		 // process event
	    logger.log("EVENT: " + gson.toJson(input));
	    logger.log("\nEVENT TYPE: " + input.getClass().toString());
		
	    
	    ObjectMapper mapper = new ObjectMapper();
	    FlightItems flightData = mapper.convertValue(input, FlightItems.class);
	    
		String message = operationsDynamoDB.upsertNewItem(flightData);
	    
	    operationsDynamoDB.destroyDynamoDbClient();
	     
		return message;
	    
	}

	
	public String addBulkFlightDate(List<Object> inputObjectList, Context context) {
		
		LambdaLogger logger = context.getLogger();
		System.out.println("Start handling the request...");
		
		operationsDynamoDB.initDynamoDbClient();
		
		 // process event
	    logger.log("EVENT: " + gson.toJson(inputObjectList));
	    logger.log("\nEVENT TYPE: " + inputObjectList.getClass().toString());
		
	    
	    ObjectMapper mapper = new ObjectMapper();
	    List<Object> flightListData = new ArrayList<>();
	    FlightItems flightData = null;
	    
	    for (Object item : inputObjectList){
	    	flightData = mapper.convertValue(item, FlightItems.class);
	    	//System.out.println("\nflightData: " + flightData);
	    	flightListData.add(flightData);
	    }
	    
		String message = operationsDynamoDB.upsertBulkItems(flightListData);
	    
	    operationsDynamoDB.destroyDynamoDbClient();
	     
		return message;
	    
	}
	
	
	public List<FlightItems> getAllItemsByFlightDate(FlightItems input, Context context) { 

		List<FlightItems> flightListResponse = null;
		
		LambdaLogger logger = context.getLogger();
		
		System.out.println("Start handling the request 'getAllItemsByFlightDate' ...");
		
		operationsDynamoDB.initDynamoDbClient();
		
		flightListResponse = operationsDynamoDB.getAllItemsByPartitionKey("AF_DAT", "13-FEB-21", "#a");
		
		operationsDynamoDB.destroyDynamoDbClient();
		    
		return flightListResponse;
	}


	public List<FlightItems> getAllItemsByFlightDateAndFlightId(FlightItems input, Context context) { 

		List<FlightItems> flightListResponse = null;
		
		LambdaLogger logger = context.getLogger();
		
		System.out.println("Start handling the request 'getAllItemsByFlightDateAndFlightId' ...");
		
		operationsDynamoDB.initDynamoDbClient();
		
		flightListResponse = operationsDynamoDB.getAllItemsByPartitionKeyAndSortKey("AF_DAT", "01-FEB-21", "#a",
				"AF_ID", "19890795", "#afid");
		
		operationsDynamoDB.destroyDynamoDbClient();
		    
		return flightListResponse;
	}
	
	public List<FlightItems> getAllItemsByFlightDateRange(FlightItems input, Context context) { 

		List<FlightItems> flightListResponse = null;
		
		LambdaLogger logger = context.getLogger();
		
		System.out.println("Start handling the request 'getAllItemsByFlightDate' ...");
		
		operationsDynamoDB.initDynamoDbClient();
		
		flightListResponse = operationsDynamoDB.getAllItemsByPartitionSortKeyRange("AC_OWNER", "TUI", "#acowner", "AF_DAT", "13-FEB-21", "14-FEB-21");
		
		operationsDynamoDB.destroyDynamoDbClient();
		    
		return flightListResponse;
	}

}
