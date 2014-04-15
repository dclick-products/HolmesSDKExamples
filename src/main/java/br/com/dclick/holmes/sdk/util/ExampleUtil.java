package br.com.dclick.holmes.sdk.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.springframework.util.MultiValueMap;

import br.com.dclick.holmes.sdk.cases.UploadCase;
import br.com.dclick.holmes.sdk.classification.ClassifyDocumentCall;
import br.com.dclick.holmes.sdk.document.UploadDocumentCall;
import br.com.dclick.holmes.sdk.dto.SearchResultDocumentDTO;
import br.com.dclick.holmes.sdk.security.OauthCredentials;

public class ExampleUtil {
	
	public static final String CHARACTERISTIC_NAME = "CHARACTERISTIC_NAME";
	public static final String CHARACTERISTIC_LANGUAGE = "CHARACTERISTIC_LANGUAGE";
	public static final String NATURE_DEVELOPMENT = "NATURE_DEVELOPMENT";
	
	public static final String BASE_URL = "BASE_URL";
	public static final String CLIENT_ID = "CLIENT_ID";
	public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
	public static final String COMPANY_NAME = "COMPANY_NAME";
	public static final String PATH = "PATH";

	public static Properties getProperties() {

		final Properties properties = new Properties();
		
		try {
			properties.load(UploadCase.class.getResourceAsStream("/examples.properties"));
	
		} catch (final IOException e) {
			e.printStackTrace();
		}
		
		return properties;
	}
	
	public static OauthCredentials getCredentials(){
		
		final Properties properties = ExampleUtil.getProperties();
		final String clientId    = properties.getProperty(ExampleUtil.CLIENT_ID);
		final String accessToken = properties.getProperty(ExampleUtil.ACCESS_TOKEN);
		final String companyName = properties.getProperty(ExampleUtil.COMPANY_NAME);
		
		return new OauthCredentials(clientId, accessToken, companyName);
	}
	
	public static String getBaseUrl(){
		return getProperties().getProperty(ExampleUtil.BASE_URL);
	}

	public static String getPath(){
		return getProperties().getProperty(ExampleUtil.PATH);
	}
	
	public static SearchResultDocumentDTO upload(File file) {

		UploadDocumentCall call = new UploadDocumentCall(file);
		call.setBaseURL(getBaseUrl());
		SearchResultDocumentDTO document = call.execute(getCredentials());
		return document;
	}
	
	public static void classify(String documentId, long natureId, MultiValueMap<String, Object> mapValues){
		
		ClassifyDocumentCall classify = new ClassifyDocumentCall(documentId, natureId, null, mapValues);
		classify.setBaseURL(getBaseUrl());
		classify.execute(getCredentials());
	}
}
