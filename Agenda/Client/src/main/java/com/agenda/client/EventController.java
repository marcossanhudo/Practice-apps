package com.agenda.client;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.web.bind.annotation.RestController;
import com.agenda.client.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EventController {
	
	@Autowired
	private EventRepository eventRepository;

	@GetMapping("/user/{userId}/events")
	public String getUsersEvents(@PathVariable("userId") Long userId) {
		return eventRepository.findByCreatorId(userId).toString();
		// What about invites?
	}

	@GetMapping("/event/{eventId}")
	public String getEvent(@PathVariable("eventId") Long eventId) {
		return eventRepository.findById(eventId).toString();
	}

	@PostMapping(path = "/schedule-event", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE) //, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	/* public void scheduleEvent(@RequestAttribute("name") String name, @RequestAttribute("description") String description,
		@RequestAttribute("dateTime") LocalDateTime dateTime, @RequestAttribute("place") String place,
		@RequestAttribute("creatorId") Long creatorId) {*/
	/* public String scheduleEvent(@RequestBody Event event) { */
	public String scheduleEvent(@RequestParam("name") String name, @RequestParam("date") LocalDate date, @RequestParam ("time") LocalTime time, @RequestParam("place") String place) {
		try { 
			eventRepository.save(new Event(new Long(1))
				.name(name)
				.date(date)
				.time(time)
				.place(place));
			return "redirect:/home.html";
		} catch (Exception e) {
			throw e;	
		}
	}

	/*@PostMapping("/reschedule-event")
	public void rescheduleEvent(@RequestAttribute("eventId") Long id, @RequestAttribute("dateTime") LocalDateTime dateTime) {
		try {
			Event event = eventRepository.findById(id);
			eventRepository.save(event
				.date(dateTime.toLocalDate())
				.time(dateTime.toLocalTime()));
		} catch (Exception e) {
			;
		}
	}*/

}