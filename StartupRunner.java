package com.example.webhook_runner.runner;

import com.example.webhook_runner.model.ApiResponse;
import com.example.webhook_runner.model.WebhookResponse;
import com.example.webhook_runner.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(StartupRunner.class);
    private final ApiService apiService;
    
    public StartupRunner(ApiService apiService) {
        this.apiService = apiService;
    }
    
    @Override
    public void run(String... args) throws Exception {
        logger.info("=========================================");
        logger.info("🚀 Starting Webhook Runner Application");
        logger.info("=========================================");
        
        try {
            // Step 1: Call Generate Webhook API
            logger.info("📡 Step 1: Generating webhook...");
            WebhookResponse webhookResponse = apiService.callGenerateWebhookApi();
            
            if (webhookResponse.getAccessToken() == null || webhookResponse.getAccessToken().isEmpty()) {
                throw new RuntimeException("Access token not received from API");
            }
            
            logger.info("✅ Webhook generated successfully!");
            logger.info("📝 Webhook URL: {}", webhookResponse.getWebhookUrl());
            logger.info("🔑 Access Token Received: Yes");
            
            // Step 2: Call Test Webhook API with SQL solution
            logger.info("📡 Step 2: Submitting SQL solution...");
            ApiResponse apiResponse = apiService.callTestWebhookApi(
                webhookResponse.getWebhookUrl(),
                webhookResponse.getAccessToken()
            );
            
            logger.info("✅ Solution submitted successfully!");
            logger.info("📊 Response Message: {}", apiResponse.getMessage());
            logger.info("🎯 Success Status: {}", apiResponse.isSuccess());
            
        } catch (Exception e) {
            logger.error("❌ Error during execution: {}", e.getMessage());
            logger.error("Stack Trace:", e);
            throw e;
        }
        
        logger.info("=========================================");
        logger.info("🏁 Application execution completed");
        logger.info("=========================================");
        
        // Optional: Exit the application after completion
        // System.exit(0);
    }
}