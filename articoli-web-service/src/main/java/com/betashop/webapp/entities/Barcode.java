package com.betashop.webapp.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "BARCODE")
@Data
public class Barcode implements Serializable{
	
	@Id
	@Column(name = "BARCODE")
	@Size(min = 8, max = 13, message = "{Size.Barcode.barcode.Validation}")
	@NotNull(message = "{NotNull.Barcode.barcode.Validation}")
	private String barcode;
	
	@Column(name = "IDTIPOART")
	@NotNull(message = "{NotNull.Barcode.barcode.Validation}")
	private String idTipoArt;
	
	@ManyToOne
	@EqualsAndHashCode.Exclude
	@JoinColumn(name = "CODART", referencedColumnName = "codArt")
	@JsonBackReference
	private Articoli articolo;

	
	private static final long serialVersionUID = -5964646338552390801L;

	
}
