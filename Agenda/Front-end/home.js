enableScheduleAnEventForm(false);
getById("schedule-an-event-button").onclick = () => { enableScheduleAnEventForm(true); }
getById("cancel-schedule-an-event-button").onclick = () => { enableScheduleAnEventForm(false); }

function enableScheduleAnEventForm(enable) {
	switch (enable) {
		case false:
			getById("schedule-an-event-form").style.display = "none";
			break;
		default:
		case true:
			getById("schedule-an-event-form").style.display = "block";
			break;
	}
}

function addEvent() {
	newEvent = document.createElement("li");

	newEventName = document.createElement("h3");
	newEventName.innerHTML = getById("schedule-form-name-input").value;
	newEvent.appendChild(newEventName);

	newEventName = document.createElement("p");
	newEventName.innerHTML = getRelativeName(getById("schedule-form-date-input").value) + ", at " + getById("schedule-form-time-input").value + ".";
	newEvent.appendChild(newEventName);

	getById("events-happening-soon").appendChild(newEvent);
	enableScheduleAnEventForm(false);
}

function getRelativeName(date) {
	today = new Date();
	date = Date.parse(date);

	if (date.getDate() === today.getDate())
		return "Today";
	if (date.getDate() === today.getDate() + 1)
		return "Tomorrow";
	return date;
}

function getById(id) {
	return document.getElementById(id);
}