package com.agenda.client;

import org.springframework.data.repository.CrudRepository;
import com.agenda.client.Domain.*;

public interface EventRepository extends CrudRepository<Event, Long> {
	public Event findByCreatorId(Long id);
}