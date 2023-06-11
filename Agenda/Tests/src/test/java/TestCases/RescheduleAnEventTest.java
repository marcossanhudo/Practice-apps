package TestCases;

import Framework.TestBase;
import Framework.Utils.FileOperation;
import Tasks.*;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.Test;

public class RescheduleAnEventTest extends TestBase {
	
	private WebDriver driver = super.getDriverFromDriverManager();
	ScheduleAnEventTask scheduleAnEventTask = new ScheduleAnEventTask(driver);
	RescheduleAnEventTask rescheduleAnEventTask = new RescheduleAnEventTask(driver);
	
	private String eventName = FileOperation.getProperties("event").getProperty("name");
	private String eventDate = FileOperation.getProperties("event").getProperty("date");
	private String eventTime = FileOperation.getProperties("event").getProperty("time");
	private String eventPlace = FileOperation.getProperties("event").getProperty("place");

	private String eventNewDate = FileOperation.getProperties("event").getProperty("newDate");
	private String eventNewTime = FileOperation.getProperties("event").getProperty("newTime");
	private String eventNewPlace = FileOperation.getProperties("event").getProperty("newPlace");

	/* Given the user is trying to reschedule an event,
	 * And he has provided all of the required information,
	 * And he has also provided optional information,
	 * When the user confirms the reschedule,
	 * Then the event is moved to a new date or time, in the user's personal agenda,
	 * And the event's details in the event page are updated.
	 */

	@Test
	public void rescheduleEvent() throws Exception {
		try {
			scheduleAnEventTask.scheduleAnEvent(this.eventName,
					this.eventDate, this.eventTime, this.eventPlace);
			Thread.sleep(300);
			rescheduleAnEventTask.rescheduleAnEvent(this.eventName,
					this.eventDate, this.eventTime, this.eventPlace, "",
					this.eventNewDate, this.eventNewTime, this.eventNewPlace);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* Given the user is trying to reschedule an event,
	 * And he has not provided all of the required information,
	 * And even if he has provided optional information,
	 * Wehn the user confirms the reschedule,
	 * Then the event is not rescheduled.
	 */
	
	@Test
	public void rescheduleEvent_missingRequiredInfo() throws Exception {
		try {
			scheduleAnEventTask.scheduleAnEvent(this.eventName,
					this.eventDate, this.eventTime, this.eventPlace);
			Thread.sleep(300);
			rescheduleAnEventTask.rescheduleAnEvent_missingDate(this.eventName,
					this.eventDate, this.eventTime, this.eventPlace, "",
					this.eventNewTime, this.eventNewPlace);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* Given the user is trying to reschedule an event,
	 * And he has provided all of the required information,
	 * And even if he has not provided optional information,
	 * When the user confirms the reschedule,
	 * Then the event is moved to a new date or time, in the user's personal agenda,
	 * And the event's details in the event page are updated.
	 */
	
	@Test
	public void rescheduleEvent_missingOptionalInfo() throws Exception {
		try {
			scheduleAnEventTask.scheduleAnEvent(this.eventName,
					this.eventDate, this.eventTime, this.eventPlace);
			Thread.sleep(300);
			rescheduleAnEventTask.rescheduleAnEvent_missingPlace(this.eventName,
					this.eventDate, this.eventTime, this.eventPlace, "",
					this.eventNewDate, this.eventNewTime);
		} catch (Exception e) {
			throw e;
		}
	}
}