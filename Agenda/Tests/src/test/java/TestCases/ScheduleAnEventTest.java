package TestCases;

import Framework.TestBase;
import Framework.Utils.FileOperation;
import Tasks.*;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.Test;

public class ScheduleAnEventTest extends TestBase {
	private WebDriver driver = super.getDriverFromDriverManager();
	ScheduleAnEventTask scheduleAnEventTask = new ScheduleAnEventTask(driver);
	
	private String eventName = FileOperation.getProperties("event").getProperty("name");
	private String eventDate = FileOperation.getProperties("event").getProperty("date");
	private String eventTime = FileOperation.getProperties("event").getProperty("time");
	private String eventPlace = FileOperation.getProperties("event").getProperty("place");

	/* Given the user has provided all required information to create an event,
	 * And also has provided optional information,
	 * When the user confirms that he wants to schedule the event,
	 * Then the event should appear in the user's agenda,
	 * And the user should be notified of the success in scheduling the event.
	 */

	@Test
	public void scheduleEvent() throws Exception {
		try {
			scheduleAnEventTask.scheduleAnEvent(this.eventName,
					this.eventDate, this.eventTime, this.eventPlace);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* Given the user has not provided all required information to create an event,
	 * And even if he has provided optional information,
	 * When the user confirms that he wants to schedule the event,
	 * Then the event should not appear in the user's agenda,
	 * And the user should be notified about the missing information.
	 */
	
	@Test
	public void scheduleEvent_missingRequiredInfo() {
		try {
			scheduleAnEventTask.scheduleAnEvent_missingName(eventDate, eventTime, eventPlace);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* Given the user has provided all required information to create an event,
	 * But has not provided any optional information,
	 * When the user confirms that he wants to schedule the event,
	 * Then the event should appear in the user's agenda,
	 * And the user should be notified of the success in scheduling the event.
	 */
	
	@Test
	public void scheduleEvent_missingOptionalInfo() throws Exception {
		try {
			scheduleAnEventTask.scheduleAnEvent_missingPlace(eventName, eventDate, eventTime);
		} catch (Exception e) {
			throw e;
		}
	}
	
}