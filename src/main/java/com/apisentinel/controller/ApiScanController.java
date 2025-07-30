package com.apisentinel.controller;

import com.apisentinel.service.OpenAPISecurityAnalyzer;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiScanController {

	@PostMapping(value = "/scan-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> scanRawFile(@RequestParam("file") MultipartFile file) throws IOException {
	    String rawSpec = new String(file.getBytes(), StandardCharsets.UTF_8);
	    double score = OpenAPISecurityAnalyzer.calculateRiskScore(rawSpec);

	    Map<String, Object> result = new HashMap<>();
	    result.put("riskScore", score);
	    result.put("status", score > 60 ? "fail" : "pass");
//	    return result;
	    return ResponseEntity.ok("File uploaded");

	}

}
