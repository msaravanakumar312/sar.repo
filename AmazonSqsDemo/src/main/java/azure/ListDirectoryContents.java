package azure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlobDirectory;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

public class ListDirectoryContents {
	 //scandata container
    public static final String SCAN_DATA_CONTAINER_NAME = "scandata";
    public static final String SCAN_DATA_FILE_JSON = ".json";

    //ioc evaluation container
    public static final String IOC_EVALUATION_CONTAINER = "iocevaluation";
    public static final String TO_PROCESS_FOLDER = "toprocess";
    public static final String SCAN_LIST_DELIMITER = "\n";
    public static final String TRIGGER_FILE_EXTN = ".trigger";
	// Define the connection-string with your values
    public static final String AZURE_STORAGE_KEY = "EgvSAIpBpwh/4OBozJvWmgBajGgbZUAAxMVymWGCa7GqBYiKilXiQi9dyRBQT8aLnwQmiZVQGW7p9Qgq4L87lw==";
    public static final String ACCOUNT_NAME = "nwesparkstdstorage1";
    public static final String azureStorageAccountString = "DefaultEndpointsProtocol=https;"
            + "AccountName=" + ACCOUNT_NAME + ";"
            + "AccountKey=" + AZURE_STORAGE_KEY;
    public static final String BLOB_AZURE_DOMAIN = "blob.core.windows.net";
    //https://nwesparkstdstorage1.blob.core.windows.net/iocevaluation/toprocess/EcatClientExported-0ab755ee-8798-43ca-9861-2c7805387778-1478674048192.trigger
    public static final String AZURE_BLOB_TO_PROCESS_DIR = "wasbs://"
            + IOC_EVALUATION_CONTAINER
            + "@" + ACCOUNT_NAME + "." + BLOB_AZURE_DOMAIN
            + "/" + TO_PROCESS_FOLDER + "/";
    public static final String AZURE_BLOB_DATA_DIR = "wasbs://"
            + SCAN_DATA_CONTAINER_NAME
            + "@" + ACCOUNT_NAME + "." + BLOB_AZURE_DOMAIN + "/";
public static void main(String[] args) throws InvalidKeyException, URISyntaxException, StorageException, IOException {
	 // Retrieve storage account from connection-string.
    CloudStorageAccount storageAccount = CloudStorageAccount.parse(ListDirectoryContents.azureStorageAccountString);
    // Create the blob client.
    CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
 // Retrieve reference to a previously created container.
    //https://nwesparkstdstorage1.blob.core.windows.net/iocevaluation/toprocess/EcatClientExported-0ab755ee-8798-43ca-9861-2c7805387778-1478674048192.trigger
    CloudBlobContainer container = blobClient.getContainerReference("iocevaluation");
 for(ListBlobItem listBlobItem:container.listBlobs())
 {
	 System.out.println(listBlobItem.getUri().toString());
	 System.out.println(listBlobItem.getClass());
	 CloudBlobDirectory cloudBlobDirectory=container.getDirectoryReference("toprocess");
	 for(ListBlobItem cloBlobItem :cloudBlobDirectory.listBlobs())
	 {
		// System.out.println(cloBlobItem.getUri().toString());
	 }
 }
//wasb url java file download
// https://nwesparkstdstorage1.blob.core.windows.net/iocevaluation/toprocess/EcatClientExported-0ab755ee-8798-43ca-9861-2c7805387778-1478674048192.trigger
//wasb://YOURDefaultContainer@YOURStorageAccount.blob.core.windows.net/SomeDirectory/ASubDirectory/AFile.txt
 /*public static final String AZURE_BLOB_TO_PROCESS_DIR = "wasbs://"
 + IOC_EVALUATION_CONTAINER
 + "@" + ACCOUNT_NAME + "." + BLOB_AZURE_DOMAIN
 + "/" + TO_PROCESS_FOLDER + "/";*/
 String filetoBeDownloaded="wasb://iocevaluation@nwesparkstdstorage1.blob.core.windows.net/toprocess/EcatClientExported-0ab755ee-8798-43ca-9861-2c7805387778-1478674048192.trigger";
 URL url=new URL(filetoBeDownloaded);
 URLConnection conn = url.openConnection();
 InputStream inputStream=conn.getInputStream();
 System.out.println(inputStream.toString());
}
}
