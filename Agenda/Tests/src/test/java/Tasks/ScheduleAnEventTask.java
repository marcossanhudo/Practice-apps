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
	 * And all the required details are provided,
	 * When the user confirms the scheduling,
	 * Then the event is added to the user's agenda.
	 */

	public void scheduleAnEvent() throws InterruptedException {
		agendaPage.getScheduleAnEventButton().click();
		agendaValidation.validateFormAvailability();

		ScheduleEventForm scheduleEventForm = new ScheduleEventForm(this.driver);
		scheduleEventForm.getNameInput().sendKeys("New event");
		scheduleEventForm.getDateInput().sendKeys("2023-05-28");
		scheduleEventForm.getTimeInput().sendKeys("10:00");
		scheduleEventForm.getPlaceInput().sendKeys("A place");
		Thread.sleep(3000);
		scheduleEventForm.getScheduleEventButton().click();
		agendaValidation.validateFormUnavailability();

		agendaValidation.validateEventListing();
	}

	/* Given the user is trying to schedule an event,
	 * And not all the required details are provided,
	 * When the user confirms the scheduling,
	 * Then the event is not be added to the user's agenda,
	 * And the user is notified about the missing detail.
	 */

	public void scheduleAnEvent_missingName() {
		agendaPage.getScheduleAnEventButton().click();
		agendaValidation.validateFormAvailability();

		ScheduleEventForm scheduleEventForm = new ScheduleEventForm(this.driver);
		ScheduleFormValidation scheduleFormValidation = new ScheduleFormValidation(this.driver);
		// Not doing ScheduleEventForm scheduleEventForm = new ScheduleEventForm(this.driver);
		scheduleEventForm.getDateInput().sendKeys();
		scheduleEventForm.getTimeInput().sendKeys();
		scheduleEventForm.getPlaceInput().sendKeys();
		scheduleEventForm.getScheduleEventButton().click();
		agendaValidation.validateFormAvailability();
		scheduleFormValidation.validateEventNameMissingNotification();
		agendaValidation.validateEventNonListing();
	}

	/* Given the user is trying to schedule an event,
	 * And all the required details are provided,
	 * But an optional detail is not provided,
	 * When the user confirms the scheduling,
	 * Then the event is added to the user's agenda.
	 */

	public void scheduleAnEvent_missingPlace() {
		agendaPage.getScheduleAnEventButton().click();
		agendaValidation.validateFormAvailability();

		ScheduleEventForm scheduleEventForm = new ScheduleEventForm(this.driver);
		scheduleEventForm.getNameInput().sendKeys();
		scheduleEventForm.getDateInput().sendKeys();
		scheduleEventForm.getTimeInput().sendKeys();
		// Not doing scheduleEventForm.getPlaceInput().sendKeys();
		scheduleEventForm.getScheduleEventButton().click();
		agendaValidation.validateFormUnavailability();

		agendaValidation.validateEventListing();
	}
}