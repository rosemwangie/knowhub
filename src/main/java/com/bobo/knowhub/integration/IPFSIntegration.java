package com.bobo.knowhub.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class IPFSIntegration {

    @Value("${ipfs.api.url:http://127.0.0.1:5001}")
    private String ipfsApiUrl;

    @Value("${ipfs.api.add-endpoint:/api/v0/add}")
    private String addEndpoint;

    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public IPFSIntegration() {
        this.httpClient = new OkHttpClient.Builder().build();
        this.objectMapper = new ObjectMapper();
    }



    public String uploadContent(String title, String content) {
        String fullContent = String.format("Title: %s\n\nContent: %s", title, content);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "article.txt",
                        RequestBody.create(fullContent.getBytes(StandardCharsets.UTF_8),
                                MediaType.parse("text/plain")))
                .build();

        Request request = new Request.Builder()
                .url(ipfsApiUrl + addEndpoint)
                .post(requestBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                throw new RuntimeException("IPFS upload failed with status: " + response.code());
            }

            String responseBody = response.body().string();
            JsonNode jsonResponse = objectMapper.readTree(responseBody);

            if (!jsonResponse.has("Hash")) {
                throw new RuntimeException("Invalid IPFS response: Hash not found");
            }

            return jsonResponse.get("Hash").asText();
        } catch (IOException e) {
            throw new RuntimeException("IPFS upload failed: " + e.getMessage(), e);
        }
    }

    @Value("${ipfs.gateway.url:http://localhost:8888}")
    private String ipfsGatewayUrl;

    public String getIpfsGatewayUrl(String hash) {
        return ipfsGatewayUrl + "/ipfs/" + hash;
    }
}