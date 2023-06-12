package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.By;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public WebElement findEvent(String name, String date, String time) {
		List<WebElement> eventList;
		
		if (parseDate(date).toEpochDay() - LocalDate.now().toEpochDay() < 7)
			eventList = getEventsHappeningSoon();
		else
			eventList = getEventsHappeningLater();
		
		for (WebElement event: eventList) {
				return event;
		}
		
		return null;
	}
	
	public WebElement getEventHappeningSoon(int index) {
		return driver.findElement(By.xpath("//ol[@id='events-happening-soon']/li[" + index + "]"));
	}
	
	public WebElement getEventHappeningLater(int index) {
		return driver.findElement(By.xpath("//ol[@id='events-happening-later']/li[" + index + "]"));
	}
	
	public List<WebElement> getEventsHappeningSoon() {
		return driver.findElements(By.xpath("//ol[@id='events-happening-soon']/li"));
	}
	
	public List<WebElement> getEventsHappeningLater() {
		return driver.findElements(By.xpath("//ol[@id='events-happening-later']/li"));
	}
	
	public LocalDate parseDate(String date) {
		Matcher matcher = Pattern.compile("[0-9]{4}-[0-1][0-9]-[0-3][0-9]").matcher(date);
		if (matcher.find()) {
			String[] dateParts = date.split("-");
			int year = Integer.parseInt(dateParts[0]);
			int month = Integer.parseInt(dateParts[1]);
			int day = Integer.parseInt(dateParts[2]);
			return LocalDate.of(year, month, day);
		}
		return null;
	}
	
	public LocalTime parseTime(String time) {
		Matcher matcher = Pattern.compile("[0-2][0-9]:[0-6][0-9]").matcher(time);
		if (matcher.find()) {
			String[] timeParts = time.split(":");
			int hour = Integer.parseInt(timeParts[0]);
			int minute = Integer.parseInt(timeParts[1]);
			return LocalTime.of(hour, minute);
		}
		return null;
	}
	
}