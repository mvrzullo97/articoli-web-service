package com.betashop.webapp.exceptions;

public class NotFoundException extends Exception {

	private String mex = "";
	
	public NotFoundException() {
		super();
	}

	public NotFoundException(String mex) {
		super(mex);
		this.mex = mex;
	}

	public String getMex() {
		return mex;
	}

	public void setMex(String mex) {
		this.mex = mex;
	}


	private static final long serialVersionUID = 1L;

}
