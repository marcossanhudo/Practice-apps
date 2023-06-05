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

	/* Given the user has scheduled an event,
	 * Then the event should appear in the user's agenda.
	 */

	@Test
	public void rescheduleEvent() throws Exception {
		try {
			scheduleAnEventTask.scheduleAnEvent(this.eventName,
					this.eventDate, this.eventTime, this.eventPlace);
			Thread.sleep(300);
			rescheduleAnEventTask.rescheduleAnEvent(this.eventName,
					this.eventDate, this.eventTime, this.eventPlace, "No description provided.",
					this.eventNewDate, this.eventNewTime, this.eventNewPlace);
		} catch (Exception e) {
			throw e;
		}
	}
	
}