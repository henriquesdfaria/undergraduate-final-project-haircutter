package br.com.haircutter.admin.service;

import br.com.haircutter.admin.model.User;
import br.com.haircutter.admin.model.repository.UserRepository;
import br.com.haircutter.admin.utils.LoggedUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggedUserService {

	@Autowired
	private UserRepository userRepository;

	public User getLoggedUser() {

		User loggedUser = userRepository.findOneByUsername(LoggedUserUtils.getLoggedUserUsername());

		return loggedUser;
	}
}
