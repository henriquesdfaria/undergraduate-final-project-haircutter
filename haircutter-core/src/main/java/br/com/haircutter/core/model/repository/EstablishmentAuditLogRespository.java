package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.model.EstablishmentAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface EstablishmentAuditLogRespository extends JpaRepository<EstablishmentAuditLog, Long> {

	List<EstablishmentAuditLog> findByEstablishmentCnpj(String cnpj);
	
}
