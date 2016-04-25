package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.model.EstablishmentService;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface EstablishmentServiceRespository extends CrudRepository<EstablishmentService, Long> {
	
	EstablishmentService findOneByEstablishmentCnpj(String cnpj);

	EstablishmentService findOneByIdAndEstablishmentCnpj(Long id, String cnpj);

	List<EstablishmentService> findAllByEstablishmentCnpj(String cnpj);

}
