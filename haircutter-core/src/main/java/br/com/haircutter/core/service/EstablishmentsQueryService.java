package br.com.haircutter.core.service;

import java.util.List;

import br.com.haircutter.core.model.Establishment;

public interface EstablishmentsQueryService {
	
	List<Establishment> findByCity(String city);
}
