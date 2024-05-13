package com.betashop.webapp.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.xml.transform.Source;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betashop.webapp.dtos.CategoriaDto;
import com.betashop.webapp.entities.FamAssort;
import com.betashop.webapp.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<CategoriaDto> selTutti() {
		
		List<FamAssort> categorie = categoriaRepository.findAll();
		
		List<CategoriaDto> retVal = categorie.
				stream()
				.map(source -> modelMapper.map(source, CategoriaDto.class)).
				collect(Collectors.toList());

		return retVal;
	}

}
