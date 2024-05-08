package com.betashop.webapp.controllers;

import java.util.List;

import javax.management.ObjectName;

import org.aspectj.weaver.tools.Trace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.betashop.webapp.dtos.ArticoliDto;
import com.betashop.webapp.entities.Articoli;
import com.betashop.webapp.exceptions.BindingException;
import com.betashop.webapp.exceptions.DuplicateException;
import com.betashop.webapp.exceptions.NotFoundException;
import com.betashop.webapp.services.ArticoliService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/articoli")
@CrossOrigin("http://localhost:4200")
public class ArticoliController {

	private static final Logger logger = LoggerFactory.getLogger(ArticoliController.class);
	
	@Autowired
	private ArticoliService articoliService;
	
	@Autowired
	private ResourceBundleMessageSource errMessage;
	
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
	
	@PostMapping(value = "/inserisci")
	public ResponseEntity<Articoli> createArt(@Valid @RequestBody Articoli articolo, BindingResult bindingResult)
	throws BindingException, DuplicateException {
		
		logger.info("Inserisco l'articolo con codice -> " + articolo.getCodArt());
		
		if (bindingResult.hasErrors())
		{
			String errMex = errMessage.getMessage(bindingResult.getFieldError(), null);
			logger.warn(errMex);
			
			throw new BindingException(errMex);
		}
		
		Articoli checkArt = articoliService.SelByCodArt2(articolo.getCodArt());
		
		if (checkArt != null) 
		{
			String errMex = String.format("Articolo %s presente in anagrafica! "
					+ "Impossibile utilizzare il metodo POST", articolo.getCodArt());
			logger.warn(errMex);
			
			throw new DuplicateException(errMex);		
		}
		
		articoliService.InsArticolo(articolo);
		
		return new ResponseEntity<Articoli>(new HttpHeaders(), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/modifica", method = RequestMethod.PUT)
	public ResponseEntity<Articoli> updateArt(@Valid @RequestBody Articoli articolo, BindingResult bindingResult)
	throws BindingException, NotFoundException {
		
		logger.info("Modifichiamo l'articolo con codice " + articolo.getCodArt());
		
		if (bindingResult.hasErrors())
		{
			String errMex = errMessage.getMessage(bindingResult.getFieldError(), null);
			logger.warn(errMex);
			
			throw new BindingException(errMex);
		}
		
		ArticoliDto checkArt = articoliService.SelByCodArt(articolo.getCodArt());

		if (checkArt == null) 
		{
			String errMex = String.format("Articolo %s non presente in anagrafica! "
					+ "Impossibile utilizzare il metodo PUT", articolo.getCodArt());
			logger.warn(errMex);
			
			throw new NotFoundException(errMex);		
		}
		
		articoliService.InsArticolo(articolo);
		
		return new ResponseEntity<Articoli>(new HttpHeaders(), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/elimina/{codart}", produces = "application/json" )
	public ResponseEntity<?> updateArt(@PathVariable("codArt") String codArt)
	throws NotFoundException {
		
		logger.info("Eliminiamo l'articolo con codice " + codArt);

		Articoli delArticolo = articoliService.SelByCodArt2(codArt);
		
		if (delArticolo == null)
		{
			String errMex = String.format("Articolo %s non presente in anagrafica! ",codArt);
			logger.warn(errMex);
			
			throw new NotFoundException(errMex);
		}
		
		articoliService.DelArticolo(delArticolo);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseNode = mapper.createObjectNode();
		
		responseNode.put("codice", HttpStatus.OK.toString());
//		System.out.println(HttpStatus.OK.toString());
		responseNode.put("mex", "Eliminazione Articolo " + codArt + " eseguita con successo");
		
		return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK );
	}
}

