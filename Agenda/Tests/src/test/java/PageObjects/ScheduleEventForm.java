package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class ScheduleEventForm {

	private WebDriver driver;

	public ScheduleEventForm(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getNameInput() {
		return driver.findElement(By.id("schedule-form-name-input"));
	}

	public WebElement getDateInput() {
		return driver.findElement(By.id("schedule-form-date-input"));
	}

	public WebElement getTimeInput() {
		return driver.findElement(By.id("schedule-form-time-input"));
	}

	public WebElement getPlaceInput() {
		return driver.findElement(By.id("schedule-form-place-input"));
	}

	public WebElement getScheduleEventButton() {
		return driver.findElement(By.id("schedule-form-schedule-event-button"));
	}

	public WebElement getCancelSchedulingButton() {
		return driver.findElement(By.id("schedule-form-cancel-scheduling-button"));
	}

}