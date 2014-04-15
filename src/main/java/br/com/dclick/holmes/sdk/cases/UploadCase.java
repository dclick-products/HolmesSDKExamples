package br.com.dclick.holmes.sdk.cases;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.dclick.holmes.sdk.dto.SearchResultDocumentDTO;
import br.com.dclick.holmes.sdk.util.ExampleUtil;

public class UploadCase {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadCase.class);
	
	public static void main(String[] args) {
		
		final File file = new File(ExampleUtil.getPath() +  "upload.pdf");
		SearchResultDocumentDTO document = ExampleUtil.upload(file);
		
		Integer documentId = document.get_document();
		String documentVersionId = document.get_documentversionid();
		
		logger.info("DOCUMENT_ID=[" + documentId  + "] DOCUMENT_VERSION_ID=[" + documentVersionId + "]");
	}
}
