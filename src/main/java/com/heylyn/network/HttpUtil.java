package com.heylyn.network;

import java.io.IOException;

import com.heylyn.exception.ResultException;
import com.heylyn.result.OnResultCallback;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {

	public static void request(final String url, final OnResultCallback callback) {
	
		ResultThread thread = new ResultThread(new ResultRunnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
			     try {
	                    OkHttpClient client = new OkHttpClient();
	                    Request request = new Request.Builder()
	                            .url(url)
	                            .build();
	                    Response response = null;
	                    response = client.newCall(request).execute();
	                    if (response.isSuccessful()) {
	                        String str = (String)response.body().string();
	                        callback.success(str, "loading reuqest success");
	                    
	                    }
	                } catch (IOException e) {	                  
	                    callback.failure(new ResultException(e.getMessage()));
	                } catch (Exception e) {
	                	 callback.failure(new ResultException(e.getMessage()));
	                }

			}});
		thread.start();
	}
	
	
	

}
