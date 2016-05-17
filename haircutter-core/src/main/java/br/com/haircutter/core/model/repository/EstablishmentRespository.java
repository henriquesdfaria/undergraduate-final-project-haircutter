package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.enums.EstablishmentStatusEnum;
import br.com.haircutter.core.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface EstablishmentRespository extends JpaRepository<Establishment, String> {

	Establishment findOneByCnpj(String cnpj);

	List<Establishment> findByStatus(EstablishmentStatusEnum status);

	List<Establishment> findByAddressCityAndStatus(String city, EstablishmentStatusEnum status);
}
