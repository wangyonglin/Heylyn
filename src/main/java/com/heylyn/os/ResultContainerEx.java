package com.heylyn.os;

public interface ResultContainerEx<T> {
	public void success(T t);
	public void failure(RuntimeException e);
}
