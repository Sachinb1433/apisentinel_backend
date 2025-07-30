package com.apisentinel.service;

import com.apisentinel.entity.Rule;
import com.apisentinel.repository.RuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class RuleService {

    private final RuleRepository repo;

    public RuleService(RuleRepository repo) {
        this.repo = repo;
    }

    @Autowired
    private RuleRepository ruleRepository;
    public List<Rule> getAllRules() {
        return repo.findAll();
    }

    public Rule save(Rule rule) {
        return repo.save(rule);
    }

    public Rule toggleRule(Long id) {
        Rule rule = repo.findById(id).orElseThrow();
        rule.setEnabled(!rule.isEnabled());
        return repo.save(rule);
    }
}
