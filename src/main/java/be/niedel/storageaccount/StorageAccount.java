package be.niedel.storageaccount;

import be.niedel.Secrets;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public class StorageAccount {

    private static final Logger logger = LoggerFactory.getLogger(StorageAccount.class);

    public static BlobServiceClient createBlobServiceClient() {
        return new BlobServiceClientBuilder()
                .endpoint("https://%s.storage.windows.net".formatted(Secrets.STORAGE_ACCOUNT_NAME))
                .connectionString(Secrets.CONNECTION_STRING)
                .buildClient();
    }

    public static BlobContainerClient createBlobContainerClient(BlobServiceClient blobServiceClient, String containerName) {
        return blobServiceClient.createBlobContainerIfNotExists(containerName);
    }

    public static void uploadBlob(BlobContainerClient blobContainerClient, String fileName) throws Exception {
        var blobClient = blobContainerClient.getBlobClient(fileName);
        var absolutePath = getAbsolutePath(fileName);

        logger.info("Start uploading {}", absolutePath);
        blobClient.uploadFromFile(absolutePath, true);
        logger.info("Completed uploading {}", absolutePath);
    }

    public static String getAbsolutePath(String resourcePath) {
        var resource = StorageAccount.class.getClassLoader().getResource(resourcePath);

        if (resource == null) {
            throw new RuntimeException("Resource not found: " + resourcePath);
        }

        try {
            return Paths.get(resource.toURI()).toAbsolutePath().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
