package com.betashop.webapp.entities;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ARTICOLI")
@Data
public class Articoli implements Serializable {

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "articolo", orphanRemoval = true)
	@JsonManagedReference
	private Set<Barcode> barcode = new HashSet<>();
	
	@OneToOne(mappedBy = "articolo", cascade = CascadeType.ALL, orphanRemoval = true)
	private Ingredienti ingredienti;
	
	@ManyToOne
	@JoinColumn(name = "IDIVA", referencedColumnName = "idIva")
	private Iva iva;
	
	@ManyToOne
	@JoinColumn(name = "IDFAMASS", referencedColumnName = "ID")
	private FamAssort famAssort;
	
	@Id
	@Column(name = "CODART")
	@Size(min = 5, max = 20, message = "{Size.Articoli.codArt.Validation}")
	@NotNull(message = "{NotNull.Articoli.codArt.Validation}")
	private String codArt;
	
	@Column(name = "DESCRIZIONE")
	@Size(min = 6, max = 80, message = "{Size.Articoli.descrizione.Validation}")
	private String descrizione;
	
	@Column(name = "UM")
	private String um;
	
	@Column(name = "CODSTAT")
	private String codStat;
	
	@Column(name = "PZCART")
	@Max(value = 99, message = "{Max.Articoli.codArt.Validation}")
	private Integer pzCart;
	
	@Column(name = "PESONETTO")
	@Min(value = (long) 0.01, message = "{Min.Articoli.codArt.Validation}")
	private Double pesoNetto;
	
	@Column(name = "IDSTATOART")
	@NotNull(message = "{NotNull.Articoli.codArt.Validation}")
	private String idStatoArt;
	
	@Column(name = "DATACREAZIONE")
	@Temporal(TemporalType.DATE)
	private Date dataCreaz;
	
	
	private static final long serialVersionUID = 7390361014785172285L;

}
