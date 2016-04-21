package br.com.haircutter.core.service;

import br.com.haircutter.core.model.Establishment;

import java.util.List;

public interface EstablishmentCreationRequestService {

    Establishment create(final Establishment establishment);

    List<Establishment> getAll();

    void approve(String cnpj);

    void deny(Establishment establishment);

}
