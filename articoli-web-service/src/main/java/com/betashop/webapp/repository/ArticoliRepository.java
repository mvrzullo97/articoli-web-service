package com.betashop.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import com.betashop.webapp.entities.Articoli;

public interface ArticoliRepository extends PagingAndSortingRepository <Articoli, String>, JpaRepository<Articoli, String>{

	@Query(value = "SELECT * FROM Articoli WHERE descrizione LIKE :desArt", nativeQuery = true)
	List<Articoli> SelByDescrizioneLike(@Param("desArt") String descrizione); 
	
	List<Articoli> findByDescrizioneLike(String descrizione, Pageable pageable);
	
	Articoli findByCodArt(String codArt);
	
	/* query basata su JPQL (Java Persistence Query Language) */
	@Query(value = "SELECT a FROM Articoli a JOIN a.barcode b WHERE b.barcode IN (:ean)")
	Articoli SelByEan(@Param("ean") String ean);
	
}
