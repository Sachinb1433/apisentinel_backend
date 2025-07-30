package com.apisentinel.service;

import com.apisentinel.dto.APIUploadRequest;
import com.apisentinel.entity.API;
import com.apisentinel.repository.APIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    @Autowired
    private APIRepository apiRepository;

    public API processAndStore(APIUploadRequest request) {
        // TODO: In future, parse spec & calculate risk score
//        double riskScore = 50.0; // Dummy static score for now
        double riskScore = OpenAPISecurityAnalyzer.calculateRiskScore(request.getRawSpec());


        API api = API.builder()
                .name((String) request.getName())
                .version((String) request.getVersion())
                .specificationType((String) request.getSpecificationType())
                .source((String) request.getSource())
                .rawSpec((String) request.getRawSpec())
                .riskScore(riskScore)
                .build();

        return apiRepository.save(api);
    }

    public java.util.List<API> getAllApis() {
        return apiRepository.findAll();
    }
}
