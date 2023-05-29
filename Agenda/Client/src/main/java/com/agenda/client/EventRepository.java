package com.agenda.client;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.agenda.client.Domain.*;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ArrayList;
//import java.util.Optional;

@Repository
public class EventRepository /*implements CrudRepository<Event, Long>*/ {

	HashMap<Long, Event> events = new HashMap();

	public ArrayList<Event> findByCreatorId(Long creatorId) {
		ArrayList<Event> foundEvents = new ArrayList<>();
		for (Entry<Long, Event> eventEntry: events.entrySet()) {
			Event event = eventEntry.getValue();
			if (event.creatorId().equals(creatorId))
				foundEvents.add(event);
		}
		return foundEvents;
	}

	//@Override
	public Event save(Event event) {
		events.put(event.id(), event);
		return events.get(event.id());
	}

	//@Override
	public Event findById(Long id) {
		return events.get(id);
	}

	public Event delete(Long id) {
		return events.remove(id);
	}

	//@Override
	public void deleteAll() {
		events.clear();
	}

}