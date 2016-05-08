package br.com.haircutter.core.service;

import br.com.haircutter.core.enums.UserRoleEnum;
import br.com.haircutter.core.model.User;

public interface UserService {
	
	User create(User user, UserRoleEnum role);

	void edit(User user);

	void delete(String username);

	User get(String username);

}
