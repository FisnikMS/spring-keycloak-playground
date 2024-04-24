package com.fk.flashcards.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fk.flashcards.domain.Topic;
import com.fk.flashcards.repository.TopicRepository;

@Service
public class TopicService {

  @Autowired
  private TopicRepository topicRepo;

  public List<Topic> findAll() {
    return this.topicRepo.findAll();
  }

  public Optional<Topic> findById(Long id) {
    return this.topicRepo.findById(id);
  }

  public Topic save(Topic topic) {
    return this.topicRepo.save(topic);
  }

  public Topic updateTopic(Long topicId, Topic newTopic) {
    Topic topic = this.findById(topicId)
        .orElseThrow(() -> new IllegalArgumentException("Topic doesn't exist"));
    topic.setName(newTopic.getName());
    this.topicRepo.save(topic);
    return topic;
  }

  public Topic deleteTopic(Long topicId) {
    Topic topic = this.findById(topicId)
        .orElseThrow(() -> new IllegalArgumentException("Topic doesn't exist"));
    this.topicRepo.deleteById(topicId);
    return topic;
  }

}
