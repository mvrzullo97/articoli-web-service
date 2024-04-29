package com.betashop.webapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betashop.webapp.dtos.ArticoliDto;
import com.betashop.webapp.entities.Articoli;
import com.betashop.webapp.entities.Barcode;
import com.betashop.webapp.exceptions.NotFoundException;
import com.betashop.webapp.services.ArticoliService;
import com.betashop.webapp.services.BarcodeService;

@RestController
@RequestMapping("api/articoli")
public class ArticoliController {

	private static final Logger logger = LoggerFactory.getLogger(ArticoliController.class);
	
	@Autowired
	private ArticoliService articoliService;
	
	@Autowired
	private BarcodeService barcodeService;
	
	@GetMapping(value = "/cerca/ean/{barcode}", produces = "application/json")
	public ResponseEntity<Articoli> listArtByEan(@PathVariable("barcode") String barcode) 
			throws NotFoundException {
		
		logger.info("------ otteniamo l'articolo con barcode -> " + barcode + " ------");
		
		Articoli a;
		Barcode Ean = barcodeService.SelByBarcode(barcode);
		
		if (Ean == null) {
			
			String errMsg = String.format("Il barcode %s non è stato trovato!", barcode);
			logger.warn(errMsg);
			
			throw new NotFoundException(errMsg);
//			return new ResponseEntity<Articoli>(HttpStatus.NOT_FOUND);
			
		} else {
			
			a = Ean.getArticolo();
			
		} 
		
		return new ResponseEntity<Articoli>(a, HttpStatus.OK);
		
	}

	@GetMapping(value = "/cerca/codice/{codart}", produces = "application/json")
	public ResponseEntity<ArticoliDto> listArtByCodArt(@PathVariable("codart") String codArt) 
			throws NotFoundException {
		
		logger.info("------ otteniamo l'articolo con codice -> " + codArt + " ------");
		
		
		ArticoliDto a = articoliService.SelByCodArt(codArt);
		
		
		
		if (a == null) {
			
			String errMsg = String.format("L'articolo con codice %s non è stato trovato!", codArt);
			logger.warn(errMsg);
			throw new NotFoundException(errMsg);
			
		}
		
		//logger.info("------ l'articolo " + codArt + " ha " + a.getPzCart() + " pezzi ------");
		
		return new ResponseEntity<ArticoliDto>(a, HttpStatus.OK);
	}
	
	@GetMapping(value = "/cerca/descrizione/{filter}", produces = "application/json")
	public ResponseEntity<List<ArticoliDto>> listArtByDesc(@PathVariable("filter") String filter) 
			throws NotFoundException{
		
		logger.info("------ otteniamo gli articoli con descrizione " + filter + " ------");

		List<ArticoliDto> articoli = articoliService.SelByDescrizione(filter.toUpperCase() + "%");
		
		if (articoli == null) {
			
			String errMsg = String.format("Non è stato trovato alcun articolo avente la descrizione %s!", filter);
			logger.warn(errMsg);
			throw new NotFoundException(errMsg);
			
		}
		
		return new ResponseEntity<List<ArticoliDto>>(articoli, HttpStatus.OK);
	}
	
}
