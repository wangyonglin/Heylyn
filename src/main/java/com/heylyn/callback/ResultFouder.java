package com.heylyn.callback;

public interface ResultFouder<T>{
	public void success(T t);
	public void failure(RuntimeException e);
}
