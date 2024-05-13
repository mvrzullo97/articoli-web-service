package com.betashop.webapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betashop.webapp.dtos.CategoriaDto;
import com.betashop.webapp.exceptions.NotFoundException;
import com.betashop.webapp.services.CategoriaService;

import lombok.extern.java.Log;

@RestController
@RequestMapping("api/cat")
@Log
@CrossOrigin("http://localhost:4200")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<CategoriaDto>> getCategorie() 
	throws NotFoundException {
		
		log.info("--- Otteniamo le categorie ---");
		
		List<CategoriaDto> categorie = categoriaService.selTutti();
		
		if (categorie.isEmpty()) 
		{
			String mexErr = String.format("Nessun elemento esistente");
			log.warning(mexErr);
			
			throw new NotFoundException(mexErr);
		}
		
		return new ResponseEntity<List<CategoriaDto>>(categorie, HttpStatus.OK);		
	}
}
