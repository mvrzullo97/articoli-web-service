package com.betashop.webapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betashop.webapp.dtos.IvaDto;
import com.betashop.webapp.entities.Iva;
import com.betashop.webapp.repository.IvaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class IvaServiceImpl implements IvaService {

	@Autowired
	IvaRepository ivaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<IvaDto> selTutti() {

		List<Iva> categorie = ivaRepository.findAll();
		
		List<IvaDto> retVal = categorie.
				stream()
				.map(source -> modelMapper.map(source, IvaDto.class)).
				collect(Collectors.toList());

		return retVal;
		
	}

}
