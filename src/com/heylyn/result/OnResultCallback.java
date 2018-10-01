package com.heylyn.result;

import com.heylyn.exception.ResultException;

public interface OnResultCallback {
	  public void success(Object obj , String msg);
	  public void failure(ResultException e);
}
