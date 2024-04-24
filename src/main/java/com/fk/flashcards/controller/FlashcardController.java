package com.fk.flashcards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fk.flashcards.domain.Flashcard;
import com.fk.flashcards.domain.dto.UpdateFlashcardDTO;
import com.fk.flashcards.service.FlashcardService;

@RestController
@RequestMapping("flashcard")
public class FlashcardController {

	@Autowired
	FlashcardService flashcardService;

	@GetMapping
	public List<Flashcard> findAll() {
		return flashcardService.findAll();
	}

	@PostMapping("{id}")
	public ResponseEntity<Flashcard> post(@RequestBody Flashcard flashcard,
			@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.flashcardService.save(flashcard, id));
	}

	@PutMapping("{id}")
	public ResponseEntity<Flashcard> update(@RequestBody UpdateFlashcardDTO updateDto,
			@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.flashcardService.update(updateDto, id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Flashcard> delete(
			@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.flashcardService.delete(id));
	}

}
