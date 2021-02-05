package com.polarising.lambda.flight.dynamoDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polarising.lambda.flight.models.FlightItems;

public class OperationsDynamoDBImpl implements OperationsDynamoDBs {

	private AmazonDynamoDB dynamoDbClient;
	private DynamoDB dynamoDb;
	private Regions REGION = Regions.EU_CENTRAL_1;
	private String DYNAMODB_TABLE_NAME = "Flights";
	private String DYNAMODB_INDEX_FLIGHTDATE = "FlightDateIndex";

	public OperationsDynamoDBImpl() {
		super();
	}

	public void initDynamoDbClient() {

		System.out.println("Setting new DynamoDB Client...");
		dynamoDbClient = AmazonDynamoDBClientBuilder.standard().withRegion(REGION).build();
		dynamoDb = new DynamoDB(dynamoDbClient);

	}

	public void destroyDynamoDbClient() {

		System.out.println("Closing DynamoDB Client...");
		dynamoDb.shutdown();

	}

	// get all items for a specified date
	public List<FlightItems> getAllItemsByPartitionKey(String partitionKeyName, String partitionKeyVal,
			String partitionAlias) {
		
		DynamoDBMapper dynamoDbMapper = new DynamoDBMapper(dynamoDbClient);

		List<FlightItems> flightListResponse = null;
		
		Map<String, String> attrNames  = new HashMap<String, String>();
		attrNames.put(partitionAlias, partitionKeyName);
		
		Map<String, AttributeValue> attrValues = new HashMap<String, AttributeValue>();
		attrValues.put(":" + partitionKeyName, new AttributeValue().withS(partitionKeyVal));
		
		DynamoDBQueryExpression<FlightItems> queryExpression = new DynamoDBQueryExpression<FlightItems>()
	            .withKeyConditionExpression(partitionAlias + " = :" + partitionKeyName)
	            .withExpressionAttributeValues(attrValues)
	            .withExpressionAttributeNames(attrNames);

		try {

			flightListResponse = dynamoDbMapper.query(FlightItems.class, queryExpression);
			
			return flightListResponse;
			
		} catch (Exception e) {
			System.err.println(e.getMessage());

		}
		return null;

	}

	@Override
	public List<FlightItems> getAllItemsByPartitionKeyAndSortKey(String partitionKeyName, String partitionKeyVal,
			String partitionAlias, String sortKeyName, String sortKeyVal, String sortAlias) {

		
		DynamoDBMapper dynamoDbMapper = new DynamoDBMapper(dynamoDbClient);

		List<FlightItems> flightListResponse = null;
		
		Map<String, String> attrNames  = new HashMap<String, String>();
		attrNames.put(partitionAlias, partitionKeyName);
		attrNames.put(sortAlias, sortKeyName);
		
		Map<String, AttributeValue> attrValues = new HashMap<String, AttributeValue>();
		attrValues.put(":" + partitionKeyName, new AttributeValue().withS(partitionKeyVal));
		attrValues.put(":" + sortKeyName, new AttributeValue().withS(sortKeyVal));
		
		DynamoDBQueryExpression<FlightItems> queryExpression = new DynamoDBQueryExpression<FlightItems>()
	            .withKeyConditionExpression(partitionAlias + " = :" + partitionKeyName + " and " + sortAlias + " = :" + sortKeyName)
	            .withExpressionAttributeValues(attrValues)
	            .withExpressionAttributeNames(attrNames);
		
		
		try {

			flightListResponse = dynamoDbMapper.query(FlightItems.class, queryExpression);
			
			return flightListResponse;

		} catch (Exception e) {
			System.err.println(e.getMessage());

		}

		return null;
	}

	@Override
	public String upsertNewItem(Object inputObject) {

		DynamoDBMapper dynamoDbMapper = new DynamoDBMapper(dynamoDbClient);

		try {

			dynamoDbMapper.save(inputObject);
			return "SUCCESS";

		} catch (Exception e) {

			System.err.println(e.getMessage());
			return "FAILURE";

		}

	}

	@Override
	public String upsertBulkItems(List<Object> inputObjectList) {
		
		DynamoDBMapper dynamoDbMapper = new DynamoDBMapper(dynamoDbClient);

		try {

			dynamoDbMapper.batchSave(inputObjectList);
			return "SUCCESS";

		} catch (Exception e) {

			System.err.println(e.getMessage());
			return "FAILURE";

		}
	}

	@Override
	public List<FlightItems> getAllItemsByPartitionSortKeyRange(String partitionKeyName, String partitionKeyVal, String partitionAlias,
			String sortKeyName, String sortKeyValMin, String sortKeyValMax) {
		
		DynamoDBMapper dynamoDbMapper = new DynamoDBMapper(dynamoDbClient);

		List<FlightItems> flightListResponse = null;
		
		Map<String, String> attrNames  = new HashMap<String, String>();
		attrNames.put(partitionAlias, partitionKeyName);

		
		Map<String, AttributeValue> attrValues = new HashMap<String, AttributeValue>();
		attrValues.put(":" + partitionKeyName, new AttributeValue().withS(partitionKeyVal));
		attrValues.put(":" + sortKeyName + "Min", new AttributeValue().withS(sortKeyValMin));
		attrValues.put(":" + sortKeyName + "Max", new AttributeValue().withS(sortKeyValMax)); 
		
		DynamoDBQueryExpression<FlightItems> queryExpression = new DynamoDBQueryExpression<FlightItems>()
				.withIndexName(DYNAMODB_INDEX_FLIGHTDATE)
	            .withKeyConditionExpression(partitionAlias + " = :" + partitionKeyName  + " and " + sortKeyName + " between :" + sortKeyName + "Min and :" + sortKeyName + "Max")
	            .withExpressionAttributeValues(attrValues)
	            .withExpressionAttributeNames(attrNames)
	            .withConsistentRead(false);
		
		
		try {
			
			flightListResponse = dynamoDbMapper.query(FlightItems.class, queryExpression);
			
			return flightListResponse;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			
		}
		return null;
	}



	
	

	

}
