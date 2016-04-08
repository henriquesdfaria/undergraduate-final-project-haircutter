package br.com.haircutter.admin.model.repository;

import br.com.haircutter.admin.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

	User findOneByUsername(String username);

}
