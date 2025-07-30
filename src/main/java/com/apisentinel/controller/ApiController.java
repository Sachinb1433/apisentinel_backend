package com.apisentinel.controller;

import com.apisentinel.dto.APIUploadRequest;
import com.apisentinel.entity.API;
import com.apisentinel.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Enable CORS for frontend calls
public class ApiController {

    @Autowired
    private ApiService apiService;

    @PostMapping("/upload")
    public API uploadApi(@RequestBody APIUploadRequest request) {
        return apiService.processAndStore(request);
    }

    @GetMapping("/all")
    public List<API> getAll() {
        return apiService.getAllApis();
    }
}
