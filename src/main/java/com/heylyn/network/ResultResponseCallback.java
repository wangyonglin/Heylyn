package com.heylyn.network;

import com.heylyn.exception.ResultException;

public interface ResultResponseCallback {
	  public void success(ResultResponse res , String msg);
	  public void failure(ResultException e);
}
