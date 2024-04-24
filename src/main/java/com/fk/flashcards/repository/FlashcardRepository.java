package com.fk.flashcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fk.flashcards.domain.Flashcard;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {

}
