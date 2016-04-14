package br.com.haircutter.core.service;

import br.com.haircutter.core.model.Establishment;

/**
 * Created by hfaria on 31/03/16.
 */
public interface EstablishmentService {

    Establishment get(String cnpj);

    void edit(Establishment establishment, String username);

}
