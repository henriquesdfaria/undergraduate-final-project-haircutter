package br.com.haircutter.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.haircutter.core.enums.EstablishmentStatusEnum;
import br.com.haircutter.core.enums.ScheduleStatusEnum;
import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.model.EstablishmentEmployee;
import br.com.haircutter.core.model.EstablishmentService;
import br.com.haircutter.core.model.Schedule;
import br.com.haircutter.core.model.repository.EstablishmentEmployeeRespository;
import br.com.haircutter.core.model.repository.EstablishmentRespository;
import br.com.haircutter.core.model.repository.EstablishmentServiceRespository;
import br.com.haircutter.core.model.repository.ScheduleRepository;
import br.com.haircutter.core.service.EstablishmentsQueryService;

@Service
public class EstablishmentsQueryServiceImpl implements EstablishmentsQueryService{

	@Autowired
    EstablishmentRespository establishmentRepository;
	
	@Autowired
	EstablishmentEmployeeRespository establishmentEmployeeReposiroy;
	
	@Autowired
	EstablishmentServiceRespository establishmentServiceRepository;
	
	@Autowired
	ScheduleRepository scheduleRepository;

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

	@Override
	public Establishment findByCnpj(String cnpj) {
		
		Establishment establishment = establishmentRepository.findOneByCnpjAndStatus(cnpj, EstablishmentStatusEnum.ACTIVE);
		
		//Add establishment employees to the establishment object
		List<EstablishmentEmployee> establishmentEmployees; 
		establishmentEmployees = establishmentEmployeeReposiroy.findAllByEstablishmentCnpjAndDeleted(establishment.getCnpj(), false);
		establishment.setEstablishmentEmployees(establishmentEmployees);
		
		//Add establishment services to the establishment object
		List<EstablishmentService> establishmentServices;
		establishmentServices = establishmentServiceRepository.findAllByEstablishmentCnpjAndDeleted(establishment.getCnpj(), false);
		establishment.setEstablishmentServices(establishmentServices);
		
		//Add establishment schedules to the establishment object
		List<Schedule> establishmentSchedules = new ArrayList<Schedule>();
		for(EstablishmentEmployee establishmentEmployee : establishmentEmployees){
			List<Schedule> schedules = scheduleRepository.findAllByUsernameAndStatus(establishmentEmployee.getUser().getUsername(), ScheduleStatusEnum.ACCEPTED); 
			establishmentSchedules.addAll(schedules);
		}
		establishment.setEstablishmentSchedules(establishmentSchedules);
		
		return establishment;
	}
}
