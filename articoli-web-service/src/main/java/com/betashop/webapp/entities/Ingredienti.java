package com.betashop.webapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "INGREDIENTI")
@Data
public class Ingredienti implements Serializable{

	@OneToOne
	@PrimaryKeyJoinColumn
	@JsonIgnore
	private Articoli articoli;
	
	@Id
	@Column(name = "CODART")
	private String codArt;
	
	@Column(name = "INFO")
	private String info;
	
	private static final long serialVersionUID = 7856158123851115733L;

	
	
}
