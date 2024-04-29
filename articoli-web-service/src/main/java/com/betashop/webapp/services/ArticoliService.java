package com.betashop.webapp.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.betashop.webapp.entities.Articoli;

public interface ArticoliService {

	public Iterable<Articoli> SelTutti();
	
	public List<Articoli> SelByDescrizione(String descr);
	
	public List<Articoli> SelByDescrizione(String descr, Pageable pageable);
	
	public Articoli SelByCodArt(String codArt);
	
	public void DelArticolo(Articoli articolo);
	
	public void InsArticolo(Articoli articolo);
	
}
