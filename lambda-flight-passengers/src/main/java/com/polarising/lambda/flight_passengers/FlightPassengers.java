package com.polarising.lambda.flight_passengers;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.polarising.lambda.flight_passengers.services.PassengersImpl;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class FlightPassengers implements RequestHandler<List<String>, String>{

	/*Passengers passengers;
	
	public FlightPassengers(Passengers passengers) {
		super();
		this.passengers = passengers;
	}*/
	
	PassengersImpl passengers = new PassengersImpl(new XMLManipulation());

	// enable pretty print JSON output
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	//XMLManipulation xmlManipulation = new XMLManipulation();
	
	public String handleRequest(List<String> inputRequest, Context context) {
		
		
		LambdaLogger logger = context.getLogger();
		
		System.out.println(Constants.WELCOME_MESSAGE);
		
		// log execution details
	    logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
	    logger.log("CONTEXT: " + gson.toJson(context));	    
		
	    // process event
	    logger.log("EVENT: " + gson.toJson(inputRequest));
	    logger.log("EVENT TYPE: " + inputRequest.getClass().toString());
	    
	    Jedis redisInstance = new Jedis(new HostAndPort(Constants.REDIS_ENDPOINT, Constants.REDIS_PORT));
	    
	    for (String AF_ID : inputRequest) {
	    	Integer AF_ID_totalPassengers = passengers.getTotalFlightPassengers(AF_ID);
	    	
	    	redisInstance.set(AF_ID, AF_ID_totalPassengers.toString());
	    	System.out.println("AF_ID : " + AF_ID + " ; AF_ID_totalPassengers : " + AF_ID_totalPassengers);
	    	
	    }
		
		return "OK";
	}

	
}
