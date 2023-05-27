package Tasks;

import PageObjects.AgendaPage;
import Validations.AgendaValidation;
import org.openqa.selenium.WebDriver;

public class RescheduleAnEventTask {

	private WebDriver driver;
	private AgendaPage agendaPage;
	private AgendaValidation agendaValidation;

	public RescheduleAnEventTask(WebDriver driver) {
		this.driver = driver;
		this.agendaPage = new AgendaPage(this.driver);
		this.agendaValidation = new AgendaValidation(this.driver);
	}

	/* Given the user is trying to reschedule an event,
	 * When the user confirms the reschedule,
	 * And the new date and time are valid,
	 * Then the event is edited in the user's agenda, with the new date and time.
	 */

	public void rescheduleAnEvent() {
		agendaValidation.validateEventListing();

		/*agendaPage.selectEvent();
		agendaValidation.validateSelectedEvent();

		agendaPage.setSelectEventNewDate();
		agendaPage.setSelectedEventNewTime();
		agendaPage.clickRescheduleButton();
		agendaValidation.validationEventListing();*/
	}

}