package com.polarising.lambda.flight_passengers.services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.polarising.lambda.flight_passengers.Constants;
import com.polarising.lambda.flight_passengers.XMLManipulation;

public class PassengersImpl implements Passengers {
	
	XMLManipulation xmlManipulation;

	public PassengersImpl(XMLManipulation xmlManipulation) {
		super();
		this.xmlManipulation = xmlManipulation;
	}


	@Override
	public Integer getTotalFlightPassengers(String flightID) {

		System.out.println("\nflightID : " + flightID);
		
		Integer totalFlightPassengers = null;
		//XMLManipulation xmlManipulation = new XMLManipulation();
	
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = null;

			dBuilder = factory.newDocumentBuilder();
			InputSource inputSource = new InputSource(new StringReader(Constants.TEST_XML_STRING));
			
			//The parse() method parses the XML file into a Document.
			Document document = dBuilder.parse(inputSource);
			
			//Normalizing the document helps generate correct results.
			document.getDocumentElement().normalize();

			List<String> listChildTagNames = new ArrayList<String>();
			listChildTagNames.add(Constants.FLIGHT_NUMBER_ADULTS);
			listChildTagNames.add(Constants.FLIGHT_NUMBER_CHILDRENS);

			totalFlightPassengers = xmlManipulation.getTagValue(document, "PAX_OBJ", listChildTagNames);

			return totalFlightPassengers;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return totalFlightPassengers;
	}

}
