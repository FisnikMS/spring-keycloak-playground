package com.fk.flashcards.domain.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.fk.flashcards.domain.Container;
import com.fk.flashcards.domain.dto.UpdateContainerDTO;

@Service
public class ContainerMapper implements Function<Container, UpdateContainerDTO> {

	@Override
	public UpdateContainerDTO apply(Container t) {
		return new UpdateContainerDTO(t.getId(), t.getName(), t.getSchedule(), t.getTopic().getId());
	}

}
