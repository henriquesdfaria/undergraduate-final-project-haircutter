package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.enums.EstablishmentStatusEnum;
import br.com.haircutter.core.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface EstablishmentRespository extends JpaRepository<Establishment, String> {

	Establishment findOneByCnpj(String cnpj);

	List<Establishment> findByStatus(EstablishmentStatusEnum status);
	
	@Query("SELECT e "
			 + "FROM Establishment e "
			 + "WHERE e.address.city like :city "
			 + "AND   e.status = :status ")
	List<Establishment> findByCityAndStatus(@Param("city") String city, @Param("status") String status);
}
