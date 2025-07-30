package com.apisentinel.controller;

import com.apisentinel.service.OpenAPISecurityAnalyzer;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiScanController {

    @PostMapping("/scan")
    public Map<String, Object> scanRawSpec(@RequestBody Map<String, String> body) {
        String spec = body.get("rawSpec");
        double score = OpenAPISecurityAnalyzer.calculateRiskScore(spec);

        Map<String, Object> response = new HashMap<>();
        response.put("riskScore", score);
        response.put("status", score > 60 ? "fail" : "pass");
        return response;
    }
}