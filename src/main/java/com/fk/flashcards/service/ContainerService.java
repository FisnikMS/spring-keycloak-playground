package com.fk.flashcards.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fk.flashcards.domain.Container;
import com.fk.flashcards.domain.Topic;
import com.fk.flashcards.domain.dto.UpdateContainerDTO;
import com.fk.flashcards.repository.ContainerRepository;
import com.fk.flashcards.repository.FlashcardRepository;
import com.fk.flashcards.repository.TopicRepository;
import com.fk.flashcards.domain.mapper.ContainerMapper;

@Service
public class ContainerService {

  @Autowired
  private ContainerRepository containerRepo;

  @Autowired
  private TopicRepository topicRepository;

  @Autowired
  private FlashcardRepository flashcardRepository;

  public List<Container> findAll() {
    return this.containerRepo.findAll();
  }

  public Optional<Container> findById(Long id) {
    return this.containerRepo.findById(id);
  }

  public Container save(Container container, Long topicId) {
    Topic topic = topicRepository.findById(topicId)
        .orElseThrow(() -> new IllegalArgumentException("Topic doesn't exist"));
    container.setTopic(topic);
    return this.containerRepo.save(container);
  }

  public UpdateContainerDTO update(UpdateContainerDTO container, Long id) {
    Optional<Topic> topic = Optional.empty();
    if (container.topic() != null) {
      topic = Optional.of(topicRepository.findById(container.topic())
          .orElseThrow(() -> new IllegalArgumentException("Topic doesn't exist")));
    }

    Container updateContainer = this.containerRepo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Topic doesn't exist"));
    updateContainer.setName(container.name());
    updateContainer.setSchedule(container.schedule());
    if (topic.isPresent()) {
      updateContainer.setTopic(topic.get());
    }
    this.containerRepo.save(updateContainer);
    return new ContainerMapper().apply(updateContainer);

  }

  public Container delete(Long id) {
    Container container = this.containerRepo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Topic doesn't exist"));
    if (container.getFlashcards().size() == 0) {
      this.flashcardRepository.deleteAll(container.getFlashcards());
    }
    this.containerRepo.delete(container);
    return container;

  }

}
