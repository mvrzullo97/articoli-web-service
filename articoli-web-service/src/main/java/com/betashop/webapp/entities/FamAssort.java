package com.betashop.webapp.entities;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Entity
@Table(name = "famassort")
@Data
public class FamAssort {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "famAssort")
	@JsonBackReference
	private Set<Articoli> articoli = new HashSet<>();
	
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "DESCRIZIONE")
	private String descrizione;
}
