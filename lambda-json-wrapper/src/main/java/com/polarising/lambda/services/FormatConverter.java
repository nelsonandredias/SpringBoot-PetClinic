package com.polarising.lambda.services;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.polarising.lambda.models.FlightItems;
import com.polarising.lambda.models.ReqDateRange;

public class FormatConverter {
	
	public List<FlightItems> convertJsonToXml(ReqDateRange ReqDateRange) throws SOAPException {

		String user = "INTERFACE_WBS_TIBCO";
		String password = "e><change4all!";
		String userCredentials = user + ":" + password;
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
		String authHeaderValue = "Basic " + basicAuth;

		String soapEndpointUrl = "https://aws-poc-flight-date-list.getsandbox.com:443/orawsv/INTERFACE_OBJ/GET_FLIGHT_DATE_LIST";
		String contentType = "text/xml;charset=UTF-8";
		String soapAction = "GET_FLIGHT_DATE_LIST";

		List<FlightItems> flightItemsList = new ArrayList<FlightItems>();

		try {

			SOAPMessage soapResponse = callSoapWebService(soapEndpointUrl, "639", contentType, soapAction, authHeaderValue, ReqDateRange);

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			soapResponse.writeTo(out);
			String strMsg = new String(out.toByteArray());

			flightItemsList = parseSoapMessageXML(strMsg);
						
			return flightItemsList;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return null;
	}

	public static void createSoapEnvelope(SOAPMessage soapMessage, ReqDateRange ReqDateRange) throws SOAPException {
		SOAPPart soapPart = soapMessage.getSOAPPart();

		String myNamespace = "get";
		String myNamespaceURI = "http://xmlns.oracle.com/orawsv/INTERFACE_OBJ/GET_FLIGHT_DATE_LIST";

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

		/*
		 * Constructed SOAP Request Message: <soapenv:Envelope
		 * xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:get=
		 * "http://xmlns.oracle.com/orawsv/INTERFACE_OBJ/GET_FLIGHT_DATE_LIST">
		 * <soapenv:Header/> <soapenv:Body> <get:GET_FLIGHT_DATE_LISTInput>
		 * <get:P_AC_OWNER-VARCHAR2-IN>TUI</get:P_AC_OWNER-VARCHAR2-IN>
		 * <get:P_AF_MODIFIED_SINCE-TIMESTAMP-IN>2021-01-24T17:45:00</get:P_AF_MODIFIED_SINCE-TIMESTAMP-IN>
		 * <get:P_FLIGHT_FROM-TIMESTAMP-IN>2021-01-21</get:P_FLIGHT_FROM-TIMESTAMP-IN>
		 * <get:P_FLIGHT_TO-TIMESTAMP-IN>2021-02-26</get:P_FLIGHT_TO-TIMESTAMP-IN>
		 * <get:L_RETURN_STR-VARCHAR2-OUT/>
		 * <get:P_FLIGHT_LIST-FLIGHT_LIST_DATE_OBJ-COUT/>
		 * </get:GET_FLIGHT_DATE_LISTInput> </soapenv:Body> </soapenv:Envelope>
		 */
		
		System.out.println("ac_owner: " + ReqDateRange.getAc_owner() + ", flight_from_timestamp: " + ReqDateRange.getFlight_from_timestamp() + ","
				+ " flight_to_timestamp: " + ReqDateRange.getFlight_to_timestamp() + ", af_date_modified: " + ReqDateRange.getAf_date_modified());
		
		String getFlight_from_timestamp = ReqDateRange.getFlight_from_timestamp().substring(0, ReqDateRange.getFlight_from_timestamp().indexOf("T"));
		String getFlight_to_timestamp = ReqDateRange.getFlight_to_timestamp().substring(0, ReqDateRange.getFlight_to_timestamp().indexOf("T"));
		String getAf_date_modified = ReqDateRange.getAf_date_modified();
		
		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement("GET_FLIGHT_DATE_LISTInput", myNamespace);
		SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("P_AC_OWNER-VARCHAR2-IN", myNamespace);
		soapBodyElem1.addTextNode(ReqDateRange.getAc_owner());
		SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("P_AF_MODIFIED_SINCE-TIMESTAMP-IN", myNamespace);
		soapBodyElem2.addTextNode(getAf_date_modified);
		SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("P_FLIGHT_FROM-TIMESTAMP-IN", myNamespace);
		soapBodyElem3.addTextNode(getFlight_from_timestamp);
		SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("P_FLIGHT_TO-TIMESTAMP-IN", myNamespace);
		soapBodyElem4.addTextNode(getFlight_to_timestamp);
		SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("RETURN_STR-VARCHAR2-OUT", myNamespace);
		soapBodyElem5.addTextNode("");
		SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("P_FLIGHT_LIST-FLIGHT_LIST_DATE_OBJ-COUT",	myNamespace);
		soapBodyElem6.addTextNode("");

	}

	public static SOAPMessage createSOAPRequest(String contentLength, String ContentType, String soapAction,
			String authorization, ReqDateRange ReqDateRange) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		createSoapEnvelope(soapMessage, ReqDateRange);

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("Content-Length", contentLength);
		headers.addHeader("Content-Type", ContentType);
		headers.addHeader("SOAPAction", soapAction);
		//headers.addHeader("Authorization", authorization);

		soapMessage.saveChanges();

		/* Print the request message, just for debugging purposes */
		//System.out.println("Request SOAP Message:");
		//soapMessage.writeTo(System.out);
		//System.out.println("\n");
		return soapMessage;
	}

	public SOAPMessage callSoapWebService(String soapEndpointUrl, String contentLength, String ContentType,
			String soapAction, String authorization, ReqDateRange ReqDateRange) {
		try {
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();

			// Send SOAP Message to SOAP Server
			SOAPMessage soapResponse = soapConnection.call(
					createSOAPRequest(contentLength, ContentType, soapAction, authorization, ReqDateRange), soapEndpointUrl);
	          
			// Print the SOAP Response
			// System.out.println("Response SOAP Message:");
			// soapResponse.writeTo(System.out);
			// System.out.println();

			soapConnection.close();

			return soapResponse;

		} catch (Exception e) {
			System.err.println(
					"\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
			e.printStackTrace();
		}
		return null;
	}

	public List<FlightItems> parseSoapMessageXML(String soapResponse) {
	
		XMLManipulation xmlManipulation = new XMLManipulation();
		
		// Initialize a list of flightItems
		List<FlightItems> flightItemsList = new ArrayList<FlightItems>();
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = null;

			dBuilder = factory.newDocumentBuilder();
			InputSource inputSource = new InputSource(new StringReader(soapResponse));

			// The parse() method parses the XML file into a Document.
			Document document = dBuilder.parse(inputSource);
			// Normalizing the document helps generate correct results.
			document.getDocumentElement().normalize();

			flightItemsList = xmlManipulation.getTagValue(document, "FLIGHT_DATE_OBJ");
			//for (FlightItems flightItem : flightItemsList) {
			//	System.out.println(flightItem.toString());
			//}
			return flightItemsList;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
