package com.betashop.webapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "BARCODE")
@Data
public class Barcode implements Serializable{

	@ManyToOne
	@EqualsAndHashCode.Exclude
	@JoinColumn(name = "CODART", referencedColumnName = "codArt")
	@JsonBackReference
	private Articoli articolo;
	
	@Id
	@Column(name = "BARCODE")
	private String barcode;
	
	@Column(name = "IDTIPOART")
	private String idTipoArt;
	
	
	private static final long serialVersionUID = -5964646338552390801L;

	
}
