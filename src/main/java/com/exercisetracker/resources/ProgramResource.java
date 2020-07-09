package com.exercisetracker.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.exercisetracker.domain.Program;
import com.exercisetracker.services.ProgramService;

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
@RequestMapping(value = "/programs")
public class ProgramResource {

    @Autowired
	private ProgramService programService;
	

    // Informa que o endpoint desse método é id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		// PathVariable informa que esse id vai para a url

		Program obj = programService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Program>> findAll() {
		// PathVariable informa que esse id vai para a url

		List<Program> list = programService.findAll();
		
		return ResponseEntity.ok().body(list);
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Program obj) {
		obj = programService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) // RequestBody - transforma obj em json
	public ResponseEntity<Void> update(@Valid @RequestBody Program obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = programService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		programService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Program>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "active") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Program> list = programService.findPage(page, linesPerPage, orderBy, direction);

		return ResponseEntity.ok().body(list);
	}
    
}