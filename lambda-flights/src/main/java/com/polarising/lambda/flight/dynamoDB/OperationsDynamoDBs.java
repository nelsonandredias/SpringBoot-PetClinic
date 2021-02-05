package com.polarising.lambda.flight.dynamoDB;

import java.util.List;

import com.polarising.lambda.flight.models.FlightItems;

public interface OperationsDynamoDBs {
	
	public List<FlightItems> getAllItemsByPartitionKey(String partitionKeyName, String partitionKeyVal, String partitionAlias);
	
	public List<FlightItems> getAllItemsByPartitionKeyAndSortKey(String partitionKeyName, String partitionKeyVal, String partitionAlias,
			String sortKeyName, String sortKeyVal, String sortAlias);

	public List<FlightItems> getAllItemsByPartitionSortKeyRange(String partitionKeyName, String partitionKeyVal, String partitionAlias,
			String sortKeyName, String sortKeyValMin, String sortKeyValMax);
	
	public String upsertNewItem(Object inputObject);
	
	public String upsertBulkItems(List<Object> inputObjectList);
}
