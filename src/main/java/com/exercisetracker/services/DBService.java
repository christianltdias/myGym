package com.exercisetracker.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.exercisetracker.domain.Category;
import com.exercisetracker.domain.Exercise;
import com.exercisetracker.domain.Muscle;
import com.exercisetracker.domain.Program;
import com.exercisetracker.domain.Serie;
import com.exercisetracker.domain.SerieItem;
import com.exercisetracker.domain.TimeItem;
import com.exercisetracker.domain.User;
import com.exercisetracker.domain.WeightItem;
import com.exercisetracker.domain.enums.UserType;
import com.exercisetracker.repositories.CategoryRepository;
import com.exercisetracker.repositories.ExerciseRepository;
import com.exercisetracker.repositories.MuscleRepository;
import com.exercisetracker.repositories.ProgramRepository;
import com.exercisetracker.repositories.SerieItemRepository;
import com.exercisetracker.repositories.SerieRepository;
import com.exercisetracker.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProgramRepository programRepository;
	
	@Autowired
	private SerieRepository serieRepository;

	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@Autowired
	private MuscleRepository muscleRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SerieItemRepository serieItemRepository;

	@Autowired
	private BCryptPasswordEncoder pe;


    public void instantiateTestDatabase() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		User user1 = new User(null, "Christian Leite Dias", "christian@gmail.com",pe.encode("123"), sdf.parse("05/09/1994"));
		user1.addProfile(UserType.ADMINISTRATOR);
		
		Program pro1 = new Program(null, "Main", sdf.parse("05/06/2020"), sdf.parse("05/12/2020"), true, user1);
		Program pro2 = new Program(null, "Second", sdf.parse("05/06/2019"), sdf.parse("05/12/2019"), false, user1);

		Serie se1 = new Serie(null, pro1, "A");
		Serie se2 = new Serie(null, pro1, "B");
		Serie se3 = new Serie(null, pro1, "C");
		Serie se4 = new Serie(null, pro2, "Monday");
		Serie se5 = new Serie(null, pro2, "Tuesday");

		Muscle m1 = new Muscle(null, "Supra");
		Muscle m2 = new Muscle(null, "Infra");
		Muscle m3 = new Muscle(null, "Oblíquo");
		Muscle m4 = new Muscle(null, "Bíceps");
		Muscle m5 = new Muscle(null, "Ombro");

		Category cat1 = new Category(null, "Abdômen");
		Category cat2 = new Category(null, "Braço");

		Exercise ex1 = new Exercise(null, "Halter alternado", "Exercicio pica");
		Exercise ex2 = new Exercise(null, "Abdominal", "Bem gostosin");
		Exercise ex3 = new Exercise(null, "Ombro", "nuuuuuuuu");

		ex1.getMuscles().addAll(Arrays.asList(m4,m5));
		ex2.getMuscles().addAll(Arrays.asList(m1,m2,m3));
		ex3.getMuscles().addAll(Arrays.asList(m3,m4,m5));

		m1.getExercises().addAll(Arrays.asList(ex2));
		m2.getExercises().addAll(Arrays.asList(ex2));
		m3.getExercises().addAll(Arrays.asList(ex2,ex3));
		m4.getExercises().addAll(Arrays.asList(ex1,ex3));
		m5.getExercises().addAll(Arrays.asList(ex1,ex3));

		m1.getCategories().addAll(Arrays.asList(cat1));
		m2.getCategories().addAll(Arrays.asList(cat1));
		m3.getCategories().addAll(Arrays.asList(cat1));
		m4.getCategories().addAll(Arrays.asList(cat2));
		m5.getCategories().addAll(Arrays.asList(cat2));

		cat1.getMuscles().addAll(Arrays.asList(m1,m2,m3));
		cat2.getMuscles().addAll(Arrays.asList(m4,m5));

		SerieItem it1 = new WeightItem(null, 3, ex1, se1, 15.0);
		SerieItem it2 = new WeightItem(null, 3, ex2, se1, 30.0);
		SerieItem it3 = new WeightItem(null, 3, ex3, se1, 20.0);
		SerieItem it4 = new WeightItem(null, 3, ex2, se2, 5.0);
		SerieItem it5 = new TimeItem(null, 3, ex1, se2, 10.0);
		SerieItem it6 = new TimeItem(null, 3, ex1, se4, 15.0);
		SerieItem it7 = new TimeItem(null, 3, ex2, se5, 20.0);
		SerieItem it8 = new TimeItem(null, 3, ex3, se5, 15.0);

		se1.getItens().addAll(Arrays.asList(it1,it2,it3));
		se2.getItens().addAll(Arrays.asList(it4,it5));
		se4.getItens().addAll(Arrays.asList(it6));
		se5.getItens().addAll(Arrays.asList(it7,it8));

		exerciseRepository.saveAll(Arrays.asList(ex1,ex2,ex3));
		muscleRepository.saveAll(Arrays.asList(m1,m2,m3,m4,m5));
		categoryRepository.saveAll(Arrays.asList(cat1,cat2));

		pro1.getSeries().addAll(Arrays.asList(se1,se2,se3));
		pro2.getSeries().addAll(Arrays.asList(se4,se5));

		user1.getPrograms().addAll(Arrays.asList(pro1, pro2));
		userRepository.saveAll(Arrays.asList(user1));

		programRepository.saveAll(Arrays.asList(pro1));

		serieRepository.saveAll(Arrays.asList(se1,se2,se3,se4,se5));
		serieItemRepository.saveAll(Arrays.asList(it1,it2,it3,it4,it5,it6,it7,it8));
	
    }
}