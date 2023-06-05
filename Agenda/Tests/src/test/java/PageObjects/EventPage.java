package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class EventPage {

	private WebDriver driver;
	
	public EventPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getYourAgendaLink() {
		return this.driver.findElement(By.id("home-link"));
	}
	
	public WebElement getTitleHeader() {
		return this.driver.findElement(By.id("title-header"));
	}
	
	public WebElement getEventNameHeader() {
		return this.driver.findElement(By.id("event-name-header"));
	}
	
	public WebElement getEventDateTimeParagraph() {
		return this.driver.findElement(By.id("event-datetime-paragraph"));	
	}
	
	public WebElement getEventPlaceParagraph() {
		return this.driver.findElement(By.id("event-place-paragraph"));
	}
	
	public WebElement getEventDescriptionParagraph() {
		return this.driver.findElement(By.id("event-description-paragraph"));
	}
	
	public WebElement getEditDetailsButton() {
		return this.driver.findElement(By.id("edit-details-button"));
	}
	
	public WebElement getRescheduleEventButton() {
		return this.driver.findElement(By.id("reschedule-event-button"));
	}
	
	public WebElement getCancelEventButton() {
		return this.driver.findElement(By.id("cancel-event-button"));
	}
	
	public WebElement getEditDetailsFormNameInput() {
		return this.driver.findElement(By.id("edit-details-name-input"));
	}
	
	public WebElement getEditDetailsFormPlaceInput() {
		return this.driver.findElement(By.id("edit-details-place-input"));
	}
	
	public WebElement getEditDetailsFormDescriptionInput() {
		return this.driver.findElement(By.id("edit-details-description-input"));
	}
	
	public WebElement getEditDetailsFormEditButton() {
		return this.driver.findElement(By.id("edit-form-edit-button"));
	}
	
	public WebElement getEditDetailsFormCancelEditButton() {
		return this.driver.findElement(By.id("edit-form-cancel-edit-button"));
	}

	public WebElement getRescheduleEventFormDateInput() {
		return this.driver.findElement(By.id("reschedule-date-input"));
	}
	
	public WebElement getRescheduleEventFormTimeInput() {
		return this.driver.findElement(By.id("reschedule-time-input"));
	}
	
	public WebElement getRescheduleEventFormPlaceInput() {
		return this.driver.findElement(By.id("reschedule-place-input"));
	}
	
	public WebElement getRescheduleEventFormRescheduleEventButton() {
		return this.driver.findElement(By.id("reschedule-form-reschedule-event-button"));
	}
	
	public WebElement getRescheduleEventFormCancelRescheduleButton() {
		return this.driver.findElement(By.id("reschedule-form-cancel-reschedule-button"));
	}
	
	public WebElement getCancelEventWarning() {
		return this.driver.findElement(By.id("cancel-event-warning"));
	}
	
	public WebElement getCancelEventWarningCancelEventButton() {
		return this.driver.findElement(By.id("cancel-event-cancel-event-button"));
	}
	
	public WebElement getCancelEventWarningDontCancelButton() {
		return this.driver.findElement(By.id("cancel-event-dont-cancel-button"));
	}
	
}
