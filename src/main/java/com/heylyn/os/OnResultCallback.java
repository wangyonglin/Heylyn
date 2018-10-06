package com.heylyn.os;

import java.util.List;

import com.heylyn.exception.ResultException;

public interface OnResultCallback<T> {
	  	public void resove(List<T> list);
	    public void resove( Class<T> cls);
	    public void reject(ResultException e);
}
