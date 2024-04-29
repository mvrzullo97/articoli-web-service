package com.betashop.webapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.betashop.webapp.entities.Articoli;
import com.betashop.webapp.repository.ArticoliRepository;

@Service
@Transactional(readOnly = true)

public class ArticoliServiceImpl implements ArticoliService {

	@Autowired
	ArticoliRepository articoliRepository;
	
	@Override
	public Iterable<Articoli> SelTutti() {
		return articoliRepository.findAll();
	}

	@Override
	public List<Articoli> SelByDescrizione(String descr) {
		return articoliRepository.SelByDescrizioneLike(descr);
	}

	@Override
	public List<Articoli> SelByDescrizione(String descr, Pageable pageable) {
		return articoliRepository.findByDescrizioneLike(descr, pageable);
	}

	@Override
	public Articoli SelByCodArt(String codArt) {
		return articoliRepository.findByCodArt(codArt);
	}

	@Override
	@Transactional
	public void DelArticolo(Articoli a) {
		articoliRepository.delete(a);
	}

	@Override
	@Transactional
	public void InsArticolo(Articoli a) {
		articoliRepository.save(a);
	}

	
	
}
