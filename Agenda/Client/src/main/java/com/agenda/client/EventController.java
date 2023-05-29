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
		@RequestParam("date") LocalDate date, @RequestParam ("time") LocalTime time,
		@RequestParam("place") String place) {
		try { 
			eventRepository.save(new Event(new Long(1))
				.name(name)
				.date(date)
				.time(time)
				.place(place)
				.creatorId(new Long(1)));
			return "redirect:/home.html";
		} catch (Exception e) {
			throw e;	
		}
	}

	/*@PostMapping("/reschedule-event")
	@ResponseBody
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