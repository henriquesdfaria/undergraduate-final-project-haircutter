package br.com.haircutter.admin;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class MvcConfig extends WebMvcAutoConfigurationAdapter {

	private static final String APP = "app/";

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		// Public
		registerView("/", "public/index", registry);
		registerView("/moderator", "moderator/index", registry);
		registerView("/establishment-admin", "establishment-admin/index", registry);
		
		// Login
		registerView("/login", "public/login", registry);


	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/vendors/**/*").addResourceLocations("classpath:/templates/vendors/");
		registry.addResourceHandler("/public/**/*").addResourceLocations("classpath:/templates/app/public/");
		registry.addResourceHandler("/client/**/*").addResourceLocations("classpath:/templates/app/client/");
		registry.addResourceHandler("/establishment-admin/**/*").addResourceLocations("classpath:/templates/app/establishment-admin/");
		registry.addResourceHandler("/establishment-attendant/**/*").addResourceLocations("classpath:/templates/app/establishment-attendant/");
		registry.addResourceHandler("/establishment-manager/**/*").addResourceLocations("classpath:/templates/app/establishment-manager/");
		registry.addResourceHandler("/establishment-professional/**/*").addResourceLocations("classpath:/templates/app/establishment-professional/");
		registry.addResourceHandler("/moderator/**/*").addResourceLocations("classpath:/templates/app/moderator/");
	}

	private void registerView(final String url, final String page, final ViewControllerRegistry registry) {
		registry.addViewController(url).setViewName(APP + page);
	}

}
