package com.betashop.webapp.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.betashop.webapp.entities.Articoli;

public interface ArticoliRepository extends PagingAndSortingRepository <Articoli, String> {

	@Query(value = "SELECT * FROM ARTICOLI WHERE DESCRIZIONE LIKE :desArt", nativeQuery = true)
	List<Articoli> SelByDescrizioneLike(@Param("desArt") String descrizione); 
	
	List<Articoli> findByDescrizioneLike(String descrizione, PageRequest pageRequest);
	
	Articoli findByCodArt(String codArt);
	
}
