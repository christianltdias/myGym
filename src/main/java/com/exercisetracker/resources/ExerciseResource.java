package com.exercisetracker.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.exercisetracker.domain.Exercise;
import com.exercisetracker.services.ExerciseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/exercises")
public class ExerciseResource {

    @Autowired
    private ExerciseService exerciseService;

    // Informa que o endpoint desse método é id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		// PathVariable informa que esse id vai para a url

		Exercise obj = exerciseService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Exercise>> findAll() {
		// PathVariable informa que esse id vai para a url

		List<Exercise> list = exerciseService.findAll();
		
		return ResponseEntity.ok().body(list);
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Exercise obj) {
		obj = exerciseService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) // RequestBody - transforma obj em json
	public ResponseEntity<Void> update(@Valid @RequestBody Exercise obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = exerciseService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		exerciseService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Exercise>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Exercise> list = exerciseService.findPage(page, linesPerPage, orderBy, direction);

		return ResponseEntity.ok().body(list);
	}
    
}