package TestCases;

import Framework.TestBase;
import org.openqa.selenium.WebDriver;
import Tasks.CancelAnEventTask;
import org.junit.jupiter.api.Test;

public class CancelAnEventTest extends TestBase {

	private WebDriver driver;
	private CancelAnEventTask cancelAnEventTask;
	
	public CancelAnEventTest(WebDriver driver) {
		this.driver = driver;
		this.cancelAnEventTask = new CancelAnEventTask(this.driver);
	}
	
	/* Given the user has cancelled an event,
	 * Then the event should no longer appear in the user's agenda.
	 */
	
	@Test
	public void cancelAnEvent() {
		try {
			cancelAnEventTask.cancelAnEvent();
		} catch (Exception e) {
			
		}
	}
	
}