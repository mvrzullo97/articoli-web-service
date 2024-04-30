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
	private Date dataCreazione;
	private double prezzo = 0;
	
	private Set<BarcodeDto> barcode = new HashSet<>();
	private IngredientiDto ingredienti;
	private CategoriaDto famAssort;
	private IvaDto iva;

}
