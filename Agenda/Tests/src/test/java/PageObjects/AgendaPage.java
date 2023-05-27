package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class AgendaPage {

	private WebDriver driver;

	public AgendaPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getAgendaH1() {
		return driver.findElement(By.id("title-header"));
	}
	
	public WebElement getScheduleAnEventButton() {
		return driver.findElement(By.id("schedule-an-event-button"));
	}
}