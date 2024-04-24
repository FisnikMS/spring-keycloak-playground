package com.fk.flashcards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fk.flashcards.domain.Container;
import com.fk.flashcards.domain.dto.UpdateContainerDTO;
import com.fk.flashcards.service.ContainerService;

@RestController
@RequestMapping("/container")
public class ContainerController {

	@Autowired
	ContainerService containerService;

	@GetMapping()
	private List<Container> findAll() {
		return containerService.findAll();
	}

	@PostMapping("/{topic_id}")
	private Container create(@RequestBody Container container, @PathVariable("topic_id") Long id) {
		return containerService.save(container, id);
	}

	@PutMapping("/{container_id}")
	private UpdateContainerDTO update(@RequestBody UpdateContainerDTO container, @PathVariable("container_id") Long id) {
		return containerService.update(container, id);
	}

	@DeleteMapping("/{container_id}")
	private Container delete(@PathVariable("container_id") Long id){
		return containerService.delete(id);
	}

}
