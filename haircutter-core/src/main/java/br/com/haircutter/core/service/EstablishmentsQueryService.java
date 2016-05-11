package br.com.haircutter.core.service;

import java.util.List;

import br.com.haircutter.core.model.Establishment;

public interface EstablishmentsQueryService {
	
	List<Establishment> findEstablishmentByName(String establishmentName);
	
	List<Establishment> findEstablishmentByProfessional(String professionalName);
	
	List<Establishment> findEstablishmentByServices(String serviceName);
	
	List<Establishment> findEstablishmentByAddress(String address);
	
	List<Establishment> findEstablishemtnByReputation(String reputation);
	
	List<Establishment> findAll(String text);
}
