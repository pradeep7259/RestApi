package com.printingHW.testcase;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

public class TestCaseApi {
    
	private volatile static String API_URL = "http://103.91.187.88:2427/ACCOUNTS-SMS-API/";
	private volatile static String API_REQUEST_HEADER_KEY = "X-CERP-API-KEY";
	private volatile static String API_REQUEST_HEADER_VALUE = "800350bbc645c75c59cb12701a8b06c5b10b85cdd90b2f5b017d5e457738f248";
	
	public static String sendRequestToAccountAPI(final String methodType,final String classURL,
			final String requestBody)throws IOException{
		/* you need to be add Dependency for AsyncHttpClient and CompletableFuture*/
		try(AsyncHttpClient client = new DefaultAsyncHttpClient()){
			
			CompletableFuture<Response> future = client.prepare(methodType, API_URL+classURL)
					.setHeader("content-type", "application/json")
					.setHeader(API_REQUEST_HEADER_KEY, API_REQUEST_HEADER_VALUE).setBody(requestBody).execute()
					.toCompletableFuture();
			
			Response response = future.join();
			return response.getStatusCode()+"&"+response.getResponseBody();
		}
		
		
				
	}
}
