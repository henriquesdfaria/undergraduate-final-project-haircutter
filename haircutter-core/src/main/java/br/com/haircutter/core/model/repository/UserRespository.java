package br.com.haircutter.core.model.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.haircutter.core.model.User;

@Transactional
public interface UserRespository extends CrudRepository<User, Long> {
	
	User findOneByUsername(String username);

}
