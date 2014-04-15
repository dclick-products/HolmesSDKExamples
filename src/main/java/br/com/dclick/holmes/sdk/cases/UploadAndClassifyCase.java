package br.com.dclick.holmes.sdk.cases;

import java.io.File;
import java.util.Properties;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.dclick.holmes.sdk.dto.SearchResultDocumentDTO;
import br.com.dclick.holmes.sdk.util.ExampleUtil;

public class UploadAndClassifyCase {
	
	public static void main(String[] args) {
		
		Properties properties = ExampleUtil.getProperties();
		
		//Loading properties values
		Long natureId = Long.valueOf(properties.getProperty(ExampleUtil.NATURE_DEVELOPMENT));
		String nameCharacteristicId = properties.getProperty(ExampleUtil.CHARACTERISTIC_NAME);
		String languageCharacteristicId = properties.getProperty(ExampleUtil.CHARACTERISTIC_LANGUAGE);

		//Upload and classify
		File file = new File(ExampleUtil.getPath() + "upload.pdf");
		SearchResultDocumentDTO document = ExampleUtil.upload(file);
		Integer documentId = document.get_document();
		
		MultiValueMap<String, Object> mapValues = new LinkedMultiValueMap<String, Object>();
		mapValues.add(nameCharacteristicId, "Tutorial hibernate");
		mapValues.add(languageCharacteristicId, "Java");
		
		ExampleUtil.classify(documentId.toString(), natureId, mapValues);
	}
}
