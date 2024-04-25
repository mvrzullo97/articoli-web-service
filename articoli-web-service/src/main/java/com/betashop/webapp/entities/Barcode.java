package com.betashop.webapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "BARCODE")
@Data
public class Barcode implements Serializable{

	@Id
	@Column(name = "BARCODE")
	private String barcode;
	
	@Column(name = "IDTIPOART")
	private String idTipoArt;
	
	
	private static final long serialVersionUID = -5964646338552390801L;

	
}
