package com.polarising.lambda.services;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.polarising.lambda.models.FlightItems;

public class XMLManipulation {
	
	public List<FlightItems> getTagValue (Document document, String parentTagName) {
		
		List<FlightItems> flightItemsList = new ArrayList<FlightItems>();
		FlightItems flightItems = null;
		
		//We get the respective root element.
        NodeList nList = document.getElementsByTagName(parentTagName);
        
        // run through element content
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
           Node node = nList.item(temp);
           if (node.getNodeType() == Node.ELEMENT_NODE)
           {
              Element eElement = (Element) node;
              //Create new flightItems Object
              flightItems = new FlightItems();
              flightItems.setaF_ID(eElement.getElementsByTagName("AF_ID").item(0).getTextContent());
              flightItems.setaF_DEP(eElement.getElementsByTagName("AF_DEP").item(0).getTextContent());
              flightItems.setaF_DES(eElement.getElementsByTagName("AF_DES").item(0).getTextContent());
              flightItems.setaF_AL(eElement.getElementsByTagName("AF_AL").item(0).getTextContent());
              flightItems.setaF_OWNER(eElement.getElementsByTagName("AF_OWNER").item(0).getTextContent());
              flightItems.setaF_NR(eElement.getElementsByTagName("AF_NR").item(0).getTextContent());
              flightItems.setaF_DAT(eElement.getElementsByTagName("AF_DAT").item(0).getTextContent());
              flightItems.setaF_LEG(eElement.getElementsByTagName("AF_LEG").item(0).getTextContent());
              flightItems.setaF_AN_S(eElement.getElementsByTagName("AF_AN_S").item(0).getTextContent());
              flightItems.setaF_AB_S(eElement.getElementsByTagName("AF_AB_S").item(0).getTextContent());
              flightItems.setaF_AN_E(eElement.getElementsByTagName("AF_AN_E").item(0).getTextContent());
              flightItems.setaF_AB_E(eElement.getElementsByTagName("AF_AB_E").item(0).getTextContent());
              flightItems.setaF_AN_A(eElement.getElementsByTagName("AF_AN_A").item(0).getTextContent());
              flightItems.setaF_AB_A(eElement.getElementsByTagName("AF_AB_A").item(0).getTextContent());
              flightItems.setaF_PLKZ(eElement.getElementsByTagName("AF_PLKZ").item(0).getTextContent());
              flightItems.setaF_FLA_PURPOSE(eElement.getElementsByTagName("AF_FLA_PURPOSE").item(0).getTextContent());
              flightItems.setaC_REG(eElement.getElementsByTagName("AC_REG").item(0).getTextContent());
              flightItems.setaF_DEP_SAL(eElement.getElementsByTagName("AF_DEP_SAL").item(0).getTextContent());
              flightItems.setaF_DES_SAL(eElement.getElementsByTagName("AF_DES_SAL").item(0).getTextContent());
              flightItems.setaF_SUFFIX(eElement.getElementsByTagName("AF_SUFFIX").item(0).getTextContent());
              flightItems.setaF_AB_O(eElement.getElementsByTagName("AF_AB_O").item(0).getTextContent());
              flightItems.setaF_AN_O(eElement.getElementsByTagName("AF_AN_O").item(0).getTextContent());
              flightItems.setaF_TOGO(eElement.getElementsByTagName("AF_TOGO").item(0).getTextContent());
              flightItems.setaF_AB_EO(eElement.getElementsByTagName("AF_AB_EO").item(0).getTextContent());
              flightItems.setaF_AN_EO(eElement.getElementsByTagName("AF_AN_EO").item(0).getTextContent());
              flightItems.setaF_MODIFIED(eElement.getElementsByTagName("AF_MODIFIED").item(0).getTextContent());
              flightItems.setaC_OWNER(eElement.getElementsByTagName("AC_OWNER").item(0).getTextContent());
              //Add flightItems to list
              flightItemsList.add(flightItems);
           }
        }
        return flightItemsList;
    }

}
