package com.example.fellon_crm;

import com.example.fellon_crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FellonCrmApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(FellonCrmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Создаём пару пользователей
		userService.createUser("User", "user@user.com", "Password");

	}
}
