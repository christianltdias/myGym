package com.exercisetracker;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.exercisetracker.domain.User;
import com.exercisetracker.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App  implements CommandLineRunner {


	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		User user1 = new User(null, "Christian Leite Dias", "christianltdias@gmail.com", sdf.parse("05/09/1994"));
		userRepository.saveAll(Arrays.asList(user1));

	}

}
