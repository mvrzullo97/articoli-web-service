package com.betashop.webapp.exceptions;

public class NotFoundException extends Exception {

	private String mex = "Elemento ricercato NOT FOUND";
	
	public NotFoundException() {
		super();
	}

	public NotFoundException(String mex) {
		super();
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
