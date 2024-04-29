package com.betashop.webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betashop.webapp.entities.Barcode;
import com.betashop.webapp.repository.BarcodeRepository;

@Service
@Transactional(readOnly = true)
public class BarcodeServiceImpl implements BarcodeService {

	@Autowired
	private BarcodeRepository barcodeRepository;
	
	@Override
	public Barcode SelByBarcode(String barcode) {
		return barcodeRepository.findByBarcode(barcode);
	}

	
	
}
