package com.example.webhook_runner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebhookRequest {

    private String name;
    private String regNo;
    private String email;
    
    // Extra constructor removed; @AllArgsConstructor provides this constructor
}