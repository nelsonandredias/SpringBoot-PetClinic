package com.polarising.lamda;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;


public class WelcomeLambda implements RequestHandler<Temperature, Map<Integer,String>>{

	
	// enable pretty print JSON output
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	Map<Integer,String> mapResponse = new HashMap<Integer,String>(); 
	
	Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
	
	RedisOperations redisOperations;
	
	String configEndpoint = "my-redis-cluster.ovkhiy.0001.euc1.cache.amazonaws.com";
    Integer clusterPort = 6379;
	
    public static int PRETTY_PRINT_INDENT_FACTOR = 4;
    public static String TEST_XML_STRING =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>  \r\n"
            + "<emails>  \r\n"
            + "<email>  \r\n"
            + "  <to>Vimal</to>  \r\n"
            + "  <from>Sonoo</from>  \r\n"
            + "  <heading>Hello</heading>  \r\n"
            + "  <body>Hello brother, how are you!</body>  \r\n"
            + "</email>  \r\n"
            + "<email>  \r\n"
            + "  <to>Peter</to>  \r\n"
            + "  <from>Jack</from>  \r\n"
            + "  <heading>Birth day wish</heading>  \r\n"
            + "  <body>Happy birth day Tom!</body>  \r\n"
            + "</email>  \r\n"
            + "<email>  \r\n"
            + "  <to>James</to>  \r\n"
            + "  <from>Jaclin</from>  \r\n"
            + "  <heading>Morning walk</heading>  \r\n"
            + "  <body>Please start morning walk to stay fit!</body>  \r\n"
            + "</email>  \r\n"
            + "<email>  \r\n"
            + "  <to>Kartik</to>  \r\n"
            + "  <from>Kumar</from>  \r\n"
            + "  <heading>Health Tips</heading>  \r\n"
            + "  <body>Smoking is injurious to health!</body>  \r\n"
            + "</email>  \r\n"
            + "</emails>  ";
	
	@Override
	public Map<Integer,String> handleRequest(Temperature request, Context context){
		
		
		
		//Jedis Cluster will attempt to discover cluster nodes automatically
		//jedisClusterNodes.add(new HostAndPort("configEndpoint", clusterPort));
		//JedisCluster jedisCluster = new JedisCluster(new HostAndPort(configEndpoint, clusterPort));
		Jedis jediNonCluster = new Jedis(new HostAndPort(configEndpoint, clusterPort));
		
		LambdaLogger logger = context.getLogger();
		
		System.out.println("Welcome to lambda function");
		
		// log execution details
	    logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
	    logger.log("CONTEXT: " + gson.toJson(context));
	    
		
		 // process event
	    logger.log("EVENT: " + gson.toJson(request));
	    logger.log("EVENT TYPE: " + request.getClass().toString());
		
	    Temperature temperature = gson.fromJson(gson.toJson(request), Temperature.class);
        System.out.println("City is: "+ temperature.getCity() +" Temperature is: "+ temperature.getTemperature());

    
        String message = "Hey there! " + temperature.getCity() + " marks " + temperature.getTemperature();
       
        // generate random index
        Random r = new Random();
        int randomInt = r.nextInt(100) + 1;
        
        // save key-value to cache
        //redisOperations.createKeyValue(jediNonCluster, "randomInt", message);
        jediNonCluster.set("randomInt",String.valueOf(randomInt));
        jediNonCluster.set("message", message);
        
        // get data from cache
        String randomInt_obtained = jediNonCluster.get("randomInt");
        String message_obtained = jediNonCluster.get("message");
        System.out.println("randomInt_obtained : " + randomInt_obtained);
        System.out.println("message_obtained : " + message_obtained);
        
        // save field-value pairs to cache 
        Map<String, String> flightDate_Object = new HashMap<String, String>();
        
        flightDate_Object.put("AF_ID", "AF_ID_value");
        flightDate_Object.put("AF_DEP", "AF_DEP_value");
        flightDate_Object.put("AF_DES", "AF_DES_value");
        flightDate_Object.put("AF_AL","AF_AL_value");
        //redisOperations.hmset(jediNonCluster, "AF_ID_00001", flightDate_Object);
        
        
        try {
            JSONObject xmlJSONObj = XML.toJSONObject(TEST_XML_STRING);
            String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
            jediNonCluster.set("xmlMessage", jsonPrettyPrintString);
            
            System.out.println(jsonPrettyPrintString);
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
        
        if (message.equals(message_obtained) ) {
        	System.out.format("Success: Fetched value %s from rediscache", message_obtained);
        	
        	mapResponse.put(randomInt, message);
        	
        } else {
        	System.out.format("Insuccess: Value is not the same as we put. Expected %s and got %s", message, message_obtained);
        }
       
        
		return mapResponse;
	}

}
