package com.emc.ecat.AmazonSqsDemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.FilenameUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	String imageUrl = "http://www.avajava.com/images/avajavalogo.jpg";
    	URL url = new URL(imageUrl);
    	 HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
         int responseCode = httpConn.getResponseCode();
  
         // always check HTTP response code first
         if (responseCode == HttpURLConnection.HTTP_OK) {
             String fileName = "";
             String disposition = httpConn.getHeaderField("Content-Disposition");
             String contentType = httpConn.getContentType();
             int contentLength = httpConn.getContentLength();
  
             /*if (disposition != null) {
                 // extracts file name from header field
                 int index = disposition.indexOf("filename=");
                 if (index > 0) {
                     fileName = disposition.substring(index + 10,
                             disposition.length() - 1);
                 }
             } else {
                 // extracts file name from URL
                 fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1,
                		 imageUrl.length());
             }*/
             System.out.println(FilenameUtils.getBaseName(imageUrl));
             System.out.println(FilenameUtils.getExtension(imageUrl));
             System.out.println(FilenameUtils.getName(imageUrl));
             System.out.println("Content-Type = " + contentType);
             System.out.println("Content-Disposition = " + disposition);
             System.out.println("Content-Length = " + contentLength);
             System.out.println("fileName = " + FilenameUtils.getName(imageUrl));
  
             // opens input stream from the HTTP connection
             InputStream inputStream = httpConn.getInputStream();
    }
}
}