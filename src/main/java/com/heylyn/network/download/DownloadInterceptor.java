package com.heylyn.network.download;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadInterceptor implements Interceptor{

	@Override
	public Response intercept(Chain chain) throws IOException {
		// TODO Auto-generated method stub
		 Request request = chain.request();
		   System.out.println(request.toString());
		 Response response = chain.proceed(request);
		  System.out.println(response);
		return response;
	}


}
