package com.fk.flashcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fk.flashcards.domain.Topic;
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{ 
    
}
