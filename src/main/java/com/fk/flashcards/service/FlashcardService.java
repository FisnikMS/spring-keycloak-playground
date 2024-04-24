package com.fk.flashcards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fk.flashcards.domain.Container;
import com.fk.flashcards.domain.Flashcard;
import com.fk.flashcards.domain.dto.UpdateFlashcardDTO;
import com.fk.flashcards.repository.FlashcardRepository;
import com.fk.flashcards.repository.ContainerRepository;

import java.util.List;

@Service
public class FlashcardService {

  @Autowired
  private FlashcardRepository flashcardRepository;

  @Autowired
  private ContainerRepository ContainerRepository;

  public Flashcard save(Flashcard flashcard, Long id) {
    Container container = this.ContainerRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Container doesn't exist"));
    flashcard.setContainer(container);
    return this.flashcardRepository.save(flashcard);
  }

  public Flashcard delete(Long id) {
    Flashcard flashcard = this.flashcardRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Flashcard doesn't exist"));
    this.flashcardRepository.delete(flashcard);
    return flashcard;
  }

  public Flashcard update(UpdateFlashcardDTO updateFlashcard, Long id) {
    Flashcard flashcard = this.flashcardRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Flashcard doesn't exist"));
    flashcard.setBackside(updateFlashcard.backside());
    flashcard.setFrontside(updateFlashcard.frontside());
    if (updateFlashcard.container() != null) {
      Container container = this.ContainerRepository.findById(updateFlashcard.container())
          .orElseThrow(() -> new IllegalArgumentException("Container doesn't exist"));
      flashcard.setContainer(container);
    }
    this.flashcardRepository.save(flashcard);
    return flashcard;

  }

  public List<Flashcard> findAll() {
    return this.flashcardRepository.findAll();
  }

  public Flashcard findById(Long id) {
    Flashcard flashcard = this.flashcardRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Flashcard doesn't exist"));
    return flashcard;
  }

}
