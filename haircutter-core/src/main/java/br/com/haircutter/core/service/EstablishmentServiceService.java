package br.com.haircutter.core.service;

import br.com.haircutter.core.model.EstablishmentService;

import java.util.List;

/**
 * Created by hfaria on 4/24/16.
 */
public interface EstablishmentServiceService {

    EstablishmentService create(EstablishmentService establishmentService, String username);

    List<EstablishmentService> getAllByCnpj(String cnpj);

    void edit(EstablishmentService establishmentService, String username, String cnpj);

    void delete(Long id, String cnpj, String username);

    EstablishmentService getByIdAndCnpj(Long id, String cnpj);
}
