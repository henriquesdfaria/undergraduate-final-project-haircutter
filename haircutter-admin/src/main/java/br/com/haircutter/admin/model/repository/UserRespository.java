package br.com.haircutter.admin.model.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.haircutter.admin.model.User;

@Transactional
public interface UserRespository extends CrudRepository<User, Long> {

	User findOneByUsername(String username);

}
