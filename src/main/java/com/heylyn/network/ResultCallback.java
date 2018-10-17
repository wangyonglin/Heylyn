package com.heylyn.network;


public interface ResultCallback<T>{
	  public void success(T res);
	  public void failure(RuntimeException e);
}
