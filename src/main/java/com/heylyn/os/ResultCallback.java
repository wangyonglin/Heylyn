package com.heylyn.os;


import com.heylyn.exception.ResultException;

public interface ResultCallback{
	    public void resove(Object o);
	    public void reject(ResultException e);
}
