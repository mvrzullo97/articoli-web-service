package com.betashop.webapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betashop.webapp.dtos.CategoriaDto;
import com.betashop.webapp.dtos.IvaDto;
import com.betashop.webapp.entities.Iva;
import com.betashop.webapp.exceptions.NotFoundException;
import com.betashop.webapp.services.CategoriaService;
import com.betashop.webapp.services.IvaService;

import lombok.extern.java.Log;

@RestController
@RequestMapping("api/iva")
@Log
@CrossOrigin("http://localhost:4200")

public class IvaController {
	
	@Autowired
	private IvaService ivaService;
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<IvaDto>> getCategorie() 
	throws NotFoundException {
		
		log.info("--- Otteniamo le aliquote IVA ---");
		
		List<IvaDto> iva = ivaService.selTutti();
		
		if (iva.isEmpty()) 
		{
			String mexErr = String.format("Nessun elemento esistente");
			log.warning(mexErr);	
			throw new NotFoundException(mexErr);
		}
		
		return new ResponseEntity<List<IvaDto>>(iva, HttpStatus.OK);		
	}
}