package com.apisentinel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apisentinel.dto.APIUploadRequest;
import com.apisentinel.entity.API;
import com.apisentinel.entity.Rule;
import com.apisentinel.repository.APIRepository;

@Service
public class ApiService {

    @Autowired
    private APIRepository apiRepository;
    
    @Autowired
    private RuleService ruleService;

    public API processAndStore(APIUploadRequest request) {
        List<Rule> rules = ruleService.getAllRules();
        double riskScore = OpenAPISecurityAnalyzer.calculateRiskScore(request.getRawSpec(), rules);

        API api = API.builder()
                .name(request.getName())
                .version(request.getVersion())
                .specificationType(request.getSpecificationType())
                .source(request.getSource())
                .rawSpec(request.getRawSpec())
                .riskScore(riskScore)
                .build();

        return apiRepository.save(api);
    }

    public List<API> getAllApis() {
        return apiRepository.findAll();
    }
}
