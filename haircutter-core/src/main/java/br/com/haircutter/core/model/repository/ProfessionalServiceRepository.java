package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.model.ProfessionalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessionalServiceRepository extends JpaRepository<ProfessionalService, Long>{
	
	List<ProfessionalService> findAllByEstablishmentEmployeeId(Long establishmentEmployeeId);
	ProfessionalService findOneByEstablishmentEmployeeId(Long establishmentEmployeeId);

}
