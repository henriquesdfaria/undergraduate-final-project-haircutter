package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.model.EstablishmentService;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface EstablishmentServiceRespository extends CrudRepository<EstablishmentService, Long> {
	
	EstablishmentService findOneByEstablishmentCnpjAndDeleted(String cnpj, Boolean deleted);

	EstablishmentService findOneByIdAndEstablishmentCnpjAndDeleted(Long id, String cnpj, Boolean deleted);

	List<EstablishmentService> findAllByEstablishmentCnpjAndDeleted(String cnpj, Boolean deleted);

}
