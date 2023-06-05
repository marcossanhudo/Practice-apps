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
	
}