package com.polarising.lambda;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.polarising.lambda.models.FlightItems;
import com.polarising.lambda.models.ReqDateRange;
import com.polarising.lambda.services.FormatConverter;

public class DateRangeUpdateHandler implements RequestHandler<ReqDateRange, List<FlightItems>> {

	String lastAfModified = null;
	
	// enable pretty print JSON output
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public List<FlightItems> handleRequest(ReqDateRange request, Context context) {
		
		LambdaLogger logger = context.getLogger();
		FormatConverter formatConverter = new FormatConverter();
		List<FlightItems> flightItemsList = new ArrayList<FlightItems>();
		
		// log execution details
		logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
	    logger.log("CONTEXT: " + gson.toJson(context));
	    
		// process event
	    logger.log("EVENT: " + gson.toJson(request));
	    logger.log("EVENT TYPE: " + request.getClass().toString());
		
	    ReqDateRange jsonRequest = gson.fromJson(gson.toJson(request), ReqDateRange.class);
		
		try {

			// use last aF_MODIFIED
			if(lastAfModified != null) {
				System.out.println("setting last aF_MODIFIED: "+ lastAfModified);
				jsonRequest.setAf_date_modified(lastAfModified);
			}
			
			flightItemsList = formatConverter.convertJsonToXml(jsonRequest);
			 
			return flightItemsList;
		
		} catch (SOAPException e) {
			System.out.println(e.getStackTrace());
		}
		
		return null;
	}

	
}
