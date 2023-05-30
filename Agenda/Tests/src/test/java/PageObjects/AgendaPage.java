package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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
	
	public WebElement getEventFromHappeningSoon(int index) {
		return driver.findElement(By.xpath("//ol[@id='events-happening-soon']/following-child::*[" + index + "]"));
	}
	
	public WebElement x(int index) {
		return driver.findElement(By.xpath("//ol[@id='events-happening-soon'][" + index + "]"));
	}
	
	public List<WebElement> y() {
		return driver.findElements(By.xpath("//ol[@id='events-happening-soon']/li"));
	}
	
}