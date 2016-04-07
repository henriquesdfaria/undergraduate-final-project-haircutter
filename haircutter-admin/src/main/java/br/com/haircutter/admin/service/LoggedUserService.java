package br.com.haircutter.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.haircutter.admin.model.User;
import br.com.haircutter.admin.model.repository.UserRespository;
import br.com.haircutter.admin.utils.LoggedUserUtils;

@Service
public class LoggedUserService {

	@Autowired
	private UserRespository userRepository;

	public User getLoggedUser() {

		User loggedUser = userRepository.findOneByUsername(LoggedUserUtils.getLoggedUserUsername());

		return loggedUser;
	}
}
