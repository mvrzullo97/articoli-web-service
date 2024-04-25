package com.betashop.webapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "INGREDIENTI")
@Data
public class Ingredienti implements Serializable{

	@Id
	@Column(name = "CODART")
	private String codArt;
	
	@Column(name = "INFO")
	private String info;
	
	private static final long serialVersionUID = 7856158123851115733L;

	
	
}
