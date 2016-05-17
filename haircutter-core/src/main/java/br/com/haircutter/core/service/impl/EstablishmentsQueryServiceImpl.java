package br.com.haircutter.core.service.impl;

import br.com.haircutter.core.enums.EstablishmentStatusEnum;
import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.model.EstablishmentEmployee;
import br.com.haircutter.core.model.EstablishmentService;
import br.com.haircutter.core.model.repository.EstablishmentEmployeeRespository;
import br.com.haircutter.core.model.repository.EstablishmentRespository;
import br.com.haircutter.core.model.repository.EstablishmentServiceRespository;
import br.com.haircutter.core.service.EstablishmentsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstablishmentsQueryServiceImpl implements EstablishmentsQueryService{

	@Autowired
    EstablishmentRespository establishmentRepository;
	
	@Autowired
	EstablishmentEmployeeRespository establishmentEmployeeReposiroy;
	
	@Autowired
	EstablishmentServiceRespository establishmentServiceRepository;

	@Override
	public List<Establishment> findByCity(String city) {
		List<Establishment> establishments = establishmentRepository.findByAddressCityAndStatus(city, EstablishmentStatusEnum.ACTIVE);
		
		for(Establishment establishment : establishments){
			
			//Add establishment employees to the establishment object
			List<EstablishmentEmployee> establishmentEmployees; 
			establishmentEmployees = establishmentEmployeeReposiroy.findAllByEstablishmentCnpjAndDeleted(establishment.getCnpj(), false);
			establishment.setEstablishmentEmployees(establishmentEmployees);
			
			//Add establishment services to the establishment object
			List<EstablishmentService> establishmentServices;
			establishmentServices = establishmentServiceRepository.findAllByEstablishmentCnpjAndDeleted(establishment.getCnpj(), false);
			establishment.setEstablishmentServices(establishmentServices);
		}
		
		return establishments;
	}
}
