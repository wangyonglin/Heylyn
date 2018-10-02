package com.heylyn.network;

import java.io.IOException;

import com.heylyn.exception.ResultException;
import com.heylyn.os.Handler;
import com.heylyn.os.Looper;
import com.heylyn.os.Message;
import com.heylyn.result.OnResultCallback;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {

	public static void request(final String url, final OnResultCallback callback) {
		 Looper.prepare();
	        final Handler handler = new Handler() {
	            @Override
	            public void handleMessage(Message message) {
	            	callback.success(message.obj.toString(), "loading reuqest success");
	            }
	        };
	    	Runnable runnable=new Runnable() {
				@Override
				public void run() {
					try {
						OkHttpClient client = new OkHttpClient();
						Request request = new Request.Builder()
								.url(url)
								.build();
						Response response = null;
						response = client.newCall(request).execute();
						if (response.isSuccessful()) {
							String str = (String)response.body().string();
							Message msg = new Message();
							msg.obj =str;
							handler.sendMessage(msg);
						}
					} catch (IOException e) {
						System.out.println(e.getStackTrace());
						callback.failure(new ResultException(e.getMessage()));
					} catch (Exception e) {
						System.out.println(e.getStackTrace());
						callback.failure(new ResultException(e.getMessage()));
					}
				}
			};
		
		new Thread(runnable).start();
		Looper.loop();
	}
	
	
	

}
