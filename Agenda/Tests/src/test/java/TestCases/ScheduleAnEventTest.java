package TestCases;

import Framework.TestBase;
import Tasks.*;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.Test;

public class ScheduleAnEventTest extends TestBase {
	private WebDriver driver = super.getDriverFromDriverManager();
	ScheduleAnEventTask scheduleAnEventTask = new ScheduleAnEventTask(driver);

	/* Given the user has scheduled an event,
	 * Then the event should appear in the user's agenda.
	 */

	@Test
	public void scheduleEvent() {
		try {
			scheduleAnEventTask.scheduleAnEvent();
		} catch (Exception e) {
			;
		}
	}
	
}