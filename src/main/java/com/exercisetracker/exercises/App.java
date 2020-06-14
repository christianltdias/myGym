package com.exercisetracker.exercises;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import repositories.UserRepository;

@SpringBootApplication
public class App  implements CommandLineRunner {

	@Autowired
    private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("Os nomes irão vir aqui: teste");
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		User user1 = new User(null, "Christian Leite Dias", "christianltdias@gmail.com", sdf.parse("05/09/1994"));
		userRepository.saveAll(Arrays.asList(user1));
		
        System.out.println("fsa fas fsa fsa fsf sdf sdf dsf sd fds fsd f dsf dsf ds fsd fsdf sdf sd");

	}

}
