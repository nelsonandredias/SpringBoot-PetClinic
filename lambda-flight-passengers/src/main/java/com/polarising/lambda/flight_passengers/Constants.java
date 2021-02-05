package com.polarising.lambda.flight_passengers;

public final class Constants {

	// welcome message
	public static final String WELCOME_MESSAGE = "Welcome to FlightPassengers lambda function";
	
	// redis instance connection
	public static final String REDIS_ENDPOINT = "my-redis-cluster.ovkhiy.0001.euc1.cache.amazonaws.com";
	public static final Integer REDIS_PORT = 6379;
	
	// SOAP response sample
	public static final int PRETTY_PRINT_INDENT_FACTOR = 4;
    public static final String TEST_XML_STRING ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><GET_PAX_LISTOutput xmlns=\"http://xmlns.oracle.com/orawsv/INTERFACE_OBJ/GET_PAX_LIST\"><P_PAX_LIST><PAX_LIST_OBJ><PAX_LIST><PAX_OBJ><AF_ID>19953901</AF_ID><FLB_CLASS>Y</FLB_CLASS><FLB_TYPE>A</FLB_TYPE><FLB_ADULT>96</FLB_ADULT><FLB_CHD>4</FLB_CHD><FLB_INF>0</FLB_INF><FLB_TRANS/><FLB_MALE>47</FLB_MALE><FLB_FEMALE>49</FLB_FEMALE><FLB_TRANS_MALE/><FLB_TRANS_FEMALE/><FLB_TRANS_CHD/><FLB_TRANS_INF/></PAX_OBJ></PAX_LIST></PAX_LIST_OBJ></P_PAX_LIST><L_RETURN_STR>OK</L_RETURN_STR></GET_PAX_LISTOutput></soap:Body></soap:Envelope>";
   
    public static final String FLIGHT_NUMBER_ADULTS = "FLB_ADULT";
	public static final String FLIGHT_NUMBER_CHILDRENS = "FLB_CHD";
    
}
