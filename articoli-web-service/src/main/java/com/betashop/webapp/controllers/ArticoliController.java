package com.betashop.webapp.controllers;

import java.lang.ProcessHandle.Info;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.betashop.webapp.dtos.ArticoliDto;
import com.betashop.webapp.dtos.InfoMsg;
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
	public ResponseEntity<InfoMsg> createArt(@Valid @RequestBody Articoli articolo, BindingResult bindingResult)
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
		
		return new ResponseEntity<InfoMsg>(new InfoMsg(LocalDate.now(), "Articolo persistito con successo!"), HttpStatus.CREATED);
	}
	
	@PutMapping("/modifica")
	public ResponseEntity<InfoMsg> updateArt(@Valid @RequestBody Articoli articolo, BindingResult bindingResult)
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
		
		return new ResponseEntity<InfoMsg>(new InfoMsg(LocalDate.now(), "Articolo modificato con successo!"), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/elimina/{codart}", produces = "application/json" )
	public ResponseEntity<?> updateArt(@PathVariable("codart") String codArt)
	throws NotFoundException, DuplicateException {
		
		logger.info("Eliminiamo l'articolo con codice " + codArt);
		
		if (codArt.equals("ArticoloDiTest1"))
		{
			String errMex = String.format("L'articolo con codice %s non può essere eliminato!" , codArt);
			
			logger.warn(errMex);
			
			throw new DuplicateException(errMex);
		}

		Articoli delArticolo = articoliService.SelByCodArt2(codArt);
		
		if (delArticolo == null)
		{
			String errMex = String.format("Articolo %s non presente in anagrafica! ", codArt);
			logger.warn(errMex);
			
			throw new NotFoundException(errMex);
		} 

		articoliService.DelArticolo(delArticolo);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseNode = mapper.createObjectNode();
		
		responseNode.put("codice", HttpStatus.OK.toString());
		System.out.println(HttpStatus.OK.toString());
		//responseNode.put("mex", "Eliminazione Articolo con codice " + codArt + " eseguita con successo");
		responseNode.put("mex", "Articolo eliminato correttamente!");

		logger.info("Eliminazione articolo con codice " + codArt + " avvenuta con successo!");
		
		return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK );
	}
}

