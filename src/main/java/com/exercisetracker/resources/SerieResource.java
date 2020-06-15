package com.exercisetracker.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.exercisetracker.domain.Serie;
import com.exercisetracker.services.SerieService;

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
@RequestMapping(value = "/series")
public class SerieResource {

    @Autowired
    private SerieService serieService;

    // Informa que o endpoint desse método é id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		// PathVariable informa que esse id vai para a url

		Serie obj = serieService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Serie>> findAll() {
		// PathVariable informa que esse id vai para a url

		List<Serie> list = serieService.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Serie obj) {
		obj = serieService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) // RequestBody - transforma obj em json
	public ResponseEntity<Void> update(@Valid @RequestBody Serie obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = serieService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		serieService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Serie>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "tag") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Serie> list = serieService.findPage(page, linesPerPage, orderBy, direction);

		return ResponseEntity.ok().body(list);
	}
}