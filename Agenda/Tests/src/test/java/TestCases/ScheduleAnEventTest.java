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

	/* Given the user has scheduled an event,
	 * Then the event should appear in the user's agenda.
	 */

	@Test
	public void scheduleEvent() {
		try {
			scheduleAnEventTask.scheduleAnEvent(this.eventName,
					this.eventDate, this.eventTime, this.eventPlace);
		} catch (Exception e) {
			;
		}
	}
	
}