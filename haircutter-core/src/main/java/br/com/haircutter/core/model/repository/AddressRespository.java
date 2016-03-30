package br.com.haircutter.core.model.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.haircutter.core.model.Address;

@Transactional
public interface AddressRespository extends CrudRepository<Address, Long> {

}
