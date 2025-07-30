package com.apisentinel.controller;

import com.apisentinel.entity.Rule;
import com.apisentinel.service.OpenAPISecurityAnalyzer;
import com.apisentinel.service.RuleService; // ✅ Import RuleService

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiScanController {

    @Autowired
    private RuleService ruleService; // ✅ Inject RuleService

    @PostMapping("/scan")
    public Map<String, Object> scanRawSpec(@RequestBody Map<String, String> body) {
        String spec = body.get("rawSpec");
        List<Rule> rules = ruleService.getAllRules(); // ✅ Works now

        double score = OpenAPISecurityAnalyzer.calculateRiskScore(spec, rules);

        Map<String, Object> response = new HashMap<>();
        response.put("riskScore", score);
        response.put("status", score > 60 ? "fail" : "pass");
        return response;
    }
}
