package com.fk.flashcards.domain.dto;

public record UpdateFlashcardDTO(
		Long id,
		String frontside,
		String backside,
		Long container) {
}
