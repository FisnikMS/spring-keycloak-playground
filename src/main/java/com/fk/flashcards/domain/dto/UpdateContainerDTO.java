package com.fk.flashcards.domain.dto;

import java.util.Date;

public record UpdateContainerDTO(
	Long id,
	String name,
	Date schedule,
	Long topic 
) {
}
