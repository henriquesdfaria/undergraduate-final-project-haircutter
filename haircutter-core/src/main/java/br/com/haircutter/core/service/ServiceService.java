package br.com.haircutter.core.service;

import br.com.haircutter.core.model.Service;

import java.util.List;

/**
 * Created by hfaria on 4/24/16.
 */
public interface ServiceService {

    Service create(Service service);

    List<Service> getAllByCnpj(String cnpj);

    void edit(Service service);

    void delete(Long id, String cnpj, String username);

    Service getByIdAndCnpj(Long id, String cnpj);
}
