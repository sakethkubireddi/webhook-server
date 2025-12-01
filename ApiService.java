package com.example.webhook_runner.service;

import com.example.webhook_runner.model.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {
    
    private final RestTemplate restTemplate;
    private final SqlQueryService sqlQueryService;
    
    public ApiService(SqlQueryService sqlQueryService) {
        this.restTemplate = new RestTemplate();
        this.sqlQueryService = sqlQueryService;
    }
    
    // Step 1: Call API to get webhook URL and token
    public WebhookResponse callGenerateWebhookApi() {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
        
        // ⚠️ IMPORTANT: Change these to YOUR details
        WebhookRequest requestBody = new WebhookRequest(
            "SAKETH",          // ← Your actual name
            "22BCE9765",          // ← Your actual registration number
            "sakethkubireddi@gmail.com"   // ← Your actual email
        );
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<WebhookRequest> request = new HttpEntity<>(requestBody, headers);
        
        ResponseEntity<WebhookResponse> response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            request,
            WebhookResponse.class
        );
        
        return response.getBody();
    }
    
    // Step 2: Call API to submit SQL solution
    public ApiResponse callTestWebhookApi(String webhookUrl, String accessToken) {
        // Get SQL query from service
        String sqlQuery = sqlQueryService.generateSqlQuery();
        
        // Create request body with SQL query
        SolutionRequest requestBody = new SolutionRequest(sqlQuery);
        
        // Prepare headers with JWT token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);
        
        HttpEntity<SolutionRequest> request = new HttpEntity<>(requestBody, headers);
        
        // Make API call
        ResponseEntity<ApiResponse> response = restTemplate.exchange(
            webhookUrl,
            HttpMethod.POST,
            request,
            ApiResponse.class
        );
        
        return response.getBody();
    }
}