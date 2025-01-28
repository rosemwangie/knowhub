package com.bobo.knowhub.integration;

import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class IPFSIntegration {

    private static final String IPFS_BASE_URL = "http://localhost:5001/api/v0"; // Change to your IPFS node URL

    private final OkHttpClient httpClient = new OkHttpClient();

    /**
     * Uploads a file to IPFS and returns the file hash.
     *
     * @param file The file to upload.
     * @return The IPFS hash of the uploaded file.
     */
    public String uploadFile(File file) {
        if (file == null || !file.exists()) {
            throw new RuntimeException("Invalid file provided for upload");
        }

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(file, MediaType.parse("application/octet-stream")))
                .build();

        Request request = new Request.Builder()
                .url(IPFS_BASE_URL + "/add")
                .post(requestBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                // Extract the hash from the IPFS response
                String responseBody = response.body().string();
                return extractHashFromResponse(responseBody);
            } else {
                throw new RuntimeException("Failed to upload file to IPFS: " + response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error uploading file to IPFS", e);
        }
    }

    /**
     * Extracts the IPFS hash from the JSON response.
     *
     * @param responseBody The JSON response from IPFS.
     * @return The extracted IPFS hash.
     */
    private String extractHashFromResponse(String responseBody) {
        // Example response: {"Name":"file.txt","Hash":"QmHashHere","Size":"12345"}
        int hashStartIndex = responseBody.indexOf("\"Hash\":\"") + 8;
        int hashEndIndex = responseBody.indexOf("\"", hashStartIndex);
        return responseBody.substring(hashStartIndex, hashEndIndex);
    }
}
