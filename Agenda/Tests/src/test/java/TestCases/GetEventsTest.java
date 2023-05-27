package TestCases;

import Framework.TestBase;
import Tasks.*;

public class GetEventsTest extends TestBase {
	
	/* Given the user has events in his agenda,
	 * When an event is happening within 7 days from the user's time,
	 * Then the event should appear in the user's agenda, under the "Happening soon" section.
	 */

	/* Given the user has events in his agenda,
	 * When an event is happening in 7 days, or later, from the user's time,
	 * Then the event should appear in the user's agenda, under the "Happening later" section.
	 */

	public void loadEvents() {
		try {
			//loadPageTask.fetchEvents();
		} catch (Exception e) {
			;
		}
	}

}