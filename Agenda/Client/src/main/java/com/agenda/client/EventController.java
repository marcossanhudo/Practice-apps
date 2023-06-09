package com.agenda.client;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;
import com.agenda.client.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class EventController {
	
	@Autowired
	private EventRepository eventRepository;

	@GetMapping("/user/{userId}/events")
	@ResponseBody
	public ArrayList<Map<String, String>> getUsersEvents(@PathVariable("userId") Long userId) {
		ArrayList<Map<String, String>> json = new ArrayList<>();
		
		ArrayList<Event> usersEvents = eventRepository.findByCreatorId(userId);
		for (Event usersEvent: usersEvents)
			json.add(usersEvent.toJSON());

		return json;
		// What about invites?
	}

	@GetMapping("/event/{eventId}")
	@ResponseBody
	public HashMap<String, String> getEvent(@PathVariable("eventId") Long eventId) {
		return eventRepository.findById(eventId).toJSON();
	}

	@PostMapping(path = "/schedule-event",
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public String scheduleEvent(@RequestParam("name") String name,
		@RequestParam("date") LocalDate date, @RequestParam("time") LocalTime time,
		@RequestParam("place") String place, @RequestParam("creatorId") Long creatorId) {
		try {
			Long id = new Long(eventRepository.repositorySize() + 1); 
			eventRepository.save(new Event(id)
				.name(name)
				.date(date)
				.time(time)
				.place(place)
				.creatorId(creatorId)
			);
			return "redirect:/home.html";
		} catch (Exception e) {
			throw e;
		}
	}

	@DeleteMapping("/cancel-event/{eventId}")
	@ResponseBody
	public HashMap<String, String> cancelEvent(@PathVariable("eventId") Long id) {
		return eventRepository.delete(id).toJSON();
	}

	@PatchMapping(path = "/reschedule-event",
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public String rescheduleEvent(@RequestParam("eventId") Long id,
		@RequestParam("date") LocalDate date, @RequestParam("time") LocalTime time,
		@RequestParam("place") String place) {
		try {
			Event event = eventRepository.findById(id);
			return eventRepository.save(event
				.date(date)
				.time(time)
				.place(place)
			).toJSON().toString();
		} catch (Exception e) {
			throw e;
		}
	}

	@PatchMapping(path = "/edit-event-details",
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public String editEventDetails(@RequestParam("eventId") Long id,
		@RequestParam("name") String name, @RequestParam("place") String place,
		@RequestParam("description") String description) {
		try {
			Event event = eventRepository.findById(id);
			return eventRepository.save(event
				.name(name)
				.place(place)
				.description(description)
			).toJSON().toString();
		} catch (Exception e) {
			throw e;
		}
	}

}