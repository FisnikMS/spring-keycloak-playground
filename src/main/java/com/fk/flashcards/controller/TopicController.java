package com.fk.flashcards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fk.flashcards.domain.Topic;
import com.fk.flashcards.service.TopicService;

@RestController
@RequestMapping("/topic")
public class TopicController {

  @Autowired
  private TopicService topicService;

  @PostMapping()
  @PreAuthorize("hasRole('flashcard.edit')")
  public Topic newTopic(@RequestBody Topic topic) {
    return topicService.save(topic);
  }

  @GetMapping()
  @PreAuthorize("hasRole('flashcard.read')")
  public ResponseEntity<List<Topic>> findAll() {
    return ResponseEntity.ok(topicService.findAll());
  }

  @PreAuthorize("hasRole('flashcard.edit')")
  @DeleteMapping("/{topic_id}")
  public ResponseEntity<Topic> deleteTopic(@PathVariable("topic_id") Long topicId) {
    return ResponseEntity.ok(this.topicService.deleteTopic(topicId));
  }

  @PutMapping("/{topic_id}")
  @PreAuthorize("hasRole('flashcard.edit')")
  public ResponseEntity<Topic> updateTopic(@PathVariable("topic_id") Long topic_id, @RequestBody Topic newTopic) {
    return ResponseEntity.ok(this.topicService.updateTopic(topic_id, newTopic));
  }

}
