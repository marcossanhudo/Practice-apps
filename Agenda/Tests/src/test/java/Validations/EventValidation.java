package Validations;

import PageObjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EventValidation {

	private WebDriver driver;
	private EventPage eventPage;
	
	public EventValidation(WebDriver driver) {
		this.driver = driver;
		this.eventPage = new EventPage(this.driver);
	}
	
	public void validateEventDetails() {
		
	}
	
}
