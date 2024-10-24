package be.niedel;

import com.azure.storage.blob.BlobContainerClient;

import static be.niedel.storageaccount.StorageAccount.createBlobContainerClient;
import static be.niedel.storageaccount.StorageAccount.createBlobServiceClient;
import static be.niedel.storageaccount.StorageAccount.uploadBlob;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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
