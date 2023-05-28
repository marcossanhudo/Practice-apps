import org.springframework.data.repository.CrudRepository;
import Domain.*;

public interface EventRepository extends CrudRepository<Event, Long> {
	public Event findByCreatorId(Long id);
}