package com.heylyn.exception;

public class ResultException extends Exception{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 8214365274285702175L;
	public ResultException(){
	        super();
	    }
	    public ResultException(String message){
	        super(message);
	    }
	    public ResultException(String message,Throwable cause){
	        super(message,cause);
	    }
}
