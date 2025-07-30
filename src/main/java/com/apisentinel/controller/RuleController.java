package com.apisentinel.controller;

import com.apisentinel.entity.Rule;
import com.apisentinel.service.RuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
@CrossOrigin(origins = "http://localhost:3000") // ✅ Add this
public class RuleController {

    private final RuleService ruleService;

    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping
    public List<Rule> getAllRules() {
        return ruleService.getAllRules();
    }

    @PostMapping
    public Rule addRule(@RequestBody Rule rule) {
        return ruleService.save(rule);
    }

    @PutMapping("/{id}/toggle")
    public Rule toggleRule(@PathVariable Long id) {
        return ruleService.toggleRule(id);
    }
}
