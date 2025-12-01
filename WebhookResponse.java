package com.example.webhook_runner.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebhookResponse {
    @JsonProperty("webhook")
    private String webhookUrl;
    
    @JsonProperty("accessToken")
    private String accessToken;
    
    // Extra constructor
    // Removed duplicate constructor as @AllArgsConstructor generates it
}