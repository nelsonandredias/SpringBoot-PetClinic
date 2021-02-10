package com.polarising.lambda.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.Datapoint;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsRequest;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsResult;
import com.amazonaws.services.cloudwatch.model.ListMetricsRequest;
import com.amazonaws.services.cloudwatch.model.ListMetricsResult;
import com.amazonaws.services.cloudwatch.model.Metric;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import com.amazonaws.services.cloudwatch.model.PutMetricDataResult;
import com.amazonaws.services.cloudwatch.model.StandardUnit;
import com.amazonaws.services.cloudwatch.model.Statistic;

public class CloudWatchManipulation {


	private Regions REGION = Regions.EU_CENTRAL_1;

	private AmazonCloudWatch amazonCloudWatch;

	public void initCloudwatchClient() {

		System.out.println("Setting new Cloudwatch Client...");
		amazonCloudWatch = AmazonCloudWatchClientBuilder.defaultClient();
		
	}

	// insert custom metric into cloudwatch
	public void publishCustomMetricData(double count, String af_modified) throws ParseException {
		
		System.out.println("start publishing custom metric number: " + count);
		
		Date latestDate = convertStringToLocalDateTime(af_modified);
		
		// A dimension is a name/value pair that is part of the identity of a metric
		Dimension dimension = new Dimension()
				.withName("AF_MODIFIED")
				.withValue("FLIGHT_DATE_LIST");

		MetricDatum datum = new MetricDatum()
				.withMetricName("WEB_SERVICE_CALLS_UPDATE")
				.withTimestamp(latestDate)
				.withDimensions(dimension)
				.withUnit(StandardUnit.None)
				.withValue(count);

		PutMetricDataRequest request = new PutMetricDataRequest()
				.withNamespace("TUI/FLIGHT_LIST_DATE")
				.withMetricData(datum);

		PutMetricDataResult response = amazonCloudWatch.putMetricData(request);
		
		System.out.println("PutMetricDataResult: " + response.toString());
		
		System.out.println("completed publish custom metric " + af_modified);
	}
	
	// retrieve statistics for the specified metric
	public void getCustomMetricTimestamp() {
		
		System.out.println("start getting custom metric timestamp ");
		
		Calendar calendar = Calendar.getInstance();
		
		//get current time
		Date now = calendar.getTime();
		
		//reset hours
		calendar.add(Calendar.MINUTE, -15);
		
		//get 15minutes back time
		Date quarterBack = calendar.getTime();
		
		// A dimension is a name/value pair that is part of the identity of a metric
		Dimension dimension = new Dimension().withName("AF_MODIFIED").withValue("FLIGHT_DATE_LIST");
		
		GetMetricStatisticsRequest request = new GetMetricStatisticsRequest()
				.withNamespace("TUI/FLIGHT_LIST_DATE")
				.withStartTime(quarterBack)
				.withEndTime(now)
				.withPeriod(60*15)
				.withMetricName("WEB_SERVICE_CALLS_UPDATE")
				.withStatistics(Statistic.SampleCount)
				.withDimensions(dimension);
		
		GetMetricStatisticsResult getMetricStatisticsResult = amazonCloudWatch.getMetricStatistics(request);
		
		for (Datapoint dataPoint : getMetricStatisticsResult.getDatapoints()) {
			System.out.println("dataPoint: " + dataPoint);
			System.out.println("dataPoint Timestamp: " + dataPoint.getTimestamp());
		}
		
		System.out.println("completed getting custom metric timestamp ");
		
	}

	public void listMets() {

		boolean done = false;
		
		ListMetricsRequest request = new ListMetricsRequest().withMetricName("LAST_ACCESS").withNamespace("TUI/FLIGHT_LIST_DATE");
		
		while(!done) {
            ListMetricsResult response = amazonCloudWatch.listMetrics(request);
 
            for(Metric metric : response.getMetrics()) {
                System.out.println("Retrieved metric: " + metric.getDimensions());
                    
            }
 
            request.setNextToken(response.getNextToken());
 
            if(response.getNextToken() == null) {
                done = true;
            }
        }
	}

	public Date convertStringToLocalDateTime(String af_modified) throws ParseException {

		Date date = new Date();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		date = df.parse(af_modified);
		System.out.println("Date: " + date);

		return date;
	}

}
