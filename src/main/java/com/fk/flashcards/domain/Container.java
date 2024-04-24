package com.fk.flashcards.domain;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;


import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "container")
public class Container {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	private String name;
	private Date schedule;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "topic_id")
	private Topic topic;

	@OneToMany(mappedBy = "container", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Flashcard> flashcards = new ArrayList<>();

	public List<Flashcard> getFlashcards() {
		return flashcards;
	}

	public void setFlashcards(List<Flashcard> flashcards) {
		this.flashcards.clear();
		this.flashcards.addAll(flashcards);
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getSchedule() {
		return this.schedule;
	}

	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Container)) {
			return false;
		}
		Container container = (Container) o;
		return Objects.equals(id, container.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, schedule);
	}

	@Override
	public String toString() {
		return "{" +
				" id='" + this.id + "'" +
				", name='" + getName() + "'" +
				", schedule='" + getSchedule() + "'" +
				", flashcards='" + getFlashcards() + "'" +
				"}";
	}
}
