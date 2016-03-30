package br.com.haircutter.admin.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.haircutter.admin.enums.UserRoleEnum;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static String USERS_BY_USERNAME_QUERY = "SELECT username, password, enabled FROM user WHERE username=?";
	private static String AUTHORITIES_USERS_BY_USERNAME_QUERY = "SELECT username, role FROM user_role WHERE username=?";

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				// Public
				.antMatchers("/vendors/**", "/", "/public/**", "/api/public/**" ).permitAll()
				// Moderator
				.antMatchers("/moderator/**", "/api/moderator/**")
					.hasAnyAuthority(UserRoleEnum.ROLE_MODERATOR.toString())
				// Establishment Admin
				.antMatchers("/establishment-admin/**", "/api/establishment-admin/**")
					.hasAnyAuthority(UserRoleEnum.ROLE_ESTABLISHMENT_ADMIN.toString())
				.anyRequest()
					.authenticated()
				.and()
			.csrf()
				.disable()
			.formLogin()
				.loginPage("/login").permitAll()
				.and()
			.logout()
				.permitAll();
	}

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(USERS_BY_USERNAME_QUERY)
				.authoritiesByUsernameQuery(AUTHORITIES_USERS_BY_USERNAME_QUERY);
	}

}