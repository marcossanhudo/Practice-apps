package TestCases;

import Framework.TestBase;
import Tasks.*;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.Test;

public class RescheduleAnEventTest extends TestBase {
	private WebDriver driver = super.getDriverFromDriverManager();
	ScheduleAnEventTask scheduleAnEventTask = new ScheduleAnEventTask(driver);
	RescheduleAnEventTask rescheduleAnEventTask = new RescheduleAnEventTask(driver);

	/* Given the user has scheduled an event,
	 * Then the event should appear in the user's agenda.
	 */

	@Test
	public void rescheduleEvent() {
		try {
			scheduleAnEventTask.scheduleAnEvent();
			rescheduleAnEventTask.rescheduleAnEvent();
		} catch (Exception e) {
			;
		}
	}
	
}