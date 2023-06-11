package TestCases;

import Framework.TestBase;
import Framework.Utils.FileOperation;

import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.Test;
import Tasks.ScheduleAnEventTask;
import Tasks.EditAnEventsDetailsTask;

public class EditAnEventsDetailsTest extends TestBase {
	
	private WebDriver driver = super.getDriverFromDriverManager();
	private ScheduleAnEventTask scheduleAnEventTask = new ScheduleAnEventTask(this.driver);
	private EditAnEventsDetailsTask editAnEventsDetailsTask = new EditAnEventsDetailsTask(this.driver);
	
	private String eventName = FileOperation.getProperties("event").getProperty("name");
	private String eventDate = FileOperation.getProperties("event").getProperty("date");
	private String eventTime = FileOperation.getProperties("event").getProperty("time");
	private String eventPlace = FileOperation.getProperties("event").getProperty("place");
	
	private String eventNewName = FileOperation.getProperties("event").getProperty("newName");
	private String eventNewDescription = FileOperation.getProperties("event").getProperty("newDescription");
	private String eventNewPlace = FileOperation.getProperties("event").getProperty("newPlace");
	
	/* Given the user is trying to edit an event's details,
	 * And the user has provided all of the required information,
	 * And the user has also provided optional information,
	 * When the user confirms the edit,
	 * Then the event's details are updated.
	 */
	
	@Test
	public void editEventDetails() throws Exception {
		try {
			scheduleAnEventTask.scheduleAnEvent(eventName, eventDate,
					eventTime, eventPlace);
			Thread.sleep(300);
			editAnEventsDetailsTask.editAnEventsDetails(eventName, eventDate,
					eventTime, eventPlace, "",
					eventNewName, eventNewDescription, eventNewPlace);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* Given the user is trying to edit an event's details,
	 * And the user has not provided all of the required information,
	 * And even if the user has also provided optional information,
	 * When the user confirms the edit,
	 * Then the event's details are not updated.
	 */
	
	@Test
	public void editEventDetails_missingRequiredInfo() throws Exception {
		try {
			scheduleAnEventTask.scheduleAnEvent(eventName, eventDate,
					eventTime, eventPlace);
			Thread.sleep(300);
			editAnEventsDetailsTask.editAnEventsDetails_missingName(eventName, eventDate,
					eventTime, eventPlace, "",
					eventNewDescription, eventNewPlace);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* Given the user is trying to edit an event's details,
	 * And the user has provided all of the required information,
	 * And even though the user has not provided optional information,
	 * When the user confirms the edit,
	 * Then the event's details are updated.
	 */
	
	@Test
	public void editEventDetails_missingOptionalInfo() throws Exception {
		try {
			scheduleAnEventTask.scheduleAnEvent(eventName, eventDate,
					eventTime, eventPlace);
			Thread.sleep(300);
			editAnEventsDetailsTask.editAnEventsDetails_missingDescription(eventName, eventDate,
					eventTime, eventPlace, "",
					eventNewName, eventPlace);
		} catch (Exception e) {
			throw e;
		}
	}
	
}