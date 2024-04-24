package com.fk.flashcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fk.flashcards.domain.Container;
@Repository
public interface ContainerRepository extends JpaRepository<Container, Long>{ 
    
}
