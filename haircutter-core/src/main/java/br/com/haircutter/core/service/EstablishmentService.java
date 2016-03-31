package br.com.haircutter.core.service;

import br.com.haircutter.core.model.Establishment;

import java.util.List;

public interface EstablishmentService {

    Establishment createNewRequest(final Establishment establishment);

    List<Establishment> getCreationRequests();

    Establishment getCreationRequestByCnpj(String cnpj);

    void approveCreationRequest(String cnpj);

    void denyCreationRequest(String cnpj);

}
