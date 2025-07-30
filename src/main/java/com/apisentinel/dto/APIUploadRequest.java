package com.apisentinel.dto;

import lombok.Data;

@Data
public class APIUploadRequest {
    private String name;
    private String version;
    private String specificationType; // OpenAPI or Postman
    private String source;            // Internal or External
    private String rawSpec;           // Raw JSON/YAML spec string
    
//    public String getName() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	public String getVersion() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	public String getSpecificationType() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	public String getSource() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	public String getRawSpec() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	public String getversion() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	public String getspecificationType() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	public String getsource() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	public String getrawSpec() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
