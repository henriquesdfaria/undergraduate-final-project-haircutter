package br.com.haircutter.admin.model.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.haircutter.admin.model.UserRole;

@Transactional
public interface UserRoleRespository extends CrudRepository<UserRole, Long> {

}
