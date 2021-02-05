package com.polarising.lambda;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.polarising.lambda.models.json.FlightItems;
import com.polarising.lambda.models.json.JSONRequest;
import com.polarising.lambda.models.json.JSONResponse;
import com.polarising.lambda.services.FormatConverter;

public class ApiWrapperHandler implements RequestHandler<JSONRequest, List<FlightItems>> {

	// enable pretty print JSON output
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public List<FlightItems> handleRequest(JSONRequest request, Context context) {
		
		LambdaLogger logger = context.getLogger();
		FormatConverter formatConverter = new FormatConverter();
		List<FlightItems> flightItemsList = new ArrayList<FlightItems>();
		
		// log execution details
	    //logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
	    //logger.log("CONTEXT: " + gson.toJson(context));
	    
		
		 // process event
	    //logger.log("EVENT: " + gson.toJson(request));
	    //logger.log("EVENT TYPE: " + request.getClass().toString());
		
	    JSONRequest jsonRequest = gson.fromJson(gson.toJson(request), JSONRequest.class);
		
		try {
			flightItemsList = formatConverter.convertJsonToXml(jsonRequest);

			for(FlightItems item : flightItemsList) {
		
				System.out.println(item.toString());
			}
			return flightItemsList;
		
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	
}
