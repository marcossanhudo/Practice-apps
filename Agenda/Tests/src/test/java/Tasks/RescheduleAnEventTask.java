package Tasks;

import PageObjects.AgendaPage;
import PageObjects.EventPage;
import Validations.AgendaValidation;
import Validations.EventValidation;
import Framework.Utils.FileOperation;

import org.openqa.selenium.WebDriver;

public class RescheduleAnEventTask {

	private WebDriver driver;
	private AgendaPage agendaPage;
	private EventPage eventPage;
	private AgendaValidation agendaValidation;
	private EventValidation eventValidation;

	public RescheduleAnEventTask(WebDriver driver) {
		this.driver = driver;
		this.agendaPage = new AgendaPage(this.driver);
		this.eventPage = new EventPage(this.driver);
		this.agendaValidation = new AgendaValidation(this.driver);
		this.eventValidation = new EventValidation(this.driver);
	}

	/* Given the user is trying to reschedule an event,
	 * And has provided a valid date, a valid time, and a valid place,
	 * When the user confirms the reschedule,
	 * Then the event is edited in the user's agenda, with the new date, time, and place.
	 */

	public void rescheduleAnEvent(String name, String date,
			String time, String place, String description,
			String newDate, String newTime, String newPlace) throws InterruptedException {
		//agendaValidation.validateEventListing(name);
		
		agendaValidation.validatePageTitleAvailability();
		agendaPage.getEventHappeningSoon(1).click();
		eventValidation.validateEventDetails(name, date, time, place, description);

		eventPage.getRescheduleEventButton().click();
		eventValidation.validateRescheduleEventFormAvailability();
		eventValidation.validateRescheduleEventFormPrefilling(date, time, place);
		eventPage.getRescheduleEventFormDateInput().clear();
		eventPage.getRescheduleEventFormDateInput().sendKeys(newDate);
		eventPage.getRescheduleEventFormTimeInput().clear();
		eventPage.getRescheduleEventFormTimeInput().sendKeys(newTime);
		eventPage.getRescheduleEventFormPlaceInput().clear();
		eventPage.getRescheduleEventFormPlaceInput().sendKeys(newPlace);
		eventPage.getRescheduleEventFormRescheduleEventButton().click();
		
		Thread.sleep(300);
		eventValidation.validateEventDetails(name, newDate, newTime, newPlace, description);
		
		eventPage.getYourAgendaLink().click();
		agendaValidation.validatePageTitleAvailability();
		//agendaValidation.validateEventListing(name);
	}

	/* Given the user is trying to reschedule an event,
	 * And the user has provided blank input for a date,	// or blank input for a time
	 * And even though the user has provided a valid time, and a valid place,
	 * When the user confirms the reschedule,
	 * Then the event is not be rescheduled.
	 */

	public void rescheduleAnEvent_missingDate(String name,
			String date, String time, String place, String description,
			String newTime, String newPlace) throws InterruptedException {
		//agendaValidation.validateEventListing(name);
		
		agendaValidation.validatePageTitleAvailability();
		agendaPage.getEventHappeningSoon(1).click();
		eventValidation.validateEventDetails(name, date, time, place, description);

		eventPage.getRescheduleEventButton().click();
		eventValidation.validateRescheduleEventFormAvailability();
		eventValidation.validateRescheduleEventFormPrefilling(date, time, place);
		eventPage.getRescheduleEventFormDateInput().clear();
		eventPage.getRescheduleEventFormTimeInput().clear();
		eventPage.getRescheduleEventFormTimeInput().sendKeys(newTime);
		eventPage.getRescheduleEventFormPlaceInput().clear();
		eventPage.getRescheduleEventFormPlaceInput().sendKeys(newPlace);
		eventPage.getRescheduleEventFormRescheduleEventButton().click();
		
		Thread.sleep(300);
		eventValidation.validateRescheduleEventFormAvailability();
		eventValidation.validateEventDetails(name, date, time, place, description);
		
		eventPage.getYourAgendaLink().click();
		agendaValidation.validatePageTitleAvailability();
		//agendaValidation.validateEventListing(name);
	}
	
	/* Given the user is trying to reschedule an event,
	 * And the user has provided a valid date, and a valid time,
	 * And even though the user has provided blank input for a place,
	 * When the user confirms the reschedule,
	 * Then the event is edited in the user's agenda, with the provided date and time,
	 * And the place information is empty.
	 */

	public void rescheduleAnEvent_missingPlace(String name, String date,
			String time, String description, String place,
			String newDate, String newTime) throws InterruptedException {
		//agendaValidation.validateEventListing(name);
		
		agendaValidation.validatePageTitleAvailability();
		agendaPage.getEventHappeningSoon(1).click();
		eventValidation.validateEventDetails(name, date, time, place, description);

		eventPage.getRescheduleEventButton().click();
		eventValidation.validateRescheduleEventFormAvailability();
		eventValidation.validateRescheduleEventFormPrefilling(date, time, place);
		eventPage.getRescheduleEventFormDateInput().clear();
		eventPage.getRescheduleEventFormDateInput().sendKeys(newDate);
		eventPage.getRescheduleEventFormTimeInput().clear();
		eventPage.getRescheduleEventFormTimeInput().sendKeys(newTime);
		eventPage.getRescheduleEventFormPlaceInput().clear();
		eventPage.getRescheduleEventFormRescheduleEventButton().click();
		
		Thread.sleep(300);
		eventValidation.validateEventDetails(name, newDate, newTime, "", description);
		
		eventPage.getYourAgendaLink().click();
		agendaValidation.validatePageTitleAvailability();
		//agendaValidation.validateEventListing(name);
	}
}