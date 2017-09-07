package com.emc.ecat.AmazonSqsDemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

public class SendMessagesqsDemo {
public static void main(String[] args) throws IOException {
	Properties properties=new Properties();
	InputStream inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream("aws.credentials");
	properties.load(inputStream);
	System.out.println(properties);
	BasicAWSCredentials credentials=new BasicAWSCredentials(properties.getProperty("accessKey"),properties.getProperty("secretKey") );
	 AmazonSQSClient sqs = new AmazonSQSClient(credentials);
	 //https://us-west-2.console.aws.amazon.com/ec2/v2/home?region=us-west-2#
    // sqs.setEndpoint("https://sqs.us-west-2.amazonaws.com");
     String myQueueUrl="https://sqs.us-west-2.amazonaws.com/609276614743/testqueue";
	// Send a message
	System.out.println("Sending a message to testqueue.fifo.\n");
	SendMessageRequest sendMessageRequest = new SendMessageRequest(myQueueUrl, "This is my message text.");
	//SendMessageRequest jsonmessagerequest=new SendMessageRequest(myQueueUrl, messageBody);
	// You must provide a non-empty MessageGroupId when sending messages to a FIFO queue
	//sendMessageRequest.setMessageGroupId("messageGroup1");
	// Uncomment the following to provide the MessageDeduplicationId
	//sendMessageRequest.setMessageDeduplicationId("1");
	SendMessageResult sendMessageResult = sqs.sendMessage(sendMessageRequest);
	String sequenceNumber = sendMessageResult.getSequenceNumber();
	String messageId = sendMessageResult.getMessageId();
	System.out.println("SendMessage succeed with messageId " + messageId + ", sequence number " + sequenceNumber + "\n");
}
}
