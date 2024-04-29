package com.betashop.webapp.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.betashop.webapp.entities.Barcode;
import com.betashop.webapp.entities.FamAssort;
import com.betashop.webapp.entities.Ingredienti;
import com.betashop.webapp.entities.Iva;

import lombok.Data;

@Data
public class ArticoliDto {
	
	private String codArt;
	private String descrizione;
	private String um;
	private String codStat;
	private String pzCart;
	private double pesoNetto;
	private String idStatoArt;
	private Date dataCreaz;
	private double prezzo = 0;
	
	private Set<Barcode> barcode = new HashSet<>();
	private Ingredienti ingredienti;
	private FamAssort famAssort;
	private Iva iva;

}
