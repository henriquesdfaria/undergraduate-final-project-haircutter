package br.com.haircutter.core.service;

import br.com.haircutter.core.model.EstablishmentAuditLog;

import java.util.List;

public interface EstablishmentAuditLogService {

	List<EstablishmentAuditLog> getAuditLogsByCnpj(String cnpj);

}
