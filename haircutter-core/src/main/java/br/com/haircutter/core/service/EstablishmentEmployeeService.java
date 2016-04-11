package br.com.haircutter.core.service;

import br.com.haircutter.core.model.EstablishmentEmployee;

import java.util.List;

public interface EstablishmentEmployeeService {

    EstablishmentEmployee get(Long id);

    List<EstablishmentEmployee> getAllByEstablishment(String cnpj);

    EstablishmentEmployee create(EstablishmentEmployee establishmentEmployee, String username);

    void edit(EstablishmentEmployee establishmentEmployee, String username);

    void delete(Long id, String username);
}
