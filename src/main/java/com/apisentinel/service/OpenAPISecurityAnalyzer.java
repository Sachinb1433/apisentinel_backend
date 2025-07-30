package com.apisentinel.service;

import java.util.List;

import com.apisentinel.entity.Rule;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;

public class OpenAPISecurityAnalyzer {

	public static double calculateRiskScore(String rawSpec, List<Rule> rules) {
	    OpenAPI openAPI = new OpenAPIV3Parser().readContents(rawSpec, null, null).getOpenAPI();
	    double score = 100.0;

	    for (Rule rule : rules) {
	        if (!rule.isEnabled()) continue;

	        switch (rule.getKey()) {
	            case "require-auth":
	                if (openAPI.getSecurity() == null || openAPI.getSecurity().isEmpty()) {
	                    score -= rule.getPenalty();
	                }
	                break;
	            case "require-cors":
	                if (!rawSpec.contains("Access-Control-Allow-Origin")) {
	                    score -= rule.getPenalty();
	                }
	                break;
	            case "require-rate-limit":
	                if (!rawSpec.toLowerCase().contains("x-ratelimit-limit")) {
	                    score -= rule.getPenalty();
	                }
	                break;
	            case "block-sensitive-endpoints":
	                for (String path : openAPI.getPaths().keySet()) {
	                    if (path.contains("login") || path.contains("token")) {
	                        score -= rule.getPenalty();
	                        break;
	                    }
	                }
	                break;
	        }
	    }

	    return Math.max(score, 0.0);
	}

	public static double calculateRiskScore(String rawSpec) {
		// TODO Auto-generated method stub
		return 0;
	}

}
