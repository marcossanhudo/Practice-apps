package Tasks;

import PageObjects.*;
import Validations.AgendaValidation;
import Validations.EventValidation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditAnEventTask {
	
	private WebDriver driver;
	private AgendaPage agendaPage;
	private EventPage eventPage;
	private AgendaValidation agendaValidation;
	private EventValidation eventValidation;
	
	public EditAnEventTask(WebDriver driver) { 
		this.driver = driver;
		this.agendaPage = new AgendaPage(this.driver);
		this.eventPage = new EventPage(this.driver);
		this.agendaValidation = new AgendaValidation(this.driver);
		this.eventValidation = new EventValidation(this.driver);
	}
	
	/* Given the user wants to change the details of an event,
	 * When the user confirms the edit,
	 * And the details provided by the user are valid, 
	 * Then the new details are saved.
	 */
	
	public void editAnEvent(String name, String date,
			String time, String place, String description,
			String newName, String newPlace, String newDescription) {
		//agendaValidation.validateEventListing(name);

		agendaPage.getEventHappeningSoon(1).click();
		eventValidation.validateEventDetails(name, date, time, place, description);

		eventPage.getEditDetailsButton().click();
		eventValidation.validateEditDetailsFormPrefilling(name, description, place);
		eventPage.getEditDetailsFormNameInput().sendKeys(newName);
		eventPage.getEditDetailsFormDescriptionInput().sendKeys(newDescription);
		eventPage.getEditDetailsFormPlaceInput().sendKeys(newPlace);
		eventPage.getEditDetailsFormEditButton().click();
		eventValidation.validateEventDetails(newName, date, time, newPlace, newDescription);
		
		eventPage.getYourAgendaLink().click();
		//agendaValidation.validateEventListing(newName);
	}
}