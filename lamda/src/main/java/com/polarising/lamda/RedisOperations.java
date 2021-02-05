package com.polarising.lamda;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

public class RedisOperations {

	
	public void createKeyValue (Jedis jediNonCluster, String key, String value) {
		System.out.println("entered createKeyValue");
	    String serializedValue = value;
	    jediNonCluster.set(key, serializedValue);
	}

	String fetchKeyValue(Jedis jediNonCluster, String key) {
	    String serializedValue = jediNonCluster.get(key);
	    return serializedValue;
	}
	
	//create Redis Hashes
	/* > hmset user:1000 username antirez birthyear 1977 verified 1
		OK
	*/
	public String hmset(Jedis jedis, String key, Map<String, String> hash) {
	    String res = null;
	    try {
	        res = jedis.hmset(key, hash);
	    } catch (Exception e) {
	    	System.out.println(e);
	    } finally {
	        jedis.close();
	    }
	    return res;
	}
	
	//create Redis Sorted sets
	/*
	 * > zadd hackers 1940 "Alan Kay"
	  	(integer) 1
	   > zadd hackers 1957 "Sophie Wilson"
		(integer) 1
	   > zadd hackers 1953 "Richard Stallman"
		(integer) 1
	 /*
		    Long res = null;
		    try {
		        res = jedis.zadd(key, null, null);
		    } catch (Exception e) {
		    	System.out.println(e);
		    } finally {
		        jedis.close();
		    }
		    return res;
		}*/

}
