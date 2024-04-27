package com.betashop.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betashop.webapp.entities.Barcode;

public interface BarcodeRepository extends JpaRepository<Barcode, String>{

	Barcode findByBarcode(String barcode);
	
}
