package com.heylyn.result;

import com.heylyn.exception.ResultException;
public interface OnHttpResultCallback<T>{
	  public void success(Class<T>cls, String msg);
	  public void failure(ResultException e);
}
