package com.betashop.webapp.exceptions;

public class BindingException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String mex;

	public BindingException(String mex) {
		super();
		this.mex = mex;
	}
	
	public BindingException() {
		super();
	}

	public String getMex() {
		return mex;
	}

	public void setMex(String mex) {
		this.mex = mex;
	}
	
	
	

	
	
}
