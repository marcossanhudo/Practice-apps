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
	
	public void validatePageTitleAvailability() {
		WebElement title = wait.loadElement(agendaPage.getAgendaH1());
		Assertions.assertTrue(title.isDisplayed());
	}
	
	public void validateFormAvailability() {
		WebElement form = wait.visibilityOfElement(By.id("schedule-an-event-form"));
		Assertions.assertTrue(form.isDisplayed());
	}
	
	public void validateFormUnavailability() {
		try {
			Assertions.assertTrue(!this.driver.findElement(By.id("schedule-an-event-form")).isDisplayed());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void validateEventListing(String name) {
		
	}
	
	public void validateEventNonListing() {
		
	}
	
}