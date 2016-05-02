package br.com.haircutter.core.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.haircutter.core.model.ProfessionalService;

public interface ProfessionalServiceRepository extends JpaRepository<ProfessionalService, Long>{
	
	List<ProfessionalService> findAllByEstablishmentEmployeeId(Long establishmentEmployeeId);
	
}
