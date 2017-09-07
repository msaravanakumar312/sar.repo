import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.FileCopyUtils;


public class FileDownloadAws {
public static void main(String[] args) throws IOException {
	//https://s3.ap-south-1.amazonaws.com/demosamarth/1494743702269_iothubcode.jpg
	String imageUrl = "https://s3.ap-south-1.amazonaws.com/demosamarth/1494743702269_iothubcode.jpg";
	URL url = new URL(imageUrl);
    HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
    int responseCode = httpConn.getResponseCode();

    // always check HTTP response code first
    if (responseCode == HttpURLConnection.HTTP_OK) {
        String fileName = "";
        String disposition = httpConn.getHeaderField("Content-Disposition");
        String contentType = httpConn.getContentType();
        int contentLength = httpConn.getContentLength();

       /* if (disposition != null) {
            // extracts file name from header field
            int index = disposition.indexOf("filename=");
            if (index > 0) {
                fileName = disposition.substring(index + 10,
                        disposition.length() - 1);
            }
        } else {
            // extracts file name from URL
            fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                    fileURL.length());
        }*/
        fileName=FilenameUtils.getBaseName( imageUrl.substring(imageUrl.lastIndexOf('/') + 1));
        System.out.println("Content-Type = " + contentType);
        System.out.println("Content-Disposition = " + disposition);
        System.out.println("Content-Length = " + contentLength);
        System.out.println("fileName = " + fileName);

        // opens input stream from the HTTP connection
        InputStream inputStream = httpConn.getInputStream();
        File destfile=new File("C:\\AwsDemo\\"+fileName+FilenameUtils.getExtension(imageUrl));
        if(!destfile.exists())
        {
        	destfile.createNewFile();
        }
        //FileCopyUtils.copy(inputStream, new FileOutputStream(destfile));
        FileUtils.copyInputStreamToFile(inputStream, destfile);
        System.out.println("download done....");
}
}
}