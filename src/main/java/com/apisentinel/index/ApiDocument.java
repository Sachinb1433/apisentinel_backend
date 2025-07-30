package com.apisentinel.index;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "apis")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiDocument {

    @Id
    private String id;
    private String name;
    private String version;
    private String specificationType;
    private String source;
    private String rawSpec;
    private double riskScore;
}
