package br.com.haircutter.core.service;

import java.util.List;

import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.model.EstablishmentAuditLog;

public interface EstablishmentService {

    Establishment createNewRequest(final Establishment establishment);
    
    List<Establishment> getCreationRequests();
    
    Establishment getCreationRequestByCnpj(String cnpj);
    
    Establishment approveCreationRequest(String cnpj);
    
    Establishment denyCreationRequest(String cnpj);

	List<EstablishmentAuditLog> getAuditLogsByCnpj(String cnpj);

}
