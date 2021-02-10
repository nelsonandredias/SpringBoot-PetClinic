package com.polarising.lambda;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import com.amazonaws.services.cloudwatch.model.PutMetricDataResult;
import com.amazonaws.services.cloudwatch.model.StandardUnit;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.CloudFrontEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.polarising.lambda.models.FlightItems;
import com.polarising.lambda.models.ReqDateRange;
import com.polarising.lambda.services.CloudWatchManipulation;
import com.polarising.lambda.services.FormatConverter;

public class DateRangeGeneralHandler implements RequestHandler<ReqDateRange, List<FlightItems>> {
	
	//double count = 0;
	
	// enable pretty print JSON output
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	//private CloudWatchManipulation cloudWatchManipulation = new CloudWatchManipulation();
	
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
			
			flightItemsList = formatConverter.convertJsonToXml(jsonRequest);

			//System.out.println("flightItemsList.size(): " + flightItemsList.size());
			//cloudWatchManipulation.initCloudwatchClient();
			//cloudWatchManipulation.publishCustomMetricData( count, jsonRequest.getAf_date_modified());
			//count++;			
			//cloudWatchManipulation.getCustomMetricTimestamp();
			
			return flightItemsList;
		
		} catch (SOAPException e) {
			System.out.println(e.getStackTrace());
		} 
		
		return null;
	}

	
}
