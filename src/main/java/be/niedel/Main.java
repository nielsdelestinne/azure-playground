package be.niedel;

import static be.niedel.storageaccount.StorageAccount.createBlobContainerClient;
import static be.niedel.storageaccount.StorageAccount.createBlobServiceClient;
import static be.niedel.storageaccount.StorageAccount.uploadBlob;

public class Main {
    public static void main(String[] args) {

        try {
            var animalsContainerClient = createBlobContainerClient(createBlobServiceClient(), "animals");
            uploadBlob(animalsContainerClient, "cat.jpg");
            uploadBlob(animalsContainerClient, "dog.jpg");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
