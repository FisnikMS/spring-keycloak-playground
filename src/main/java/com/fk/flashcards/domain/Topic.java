package com.fk.flashcards.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "topic")
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(unique = true)
	private String name;
	@OneToMany(mappedBy = "topic", fetch = FetchType.LAZY,  orphanRemoval = false)
	private List<Container> containers = new ArrayList<Container>();

	public List<Container> getContainers() {
		return containers;
	}

	public void setContainers(List<Container> containers) {
		this.containers.clear();
		containers.forEach(c -> this.addContainer(c));

	}

	public void setContent(Topic topic) {
		this.setName(topic.getName());
		this.setContainers(topic.getContainers());
	}

	// @JdbcTypeCode(SqlTypes.JSON)
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "ID")
	// private Map<Long,Tag> tags = new HashMap<Long,Tag>();
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public boolean addContainer(Container container) {
		return this.containers.add(container);
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", name=" + name + ", containers=" + containers + "]";
	}

	// public Topic(String name, List<Container> containers, Set<Tag> tags) {
	// this.name = name;
	// this.containers = containers;
	// this.tags = tags;
	// }
	//
	
	 


}
