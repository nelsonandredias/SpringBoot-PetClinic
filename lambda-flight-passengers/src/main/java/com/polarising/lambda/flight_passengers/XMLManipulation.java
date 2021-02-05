package com.polarising.lambda.flight_passengers;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLManipulation {
	
	public Integer getTagValue (Document document, String parentTagName, List<String> childTagNames) {
		
		List<Integer> flightPassengers = new ArrayList<Integer>();
		String tagValue= null;
		
		//We get the respective root element.
        NodeList nList = document.getElementsByTagName(parentTagName);
        
        // run through element content
        for (int i = 0; i < nList.getLength(); i++) {
        	
        	Node nNode = nList.item(i);

            System.out.println("Current Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            	
            	Element elem = (Element) nNode;
            	
            	for (String childTagName : childTagNames) {
            	    System.out.println("Current Tag Name: " + childTagName);
            	    Node node1 = elem.getElementsByTagName(childTagName).item(0);
                	tagValue = node1.getTextContent();
                	flightPassengers.add(Integer.parseInt(tagValue));
                	System.out.println(childTagName + ": " + tagValue);
            	}

            	return calculateTotalFlightPassengers(flightPassengers.get(0), flightPassengers.get(1));
            }
        }

        return null;
    }
	
	public Integer calculateTotalFlightPassengers (Integer flightAdults, Integer flightChildrens) {
		Integer totalFlightPassengers = flightAdults + flightChildrens;
		System.out.println("totalFlightPassengers: " + totalFlightPassengers);
		return totalFlightPassengers;
	}

}
