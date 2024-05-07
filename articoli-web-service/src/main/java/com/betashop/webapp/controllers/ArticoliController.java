package com.betashop.webapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betashop.webapp.dtos.ArticoliDto;
import com.betashop.webapp.exceptions.NotFoundException;
import com.betashop.webapp.services.ArticoliService;

@RestController
@RequestMapping("api/articoli")
@CrossOrigin("http://localhost:4200")
public class ArticoliController {

	private static final Logger logger = LoggerFactory.getLogger(ArticoliController.class);
	
	@Autowired
	private ArticoliService articoliService;
	
	@GetMapping(value = "/cerca/barcode/{ean}", produces = "application/json")
	public ResponseEntity<ArticoliDto> listArtByEan(@PathVariable("ean") String ean) 
			throws NotFoundException {
		
		logger.info("------ otteniamo l'articolo con barcode -> " + ean + " ------");
		
		
		ArticoliDto a = articoliService.SelByBarcode(ean);
		
		if (a == null) {
			
			String errMsg = String.format("Il barcode %s non è stato trovato!", ean);
			logger.warn(errMsg);
			
			throw new NotFoundException(errMsg);
		} 
		
		return new ResponseEntity<ArticoliDto>(a, HttpStatus.OK);
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
		
		return new ResponseEntity<ArticoliDto>(a, HttpStatus.OK);
	}
	
	@GetMapping(value = "/cerca/descrizione/{filter}", produces = "application/json")
	public ResponseEntity<List<ArticoliDto>> listArtByDesc(@PathVariable("filter") String filter) 
			throws NotFoundException{
		
		logger.info("------ otteniamo gli articoli con descrizione " + filter + " ------");

		List<ArticoliDto> articoli = articoliService.SelByDescrizione(filter.toUpperCase() + "%");
		
		if (articoli.isEmpty()) {
			
			String errMsg = String.format("Non è stato trovato alcun articolo avente la descrizione %s!", filter);
			logger.warn(errMsg);
			throw new NotFoundException(errMsg);		
		}
		
		return new ResponseEntity<List<ArticoliDto>>(articoli, HttpStatus.OK);
	}
	
}
