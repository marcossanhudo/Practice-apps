package Tasks;

import org.openqa.selenium.WebDriver;
import PageObjects.*;
import Validations.*;

public class CancelAnEventTask {
	
	private WebDriver driver;
	private EventPage eventPage;
	private AgendaPage agendaPage;
	private EventValidation eventValidation;
	private AgendaValidation agendaValidation;
	
	
	public CancelAnEventTask(WebDriver driver) { 
		this.driver = driver;
		this.eventPage = new EventPage(this.driver);
		this.agendaPage = new AgendaPage(this.driver);
		this.eventValidation = new EventValidation(this.driver);
		this.agendaValidation = new AgendaValidation(this.driver);
	}
	
	/* Given the user is trying to cancel an event in his personal agenda,
	 * When the user confirms the cancellation,
	 * Then the event is removed from the user's personal agenda.
	 */
	
	public void cancelAnEvent(String name, String date, String time, String place, String description) {
		agendaValidation.validatePageTitleAvailability();
		agendaPage.getEventHappeningSoon(1).click();
		eventValidation.validateEventDetails(name, date, time, place, description);
		
		eventPage.getCancelEventButton().click();
		eventValidation.validateCancelEventWarningAvailability();
		eventPage.getCancelEventWarningCancelEventButton().click();
		
		agendaValidation.validatePageTitleAvailability();
		//agendaValidation.validateEventNonListing();
	}
	
}