package com.example.webhook_runner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolutionRequest {

    private String finalQuery;
    
    // Extra constructor removed to avoid duplication with Lombok's @AllArgsConstructor
}