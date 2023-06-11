package Tasks;

import PageObjects.AgendaPage;
import PageObjects.ScheduleEventForm;
import Validations.AgendaValidation;
import Validations.ScheduleFormValidation;
import org.openqa.selenium.WebDriver;

public class ScheduleAnEventTask {

	private WebDriver driver;
	private AgendaPage agendaPage;
	private AgendaValidation agendaValidation;
	
	public ScheduleAnEventTask(WebDriver driver) {
		this.driver = driver;
		this.agendaPage = new AgendaPage(this.driver);
		this.agendaValidation = new AgendaValidation(this.driver);
	}

	/* Given the user is trying to schedule an event,
	 * And the user has provided a valid name, a valid date, a valid time, and a valid place,
	 * When the user confirms the scheduling,
	 * Then the event is added to the user's agenda.
	 */

	public void scheduleAnEvent(String name, String date, String time,
			String place) {
		agendaValidation.validatePageTitleAvailability();
		agendaPage.getScheduleAnEventButton().click();
		agendaValidation.validateFormAvailability();

		ScheduleEventForm scheduleEventForm = new ScheduleEventForm(this.driver);
		scheduleEventForm.getNameInput().sendKeys(name);
		scheduleEventForm.getDateInput().sendKeys(date);
		scheduleEventForm.getTimeInput().sendKeys(time);
		scheduleEventForm.getPlaceInput().sendKeys(place);
		scheduleEventForm.getScheduleEventButton().click();
		agendaValidation.validateFormUnavailability();

		//agendaValidation.validateEventListing(name);
	}

	/* Given the user is trying to schedule an event,
	 * And the user has provided blank input for the event name,
	 * And even though the user has provided a valid date, a valid time, and a valid place,
	 * When the user confirms the scheduling,
	 * Then the event is not added to the user's agenda.
	 */

	public void scheduleAnEvent_missingName(String date, String time, String place) {
		agendaPage.getScheduleAnEventButton().click();
		agendaValidation.validateFormAvailability();

		ScheduleEventForm scheduleEventForm = new ScheduleEventForm(this.driver);
		ScheduleFormValidation scheduleFormValidation = new ScheduleFormValidation(this.driver);
		// Not doing scheduleEventForm.getNameInput().sendKeys(name);
		scheduleEventForm.getDateInput().sendKeys(date);
		scheduleEventForm.getTimeInput().sendKeys(time);
		scheduleEventForm.getPlaceInput().sendKeys(place);
		scheduleEventForm.getScheduleEventButton().click();
		agendaValidation.validateFormAvailability();

		//agendaValidation.validateEventNonListing();
	}

	/* Given the user is trying to schedule an event,
	 * And the user has provided a valid name, a valid date, and a valid time,
	 * And even though the user has provided blank input for a place,
	 * When the user confirms the scheduling,
	 * Then the event is added to the user's agenda.
	 */

	public void scheduleAnEvent_missingPlace(String name, String date, String time) {
		agendaPage.getScheduleAnEventButton().click();
		agendaValidation.validateFormAvailability();

		ScheduleEventForm scheduleEventForm = new ScheduleEventForm(this.driver);
		scheduleEventForm.getNameInput().sendKeys(name);
		scheduleEventForm.getDateInput().sendKeys(date);
		scheduleEventForm.getTimeInput().sendKeys(time);
		// Not doing scheduleEventForm.getPlaceInput().sendKeys(place);
		scheduleEventForm.getScheduleEventButton().click();
		agendaValidation.validateFormUnavailability();

		//agendaValidation.validateEventListing(name);
	}
}