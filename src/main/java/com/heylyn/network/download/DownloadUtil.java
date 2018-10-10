package com.heylyn.network.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import com.heylyn.exception.ResultException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadUtil {
	 private static OkHttpClient client=null;

	    private synchronized static  OkHttpClient instance() {
	        if (client == null) {
	            OkHttpClient.Builder builder = new OkHttpClient.Builder()
	                    //设置连接超时等属性,不设置可能会报异常
	                    .connectTimeout(120, TimeUnit.SECONDS)
	                    .readTimeout(120, TimeUnit.SECONDS)
	                    .writeTimeout(120, TimeUnit.SECONDS);
	            builder.addInterceptor(new DownloadInterceptor());
	            client = builder.build();
	        }
	        return client;
	    }
	    private static  Request request(String uri) {
	        return new Request.Builder() .url(uri).build();	
	    }
	public static void run(String uri,String parent,String child,ProgressCallback callback) {
		client = DownloadUtil.instance();
		client.newCall(request(uri)).enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				// TODO Auto-generated method stub
				// 下载失败
				callback.failure(new ResultException(e.getMessage()));
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				// TODO Auto-generated method stub
				InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
               
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(parent,child);
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        callback.loading(progress);
                    }
                    fos.flush();
                    // 下载完成
                    callback.success();
                } catch (Exception e) {
                	callback.failure(new ResultException(e.getMessage()));
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
			}});
	}
	
	public static void run(String uri,File file,ProgressCallback callback) {
		client = DownloadUtil.instance();
		client.newCall(request(uri)).enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				// TODO Auto-generated method stub
				// 下载失败
				callback.failure(new ResultException(e.getMessage()));
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				// TODO Auto-generated method stub
				InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
               
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                   
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        callback.loading(progress);
                    }
                    fos.flush();
                    // 下载完成
                    callback.success();
                } catch (Exception e) {
                	callback.failure(new ResultException(e.getMessage()));
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
			}});
	}


}
