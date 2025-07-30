package com.apisentinel.service;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.parser.OpenAPIV3Parser;

import java.util.Map;

public class OpenAPISecurityAnalyzer {

    public static double calculateRiskScore(String rawSpec) {
        try {
            OpenAPI openAPI = new OpenAPIV3Parser().readContents(rawSpec, null, null).getOpenAPI();

            if (openAPI == null || openAPI.getPaths() == null) {
                return 100.0; // Maximum risk
            }

            double score = 100.0;

            // Heuristic 1: Check for authentication
            if (openAPI.getSecurity() == null || openAPI.getSecurity().isEmpty()) {
                score -= 20;
            }

            // Heuristic 2: Check if CORS headers are mentioned (not always in spec)
            // Just simulate deduction
            if (!rawSpec.contains("Access-Control-Allow-Origin")) {
                score -= 10;
            }

            // Heuristic 3: Check for rate limits
            if (!rawSpec.toLowerCase().contains("x-ratelimit-limit")) {
                score -= 15;
            }

            // Heuristic 4: Check for sensitive endpoints
            for (Map.Entry<String, PathItem> entry : openAPI.getPaths().entrySet()) {
                String path = entry.getKey().toLowerCase();
                if (path.contains("login") || path.contains("token") || path.contains("auth")) {
                    score -= 10;
                    break;
                }
            }

            return Math.max(score, 0.0);
        } catch (Exception e) {
            e.printStackTrace();
            return 100.0;
        }
    }
}
