package Validations;

import PageObjects.AgendaPage;
import Framework.Browser.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Assertions;

public class AgendaValidation {
	
	private WebDriver driver;
	private Waits wait;
	private AgendaPage agendaPage;
	
	public AgendaValidation(WebDriver driver) {
		this.driver = driver;
		this.wait = new Waits(this.driver); 
		this.agendaPage = new AgendaPage(this.driver);
	}
	
	public void validateFormAvailability() {
		WebElement form = wait.visibilityOfElement(By.id("schedule-an-event-form"));
		Assertions.assertTrue(form.isDisplayed());
	}
	
	public void validateFormUnavailability() {
		WebElement form = wait.visibilityOfElement(By.id("schedule-an-event-form"));
		Assertions.assertTrue(!form.isDisplayed());
	}
	
	public void validateEventListing() {
		
	}
	
	public void validateEventNonListing() {
		
	}
	
}