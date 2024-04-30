package com.betashop.webapp;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.betashop.webapp.dtos.ArticoliDto;
import com.betashop.webapp.dtos.BarcodeDto;
import com.betashop.webapp.entities.Articoli;
import com.betashop.webapp.entities.Barcode;


@Configuration
public class ModelMapperConf {

	@Bean
	public ModelMapper modelMapper() {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		
		modelMapper.addMappings(articoliMapping);
		
		modelMapper.addMappings(new PropertyMap<Barcode, BarcodeDto>() {

			@Override
			protected void configure() {
				map().setTipo(source.getIdTipoArt());
			}
		});
		
		modelMapper.addConverter(articoliConverter);
		
		return modelMapper;		
	}
	
	PropertyMap<Articoli, ArticoliDto> articoliMapping = new PropertyMap<Articoli, ArticoliDto>() {
		
		@Override
		protected void configure() {
			map().setDataCreazione(source.getDataCreaz());		
		}
	};
	
	Converter<String, String> articoliConverter = new Converter<String, String>() {

		@Override
		public String convert(MappingContext<String, String> context) {
			return context.getSource() == null ? "" : context.getSource().trim();
		}
	};
	
}
