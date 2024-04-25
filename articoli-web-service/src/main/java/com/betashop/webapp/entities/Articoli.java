package com.betashop.webapp.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Entity
@Table(name = "ARTICOLI")
@Data
public class Articoli implements Serializable {

	@Id
	@Column(name = "CODART")
	private String codArt;
	
	@Column(name = "DESCRIZIONE")
	private String descrizione;
	
	@Column(name = "UM")
	private String um;
	
	@Column(name = "CODSTAT")
	private String codStat;
	
	@Column(name = "PZCART")
	private Integer pzCart;
	
	@Column(name = "PESONETTO")
	private Double pesoNetto;
	
	@Column(name = "IDSTATOART")
	private String idStatoArt;
	
	@Column(name = "DATACREAZIONE")
	@Temporal(TemporalType.DATE)
	private Date dataCreaz;
	
	
	private static final long serialVersionUID = 7390361014785172285L;

}
