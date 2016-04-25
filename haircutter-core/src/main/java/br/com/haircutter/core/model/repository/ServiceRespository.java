package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.model.Service;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ServiceRespository extends CrudRepository<Service, Long> {
	
	Service findOneByEstablishmentCnpj(String cnpj);

	List<Service> findAllByEstablishmentCnpj(String cnpj);

}
