package Tasks;

import PageObjects.*;
import Validations.EventValidation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditAnEventTask {
	
	private WebDriver driver;
	private EventPage eventPage;
	private EventValidation eventValidation;
	
	public EditAnEventTask(WebDriver driver) { 
		this.driver = driver;
		this.eventPage = new EventPage(this.driver);
		this.eventValidation = new EventValidation(this.driver);
	}
	
	/* Given the user wants to change the details of an event,
	 * When the user confirms the edit,
	 * And the details provided by the user are valid, 
	 * Then the new details are saved.
	 */
	
	public void editAnEvent() {
		eventPage.getEditDetailsButton().click();
		eventPage.getEditDetailsFormNameInput().sendKeys();
		eventPage.getEditDetailsFormPlaceInput().sendKeys();
		eventPage.getEditDetailsFormDescriptionInput().sendKeys();
		eventPage.getEditDetailsFormSaveEditButton().click();
		eventValidation.validateEventDetails();
	}
}