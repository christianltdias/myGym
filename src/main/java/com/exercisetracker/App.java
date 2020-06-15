package com.exercisetracker;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.exercisetracker.domain.Program;
import com.exercisetracker.domain.Serie;
import com.exercisetracker.domain.User;
import com.exercisetracker.domain.enums.UserType;
import com.exercisetracker.repositories.ProgramRepository;
import com.exercisetracker.repositories.SerieRepository;
import com.exercisetracker.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProgramRepository programRepository;
	
	@Autowired
	private SerieRepository serieRepository;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		User user1 = new User(null, "Christian Leite Dias", "christianltdias@gmail.com", sdf.parse("05/09/1994"),
				UserType.ADMINISTRATOR);

		Program pro1 = new Program(null, "Main", sdf.parse("05/06/2020"), sdf.parse("05/12/2020"), true, user1);
		Program pro2 = new Program(null, "Main", sdf.parse("05/06/2019"), sdf.parse("05/12/2019"), false, user1);

		Serie se1 = new Serie(null, pro1, "A");
		Serie se2 = new Serie(null, pro1, "B");
		Serie se3 = new Serie(null, pro1, "C");
		Serie se4 = new Serie(null, pro2, "Monday");
		Serie se5 = new Serie(null, pro2, "Tuesday");


		pro1.getSeries().addAll(Arrays.asList(se1,se2,se3));
		pro2.getSeries().addAll(Arrays.asList(se4,se5));

		user1.getPrograms().addAll(Arrays.asList(pro1, pro2));
		userRepository.saveAll(Arrays.asList(user1));

		programRepository.saveAll(Arrays.asList(pro1));

		serieRepository.saveAll(Arrays.asList(se1,se2,se3,se4,se5));


	}

}
