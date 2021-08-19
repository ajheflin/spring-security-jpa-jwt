package io.ajh.userservice;

import io.ajh.userservice.domain.Role;
import io.ajh.userservice.domain.User;
import io.ajh.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.security.util.Password;
import sun.tools.jar.CommandLine;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "AJ Heflin", "ajheflin", "password123", new ArrayList<>()));
			userService.saveUser(new User(null, "Rachel Hester", "rachelhester", "password321", new ArrayList<>()));
			userService.saveUser(new User(null, "Dylan Franck", "dylanfranck", "password456", new ArrayList<>()));

			userService.addRoleToUser("ajheflin", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("ajheflin", "ROLE_ADMIN");
			userService.addRoleToUser("ajheflin", "ROLE_USER");
			userService.addRoleToUser("rachelhester", "ROLE_ADMIN");
			userService.addRoleToUser("dylanfranck", "ROLE_MANAGER");
		};
	}

}
