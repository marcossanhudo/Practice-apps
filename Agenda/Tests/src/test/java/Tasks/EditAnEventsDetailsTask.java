package Tasks;

import PageObjects.*;
import Validations.AgendaValidation;
import Validations.EventValidation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditAnEventsDetailsTask {
	
	private WebDriver driver;
	private AgendaPage agendaPage;
	private EventPage eventPage;
	private AgendaValidation agendaValidation;
	private EventValidation eventValidation;
	
	public EditAnEventsDetailsTask(WebDriver driver) { 
		this.driver = driver;
		this.agendaPage = new AgendaPage(this.driver);
		this.eventPage = new EventPage(this.driver);
		this.agendaValidation = new AgendaValidation(this.driver);
		this.eventValidation = new EventValidation(this.driver);
	}
	
	/* Given the user wants to change the details of an event,
	 * And the user has provided a valid name, a valid description, and a valid place,
	 * When the user confirms the edit,
	 * Then the new details are saved, and appear on the event page, and on the agenda page.
	 */
	
	public void editAnEventsDetails(String name, String date,
			String time, String place, String description,
			String newName, String newDescription, String newPlace) throws InterruptedException {
		//agendaValidation.validateEventListing(name);

		agendaPage.getEventHappeningSoon(1).click();
		eventValidation.validateEventDetails(name, date, time, place, description);

		eventPage.getEditDetailsButton().click();
		eventValidation.validateEditDetailsFormAvailability();
		eventValidation.validateEditDetailsFormPrefilling(name, description, place);
		eventPage.getEditDetailsFormNameInput().clear();
		eventPage.getEditDetailsFormNameInput().sendKeys(newName);
		eventPage.getEditDetailsFormDescriptionInput().clear();
		eventPage.getEditDetailsFormDescriptionInput().sendKeys(newDescription);
		eventPage.getEditDetailsFormPlaceInput().clear();
		eventPage.getEditDetailsFormPlaceInput().sendKeys(newPlace);
		eventPage.getEditDetailsFormEditButton().click();
		
		Thread.sleep(300);
		eventValidation.validateEventDetails(newName, date, time, newPlace, newDescription);
		
		eventPage.getYourAgendaLink().click();
		agendaValidation.validatePageTitleAvailability();
		//agendaValidation.validateEventListing(newName);
	}
	
	/* Given the user wants to change the details of an event,
	 * And the user has provided a valid description, and a valid place,
	 * But has not provided a valid name,
	 * When the user confirms the edit,
	 * Then the new details are not saved.
	 */
	
	public void editAnEventsDetails_missingName(String name, String date,
			String time, String place, String description,
			String newDescription, String newPlace) throws InterruptedException {
		//agendaValidation.validateEventListing(name);

		agendaPage.getEventHappeningSoon(1).click();
		eventValidation.validateEventDetails(name, date, time, place, description);

		eventPage.getEditDetailsButton().click();
		eventValidation.validateEditDetailsFormAvailability();
		eventValidation.validateEditDetailsFormPrefilling(name, description, place);
		eventPage.getEditDetailsFormNameInput().clear();
		// Not doing eventPage.getEditDetailsFormNameInput().sendKeys(newName);
		eventPage.getEditDetailsFormDescriptionInput().clear();
		eventPage.getEditDetailsFormDescriptionInput().sendKeys(newDescription);
		eventPage.getEditDetailsFormPlaceInput().clear();
		eventPage.getEditDetailsFormPlaceInput().sendKeys(newPlace);
		eventPage.getEditDetailsFormEditButton().click();
		
		Thread.sleep(300);
		eventValidation.validateEditDetailsFormAvailability();
		eventValidation.validateEventDetails(name, date, time, place, description);
		
		eventPage.getYourAgendaLink().click();
		agendaValidation.validatePageTitleAvailability();
		//agendaValidation.validateEventListing(newName);
	}
	
	/* Given the user wants to change the details of an event,
	 * And the user has provided a valid name, and a valid place,
	 * And even though the user has provided blank input for a description,
	 * When the user confirms the edit,
	 * Then the new details are saved, and appear on the event page, and on the agenda page.
	 */
	
	public void editAnEventsDetails_missingDescription(String name, String date,
			String time, String place, String description,
			String newName, String newPlace) throws InterruptedException {
		//agendaValidation.validateEventListing(name);

		agendaPage.getEventHappeningSoon(1).click();
		eventValidation.validateEventDetails(name, date, time, place, description);

		eventPage.getEditDetailsButton().click();
		eventValidation.validateEditDetailsFormAvailability();
		eventValidation.validateEditDetailsFormPrefilling(name, description, place);
		eventPage.getEditDetailsFormNameInput().clear();
		eventPage.getEditDetailsFormNameInput().sendKeys(newName);
		eventPage.getEditDetailsFormDescriptionInput().clear();
		// Not doing eventPage.getEditDetailsFormDescriptionInput().sendKeys(newDescription);
		eventPage.getEditDetailsFormPlaceInput().clear();
		eventPage.getEditDetailsFormPlaceInput().sendKeys(newPlace);
		eventPage.getEditDetailsFormEditButton().click();
		
		Thread.sleep(300);
		eventValidation.validateEventDetails(newName, date, time, newPlace, description);
		
		eventPage.getYourAgendaLink().click();
		agendaValidation.validatePageTitleAvailability();
		//agendaValidation.validateEventListing(newName);
	}
}