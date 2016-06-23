package br.com.haircutter.core.service.impl;

import br.com.haircutter.core.enums.EstablishmentStatusEnum;
import br.com.haircutter.core.enums.ScheduleStatusEnum;
import br.com.haircutter.core.enums.UserRoleEnum;
import br.com.haircutter.core.model.*;
import br.com.haircutter.core.model.repository.*;
import br.com.haircutter.core.service.EstablishmentsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstablishmentsQueryServiceImpl implements EstablishmentsQueryService{

	@Autowired
    EstablishmentRespository establishmentRepository;
	
	@Autowired
	EstablishmentEmployeeRespository establishmentEmployeeReposiroy;
	
	@Autowired
	EstablishmentServiceRespository establishmentServiceRepository;

	@Autowired
	ProfessionalServiceRepository professionalServiceRepository;

	@Autowired
	EstablishmentEvaluationRepository establishmentEvaluationRepository;
	
	@Autowired
	ScheduleRepository scheduleRepository;

	@Override
	public List<Establishment> findByCity(String city) {
		List<Establishment> establishments = establishmentRepository.findByAddressCityAndStatus(city, EstablishmentStatusEnum.ACTIVE);

		for(Establishment establishment : establishments){

            establishment.setProfessionalServices(new ArrayList<>());

			//Add establishment employees to the establishment object
			List<EstablishmentEmployee> establishmentEmployees = establishmentEmployeeReposiroy
                    .findAllByEstablishmentCnpjAndDeletedAndUserRole(establishment.getCnpj(), false, UserRoleEnum.ROLE_PROFESSIONAL);
			establishment.setEstablishmentEmployees(establishmentEmployees);

            establishmentEmployees.stream().forEach(ee -> {
                List<ProfessionalService> ps = professionalServiceRepository.findAllByEstablishmentEmployeeId(ee.getId());
                establishment.getProfessionalServices().addAll(ps);
            });

            establishment.getProfessionalServices().stream().forEach(ps -> {
                ps.setEstablishmentEmployee(establishmentEmployeeReposiroy.findOne(ps.getEstablishmentEmployeeId()));
                ps.setEstablishmentService(establishmentServiceRepository.findOne(ps.getEstablishmentServiceId()));
            });


            //Add establishment services to the establishment object
			List<EstablishmentService> establishmentServices;
			establishmentServices = establishmentServiceRepository.findAllByEstablishmentCnpjAndDeleted(establishment.getCnpj(), false);
			establishment.setEstablishmentServices(establishmentServices);

			establishment.setEvaluations(establishmentEvaluationRepository.findByEstablishmentCnpj(establishment.getCnpj()));

			Integer rating = 0;
			Integer count = 0;

			for (EstablishmentEvaluation evaluation : establishment.getEvaluations()) {
				count++;
				rating = rating + (evaluation.getRating() != null ? evaluation.getRating() : 1);
			}

			if (count > 0 && rating > 0) {
				establishment.setRatingMedium(rating/count);
			} else {
				establishment.setRatingMedium(0);
			}
		}
		
		return establishments;
	}

	@Override
	public Establishment findByCnpj(String cnpj) {
		
		Establishment establishment = establishmentRepository.findOneByCnpjAndStatus(cnpj, EstablishmentStatusEnum.ACTIVE);

        establishment.setProfessionalServices(new ArrayList<>());

		//Add establishment employees to the establishment object
		List<EstablishmentEmployee> establishmentEmployees; 
		establishmentEmployees = establishmentEmployeeReposiroy.findAllByEstablishmentCnpjAndDeletedAndUserRole(establishment.getCnpj(), false, UserRoleEnum.ROLE_PROFESSIONAL);
		establishment.setEstablishmentEmployees(establishmentEmployees);

        establishmentEmployees.stream().forEach(ee -> {
            List<ProfessionalService> ps = professionalServiceRepository.findAllByEstablishmentEmployeeId(ee.getId());
            establishment.getProfessionalServices().addAll(ps);
        });

        establishment.setProfessionalServices(establishment.getProfessionalServices().stream().distinct().collect(Collectors.toList()));

		//Add establishment services to the establishment object
		List<EstablishmentService> establishmentServices;
		establishmentServices = establishmentServiceRepository.findAllByEstablishmentCnpjAndDeleted(establishment.getCnpj(), false);
		establishment.setEstablishmentServices(establishmentServices);

        establishment.getProfessionalServices().stream().forEach(ps -> {
            ps.setEstablishmentEmployee(establishmentEmployeeReposiroy.findOne(ps.getEstablishmentEmployeeId()));
            ps.setEstablishmentService(establishmentServiceRepository.findOne(ps.getEstablishmentServiceId()));
        });

        //Add establishment schedules to the establishment object
		List<Schedule> establishmentSchedules = new ArrayList<>();
		for(EstablishmentEmployee establishmentEmployee : establishmentEmployees){
			List<Schedule> schedules = scheduleRepository.findAllByUsernameAndStatus(establishmentEmployee.getUser().getUsername(), ScheduleStatusEnum.ACCEPTED); 
			establishmentSchedules.addAll(schedules);
		}
		establishment.setEstablishmentSchedules(establishmentSchedules);


		establishment.setEvaluations(establishmentEvaluationRepository.findByEstablishmentCnpj(cnpj));

		Integer rating = 0;
		Integer count = 0;

		for (EstablishmentEvaluation evaluation : establishment.getEvaluations()) {
			count++;
			rating = rating + (evaluation.getRating() != null ? evaluation.getRating() : 1);
		}

		if (count > 0 && rating > 0) {
			establishment.setRatingMedium(rating/count);
		} else {
			establishment.setRatingMedium(0);
		}

		return establishment;
	}
}
