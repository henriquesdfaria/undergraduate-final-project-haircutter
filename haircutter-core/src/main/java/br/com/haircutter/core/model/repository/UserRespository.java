package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRespository extends CrudRepository<User, String> {
	
	User findOneByUsername(String username);

}
