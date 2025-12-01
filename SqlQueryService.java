package com.example.webhook_runner.service;

import org.springframework.stereotype.Service;

@Service
public class SqlQueryService {
    
    // ⚠️ IMPORTANT: Change this to YOUR registration number
    private static final String REG_NO = "22BCE9765";
    
    public String generateSqlQuery() {
        // Get last 2 digits of registration number
        String lastTwoDigits = REG_NO.substring(REG_NO.length() - 2);
        int lastDigits = Integer.parseInt(lastTwoDigits);
        
        // Check if even (Question 2) or odd (Question 1)
        if (lastDigits % 2 == 0) {
            // Question 2 - From your PDF
            return getQuestion2Query();
        } else {
            // Question 1 (Different question)
            return getQuestion1Query();
        }
    }
    
    private String getQuestion2Query() {
        // SQL Query for Question 2 (from your PDF)
        return """
            -- Question 2: Average age of employees earning > 70000 per department
            
            SELECT 
                d.DEPARTMENT_NAME,
                ROUND(AVG(
                    EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM e.DOB)
                ), 2) AS AVERAGE_AGE,
                STRING_AGG(
                    e.FIRST_NAME || ' ' || e.LAST_NAME, 
                    ', '
                ) AS EMPLOYEE_LIST
            FROM DEPARTMENT d
            JOIN EMPLOYEE e ON d.DEPARTMENT_ID = e.DEPARTMENT
            JOIN PAYMENTS p ON e.EMP_ID = p.EMP_ID
            WHERE p.AMOUNT > 70000
            GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME
            ORDER BY d.DEPARTMENT_ID DESC;
            """;
    }
    
    private String getQuestion1Query() {
        // Placeholder for Question 1
        return """
            -- Question 1: Placeholder query
            SELECT 'Implement Question 1 based on other PDF' AS result;
            """;
    }
}