package com.heylyn.network;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import com.heylyn.exception.ResultException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpClient {
	public static void get(String url,ResultResponseCallback callback) {
		 FutureTask<ResultResponse> task = new FutureTask<ResultResponse>(new Callable<ResultResponse>() {

			@Override
			public ResultResponse call() throws Exception {
				// TODO Auto-generated method stub
				ResultResponse resultResponse = new ResultResponse();
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = null;
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        resultResponse.body=(String) response.body().string();
                        resultResponse.code=response.code();
                    }else {
                    	callback.failure(new ResultException("network request error: "+ response.code()));
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
	              callback.success(task.get(),"network request ok");
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } catch (ExecutionException e) {
	            e.printStackTrace();
	        }
	       
	    
	}
}
