package com.heylyn.os;

public interface AuthorityCallback<T>{
	public void success(T t);
	public void failure(RuntimeException e);
}
