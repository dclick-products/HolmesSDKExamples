package br.com.dclick.holmes.sdk.cases;

import java.io.File;
import java.util.Arrays;

import br.com.dclick.holmes.sdk.document.DownloadDocumentCall;
import br.com.dclick.holmes.sdk.dto.DocumentDTO;
import br.com.dclick.holmes.sdk.dto.SearchResultDTO;
import br.com.dclick.holmes.sdk.search.PermissionType;
import br.com.dclick.holmes.sdk.search.SearchCall;
import br.com.dclick.holmes.sdk.util.ExampleUtil;

public class SearchAndDownloadCase {

	public static void main(String[] args) {
		
		String query = "*:*";
		int start 	= 0;
		int rows 	= 5;
		
		SearchCall searchCall = new SearchCall(query, start, rows, PermissionType.VIEW);
		searchCall.setBaseURL(ExampleUtil.getBaseUrl());
		SearchResultDTO execute = searchCall.execute(ExampleUtil.getCredentials());
		
		DocumentDTO firstDocument = execute.getDocuments().get(0);
		
		File destination = new File("/tmp/" + firstDocument.getName());
		
		DownloadDocumentCall downloadCall = new DownloadDocumentCall(destination, Arrays.asList(firstDocument.getDocumentversionid()));
		downloadCall.setBaseURL(ExampleUtil.getBaseUrl());
		downloadCall.execute(ExampleUtil.getCredentials());
	}
}
