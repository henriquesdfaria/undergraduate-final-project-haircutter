package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.model.UserProfile;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserProfileRespository extends CrudRepository<UserProfile, String> {
	
	UserProfile findOneByUsername(String username);

}
