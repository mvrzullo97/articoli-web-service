package com.betashop.webapp.exceptions;

public class DuplicateException extends Exception {

	private static final long serialVersionUID = 1L;

	private String m;

	public DuplicateException(String m) {
		super(m);
		this.m = m;
	}
	
	public DuplicateException() {
		super();
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}
	
	
	
}
