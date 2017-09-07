import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;


public class FileUpload {
public static void main(String[] args) {
	 AWSCredentials credentials = new BasicAWSCredentials("AKIAJPQBGHMENHXSDRIQ", "3kYUs8/G9TLVN2ZLtFKSWE8jjoBw8n/INU14AqpI");
     //java.security.Security.setProperty("networkaddress.cache.ttl", S3_CACHE);
     //s3 = new AmazonS3Client(credentials);	s3.setEndpoint(END_POINT_URL);
	 AmazonS3 s3client = new AmazonS3Client(credentials);
    // InputStream stream = multipartFile.getInputStream();
	 InputStream stream=Thread.currentThread().getContextClassLoader().getResourceAsStream("iothubcode.jpg");
	 File srcFile=new File(Thread.currentThread().getContextClassLoader().getResource("iothubcode.jpg").getFile());
	 //String fileName=FilenameUtils.getBaseName(srcFile.getName());
	 String fileName=srcFile.getName();
     fileName = System.currentTimeMillis() + "_" + fileName;
     ObjectMetadata objectMetadata = new ObjectMetadata();
     //objectMetadata.setContentType("");
     PutObjectRequest putObjectRequest = new PutObjectRequest("demosamarth",  fileName, stream,objectMetadata);
     //skip if do not want to access the image directly from S3
     putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead); 
     //skip if do not want to access the image directly from S3
     s3client.putObject(putObjectRequest);
 	System.out.println("done.............");
}
}
