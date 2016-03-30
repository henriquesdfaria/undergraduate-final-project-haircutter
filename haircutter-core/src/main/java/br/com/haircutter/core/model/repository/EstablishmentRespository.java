package br.com.haircutter.core.model.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.haircutter.core.enums.EstablishmentStatusEnum;
import br.com.haircutter.core.model.Establishment;

@Transactional
public interface EstablishmentRespository extends JpaRepository<Establishment, String> {

	Establishment findOneByCnpj(String cnpj);
	
	public List<Establishment> findByStatus(EstablishmentStatusEnum status);

}
