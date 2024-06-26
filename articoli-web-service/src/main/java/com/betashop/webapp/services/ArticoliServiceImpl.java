package com.betashop.webapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betashop.webapp.dtos.ArticoliDto;
import com.betashop.webapp.entities.Articoli;
import com.betashop.webapp.repository.ArticoliRepository;

@Service
@Transactional(readOnly = true)

public class ArticoliServiceImpl implements ArticoliService {

	@Autowired
	ArticoliRepository articoliRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Iterable<Articoli> SelTutti() {
		return articoliRepository.findAll();
	}

	@Override
	public List<ArticoliDto> SelByDescrizione(String descr) {
		
		String filter = "%" + descr.toUpperCase() + "%";
		
		List<Articoli> articoli = articoliRepository.SelByDescrizioneLike(filter);
		
		List<ArticoliDto> retVal = articoli
				.stream()
				.map(s -> modelMapper.map(s, ArticoliDto.class))
				.collect(Collectors.toList());
		
		return retVal;
	}

	@Override
	public List<Articoli> SelByDescrizione(String descr, Pageable pageable) {
		return articoliRepository.findByDescrizioneLike(descr, pageable);
	}

	@Override
	public ArticoliDto SelByCodArt(String codArt) {
		
		Articoli a = articoliRepository.findByCodArt(codArt);
		
		return this.convertToDto(a);
	}
	
	@Override
	public ArticoliDto SelByBarcode(String barcode) {
		
		Articoli articoli = articoliRepository.SelByEan(barcode);
		
		return this.convertToDto(articoli);
	}

	private ArticoliDto convertToDto(Articoli articoli) {
		
		ArticoliDto aDto = null;
		
		if (articoli != null) {
			aDto = modelMapper.map(articoli, ArticoliDto.class);
		}	
		
		return aDto;
	}

	@Override
	@Transactional
	public void DelArticolo(Articoli a) {
		articoliRepository.delete(a);
	}

	@Override
	@Transactional
	public void InsArticolo(Articoli a) {

		a.setDescrizione(a.getDescrizione().toUpperCase());	
		articoliRepository.save(a);
	}

	@Override
	public Articoli SelByCodArt2(String codArt) {
		 
		return articoliRepository.findByCodArt(codArt);
	}

	
	
	
}
