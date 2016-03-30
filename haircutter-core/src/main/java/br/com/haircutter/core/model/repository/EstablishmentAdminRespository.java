package br.com.haircutter.core.model.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.haircutter.core.model.EstablishmentAdmin;

@Transactional
public interface EstablishmentAdminRespository extends JpaRepository<EstablishmentAdmin, Long> {


}
