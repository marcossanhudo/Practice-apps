import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.RestController;
import Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

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

	@PostMapping("/schedule-event")
	public void scheduleEvent(@RequestAttribute("name") String name, @RequestAttribute("description") String description,
		@RequestAttribute("dateTime") LocalDateTime dateTime, @RequestAttribute("place") String place,
		@RequestAttribute("creatorId") Long creatorId) {
		try { 
			eventRepository.save(new Event(new Long(1))
				.name(name)
				.description(description)
				.date(dateTime.toLocalDate())
				.time(dateTime.toLocalTime())
				.place(place)
				.creatorId(creatorId));
		} catch (Exception e) {
			;	
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