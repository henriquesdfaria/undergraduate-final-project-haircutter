package br.com.haircutter.core.model.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.haircutter.core.model.UserRole;

@Transactional
public interface UserRoleRespository extends CrudRepository<UserRole, Long> {

}
