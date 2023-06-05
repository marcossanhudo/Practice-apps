package Validations;

import PageObjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Framework.Browser.Waits;
import org.junit.jupiter.api.Assertions;

public class EventValidation {

	private WebDriver driver;
	private EventPage eventPage;
	private Waits wait;
	
	public EventValidation(WebDriver driver) {
		this.driver = driver;
		this.eventPage = new EventPage(this.driver);
		this.wait = new Waits(this.driver);
	}
	
	public void validateEventDetails(String name, String date, String time, String place, String description) {
		try {
			WebElement eventNameHeader = wait.loadElement(eventPage.getEventNameHeader());
			Assertions.assertEquals(name, eventNameHeader.getText());
			WebElement eventDateTimeParagraph = wait.loadElement(eventPage.getEventDateTimeParagraph());
			Assertions.assertEquals(
					date + ", at " + time + ".",
					eventDateTimeParagraph.getText());
			WebElement eventPlaceParagraph = wait.loadElement(eventPage.getEventPlaceParagraph());
			Assertions.assertEquals(
					place.equals("")
						? "No place provided."
						: "At " + place + ".",
					eventPlaceParagraph.getText());
			WebElement eventDescriptionParagraph = wait.loadElement(eventPage.getEventDescriptionParagraph());
			Assertions.assertEquals(
					description.equals("")
						? "No description provided."
						: description,
					eventDescriptionParagraph.getText());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void validateCancelEventWarningVisibility() {
		try {
			WebElement cancelEventWarning = wait.loadElement(eventPage.getCancelEventWarning());
			Assertions.assertTrue(cancelEventWarning.isDisplayed());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void validateEditDetailsFormPrefilling(String name, String description, String place) {
		try {
			WebElement nameInput = wait.loadElement(eventPage.getEditDetailsFormNameInput());
			Assertions.assertEquals(name, nameInput.getAttribute("value"));
			WebElement descriptionInput = wait.loadElement(eventPage.getEditDetailsFormDescriptionInput());
			Assertions.assertEquals(description, descriptionInput.getAttribute("value"));
			WebElement placeInput = wait.loadElement(eventPage.getEditDetailsFormPlaceInput());
			Assertions.assertEquals(place, placeInput.getAttribute("value"));
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void validateRescheduleEventFormPrefilling(String date, String time, String place) {
		try {
			WebElement dateInput = wait.loadElement(wait.loadElement(eventPage.getRescheduleEventFormDateInput()));
			Assertions.assertEquals(date, dateInput.getAttribute("value"));
			WebElement timeInput = wait.loadElement(wait.loadElement(eventPage.getRescheduleEventFormTimeInput()));
			Assertions.assertEquals(time, timeInput.getAttribute("value"));
			WebElement placeInput = wait.loadElement(wait.loadElement(eventPage.getRescheduleEventFormPlaceInput()));
			Assertions.assertEquals(place, placeInput.getAttribute("value"));
		} catch (Exception e) {
			throw e;
		}
	}
	
}
