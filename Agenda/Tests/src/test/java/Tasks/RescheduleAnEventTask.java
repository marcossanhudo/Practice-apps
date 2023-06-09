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
	 * When the user confirms the reschedule,
	 * And the new date, time, and place, are valid,
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
	 * When the user confirms the reschedule,
	 * And the new date, time, and place, are valid,
	 * Then the event is edited in the user's agenda, with the new date, time, and place.
	 */

	public void rescheduleAnEvent_missingDate(String name,
			String date, String time, String place, String description,
			String newTime, String newPlace) throws InterruptedException {
		//agendaValidation.validateEventListing(name);
		
		agendaValidation.validatePageTitleAvailability();
		agendaPage.getEventHappeningSoon(1).click();
		eventValidation.validateEventDetails(name, date, time, place, description);

		eventPage.getRescheduleEventButton().click();
		eventValidation.validateRescheduleEventFormPrefilling(date, time, place);
		eventPage.getRescheduleEventFormDateInput().clear();
		eventPage.getRescheduleEventFormDateInput().sendKeys("");
		eventPage.getRescheduleEventFormTimeInput().clear();
		eventPage.getRescheduleEventFormTimeInput().sendKeys(newTime);
		eventPage.getRescheduleEventFormPlaceInput().clear();
		eventPage.getRescheduleEventFormPlaceInput().sendKeys(newPlace);
		eventPage.getRescheduleEventFormRescheduleEventButton().click();
		
		Thread.sleep(300);
		// ...
		
		eventPage.getYourAgendaLink().click();
		agendaValidation.validatePageTitleAvailability();
		//agendaValidation.validateEventListing(name);
	}
	
	/* Given the user is trying to reschedule an event,
	 * When the user confirms the reschedule,
	 * And the new date, time, and place, are valid,
	 * Then the event is edited in the user's agenda, with the new date, time, and place.
	 */

	public void rescheduleAnEvent_missingPlace(String name, String date,
			String time, String description, String place,
			String newDate, String newTime) throws InterruptedException {
		//agendaValidation.validateEventListing(name);
		
		agendaValidation.validatePageTitleAvailability();
		agendaPage.getEventHappeningSoon(1).click();
		eventValidation.validateEventDetails(name, date, time, place, description);

		eventPage.getRescheduleEventButton().click();
		eventValidation.validateRescheduleEventFormPrefilling(date, time, place);
		eventPage.getRescheduleEventFormDateInput().clear();
		eventPage.getRescheduleEventFormDateInput().sendKeys(newDate);
		eventPage.getRescheduleEventFormTimeInput().clear();
		eventPage.getRescheduleEventFormTimeInput().sendKeys(newTime);
		eventPage.getRescheduleEventFormPlaceInput().clear();
		eventPage.getRescheduleEventFormPlaceInput().sendKeys("");
		eventPage.getRescheduleEventFormRescheduleEventButton().click();
		
		Thread.sleep(300);
		eventValidation.validateEventDetails(name, newDate, newTime, "", description);
		
		eventPage.getYourAgendaLink().click();
		agendaValidation.validatePageTitleAvailability();
		//agendaValidation.validateEventListing(name);
	}
}