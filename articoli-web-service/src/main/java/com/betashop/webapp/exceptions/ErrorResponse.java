package com.betashop.webapp.exceptions;

import java.util.Date;

import lombok.Data;

@Data

public class ErrorResponse {

	private Date data = new Date();
	private int codice;
	private String mex;
	
}
