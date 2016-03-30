package br.com.haircutter.core.model.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.haircutter.core.model.EstablishmentAuditLog;

@Transactional
public interface EstablishmentAuditLogRespository extends JpaRepository<EstablishmentAuditLog, Long> {

	public List<EstablishmentAuditLog> findByEstablishmentCnpj(String cnpj);
	
}
