package com.emc.ecat.AmazonSqsDemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

public class ReceiveMessagesqsDemo {
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
	// Receive messages
	System.out.println("Receiving messages from MyFifoQueue.fifo.\n");
	ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);
	// Uncomment the following to provide the ReceiveRequestDeduplicationId
	//receiveMessageRequest.setReceiveRequestAttemptId("1");
	List<com.amazonaws.services.sqs.model.Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
	for (com.amazonaws.services.sqs.model.Message message : messages) {
	    System.out.println("  Message");
	    System.out.println("    MessageId:     " + message.getMessageId());
	    System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
	    System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
	    System.out.println("    Body:          " + message.getBody());
	    for (Entry<String, String> entry : ((com.amazonaws.services.sqs.model.Message) message).getAttributes().entrySet()) {
	        System.out.println("  Attribute");
	        System.out.println("    Name:  " + entry.getKey());	
	        System.out.println("    Value: " + entry.getValue());
	    }
	}
	System.out.println();
}
}
