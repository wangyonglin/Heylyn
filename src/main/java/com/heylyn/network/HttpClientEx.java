package com.heylyn.network;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpClientEx {
	public static void execute(String url,ResultCallback<InputStream> callback) {

		 FutureTask<InputStream> task = new FutureTask<InputStream>(new Callable<InputStream>() {

			@Override
			public InputStream call() throws Exception {
				// TODO Auto-generated method stub
				InputStream resultResponse = null;
              try {
                  OkHttpClient client = new OkHttpClient();
                  Request request = new Request.Builder()
                          .url(url)
                          .build();
                  Response response = null;
                  response = client.newCall(request).execute();
                  if (response.isSuccessful()) {
               	   resultResponse=response.body().byteStream();
                  }else {
               	  callback.failure(new RuntimeException("network request error : "+ response.code()));
                  	
                  }
              } catch (IOException e) {
                  System.out.println(e.getStackTrace());

              } catch (Exception e) {
                  System.out.println(e.getStackTrace());

              }
              return resultResponse;
			}});
	        new Thread(task).start();
	      
	        try {
	              callback.success(task.get());
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } catch (ExecutionException e) {
	            e.printStackTrace();
	        }
	       
	    
	}
}
