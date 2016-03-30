package br.com.haircutter.admin.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.haircutter.admin.enums.UserRoleEnum;

public class LoggedUserUtils {

	public static String getLoggedUserUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication.getName();
	}

	public static UserRoleEnum getLoggedUserRole() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return UserRoleEnum.valueOf(authentication.getAuthorities().toArray()[0].toString());
	}

}
