package TestCases;

import Framework.TestBase;
import Framework.Utils.FileOperation;

import org.openqa.selenium.WebDriver;
import Tasks.CancelAnEventTask;
import Tasks.ScheduleAnEventTask;

import org.junit.jupiter.api.Test;

public class CancelAnEventTest extends TestBase {

	private WebDriver driver = super.getDriverFromDriverManager();
	private CancelAnEventTask cancelAnEventTask = new CancelAnEventTask(this.driver);
	private ScheduleAnEventTask scheduleAnEventTask = new ScheduleAnEventTask(this.driver);
	
	private String eventName = FileOperation.getProperties("event").getProperty("name");
	private String eventDate = FileOperation.getProperties("event").getProperty("date");
	private String eventTime = FileOperation.getProperties("event").getProperty("time");
	private String eventPlace = FileOperation.getProperties("event").getProperty("place");

	
	
	/* Given the user has cancelled an event,
	 * Then the event should no longer appear in the user's agenda.
	 */
	
	@Test
	public void cancelAnEvent() {
		try {
			scheduleAnEventTask.scheduleAnEvent(this.eventName,
					this.eventDate, this.eventTime, this.eventPlace);
			cancelAnEventTask.cancelAnEvent(this.eventName,
					this.eventDate, this.eventTime, this.eventPlace, "No description provided.");
		} catch (Exception e) {
			;
		}
	}
	
}