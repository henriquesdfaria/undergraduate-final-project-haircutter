package br.com.haircutter.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.model.repository.EstablishmentsQueryRepository;
import br.com.haircutter.core.service.EstablishmentsQueryService;

@Service
public class EstablishmentsQueryServiceImpl implements EstablishmentsQueryService{

	@Autowired
    EstablishmentsQueryRepository establishmentsQueryRepository;
	
	@Override
	public List<Establishment> findEstablishmentByName(String establishmentName) {
		return establishmentsQueryRepository.findEstablishmentByName(establishmentName);
	}

	@Override
	public List<Establishment> findEstablishmentByProfessional(String professionalName) {
		return establishmentsQueryRepository.findEstablishmentByProfessional(professionalName);
	}

	@Override
	public List<Establishment> findEstablishmentByServices(String serviceName) {
		return establishmentsQueryRepository.findEstablishmentByServices(serviceName);
	}

	@Override
	public List<Establishment> findEstablishmentByAddress(String address) {
		return establishmentsQueryRepository.findEstablishmentByAddress(address);
	}

	@Override
	public List<Establishment> findEstablishemtnByReputation(String reputation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Establishment> findAll(String text) {
		List<Establishment> result = new ArrayList<Establishment>();
		
		result.addAll(findEstablishmentByName(text));
		result.addAll(findEstablishmentByProfessional(text));
		result.addAll(findEstablishmentByServices(text));
		result.addAll(findEstablishmentByAddress(text));
		result.addAll(findEstablishemtnByReputation(text));
		
		return result;
	}
}
