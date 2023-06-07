const userId = 1;
//endpoints = {
	const base = "http://localhost:8000";
	const usersEvents = base + "/user" + "/" + userId + "/events";
	const scheduleEvent = base + "/schedule-event";
//}

enableScheduleAnEventForm(false);
enableInviteeManagementInterface(false);
getById("schedule-an-event-button").onclick = () => { enableScheduleAnEventForm(true); }
getById("schedule-form-cancel-scheduling-button").onclick = async () => { enableScheduleAnEventForm(false); }
getById("schedule-form-schedule-event-button").onclick = () => { enableScheduleAnEventForm(false); }
getById("schedule-form-manage-invitees-button").onclick = () => { enableInviteeManagementInterface(true); }
getById("invitee-management-cancel-changes-button").onclick = () => { enableInviteeManagementInterface(false); }
populateAgenda();
//simulateInvite();

getById("schedule-an-event-form").addEventListener("submit", async function(event) {
	event.preventDefault();
	await sendScheduleAnEventRequest()
	setTimeout(function () {
		populateAgenda();
	}, 300);
})

function enableScheduleAnEventForm(enable) {
	switch (enable) {
		case false:
			getById("schedule-an-event-button").style.display = "block";
			getById("schedule-an-event-form").style.display = "none";
			break;
		default:
		case true:
			getById("schedule-an-event-button").style.display = "none";
			getById("schedule-an-event-form").reset();
			getById("schedule-an-event-form").style.display = "block";
			break;
	}
}

function enableInviteeManagementInterface(enable) {
	switch (enable) {
		case false:
			getById("invitee-management-interface").style.display = "none";
			break;
		default:
		case true:
			getById("invitee-management-interface").style.display = "block";
			break;
	}
}

async function populateAgenda() {
	const events = await fetch(/*endpoints.*/ usersEvents);
	const json = await events.json();
	console.log(json);

	await clearAgenda();

	today = Date.now();
	json.forEach((event) => {
		renderEventKeyDetailListing(event);
	})
}

async function clearAgenda() {
	/*
	if (getById("events-happening-right-now").children !== undefined
		&& getById("events-happening-right-now").children.length > 0)
		getById("events-happening-right-now").children.forEach(child => {
			if (elementIsEvent(child))
				child.remove();
		});

	if (getById("events-happening-soon").children !== undefined
		&& getById("events-happening-soon").children.length > 0) {
		children = await getById("events-happening-soon").children;
		children.forEach(child => {
			if (elementIsEvent(child))
				child.remove();
		});
	}

	if (getById("events-happening-later").children !== undefined
		&& getById("events-happening-later").children.length > 0) {
		children = await getById("events-happening-later").children;
		children.forEach(child => {
			if (elementIsEvent(child))
				child.remove();
		});
	}
	*/
}

function elementIsEvent(element) {
	if (element.className === "event-key-details"
		|| element.className === "event-all-details")
		return true;
	return false;
}

function renderEventKeyDetailListing(event) {
	newEvent = document.createElement("li");

	newEventLink = document.createElement("a");
	newEventLink.href = "/event.html?id=" + event.id;

	newEventLink.className = "event-key-details";
	newEvent.ariaLabel = "Event";

	newEventName = document.createElement("h3");
	newEventName.className = "event-name-header";
	newEventName.innerHTML = event.name;
	newEventLink.appendChild(newEventName);

	newEventDateTime = document.createElement("p");
	newEventDateTime.className = "event-datetime-paragraph";
	const dateTime = new Date(event.date + "T" + event.time);
	newEventDateTime.innerHTML = getRelativeName(dateTime) + ", at " + getFriendlyTimeHHMM(dateTime) + ".";
	newEventLink.appendChild(newEventDateTime);

	newEvent.appendChild(newEventLink);

	if (dateTime.getTime() - today.getTime() < (1000 * 60 * 60 * 24 * 7)) {
		getById("no-events-happening-soon-paragraph").style.display = "none";
		getById("events-happening-soon").appendChild(newEvent);
	} else {
		getById("no-events-happening-later-paragraph").style.display = "none";
		getById("events-happening-later").appendChild(newEvent);
	}
}

async function sendScheduleAnEventRequest(event) {
	if (validateScheduleAnEventFormInputs()) {
		requestBody =  "creatorId=" + userId +
			"&" + "name=" + getById("schedule-form-name-input").value +
			"&" + "date=" + getById("schedule-form-date-input").value +
			"&" + "time=" + getById("schedule-form-time-input").value +
			"&" + "place=" + getById("schedule-form-place-input").value

		/*{
			creatorId: userId,
			name: getById("schedule-form-name-input").value,
			date: getById("schedule-form-date-input").value,
			time: getById("schedule-form-time-input").value,
			place: getById("schedule-form-place-input").value
		}*/

		console.log(requestBody);

		await fetch(/*endpoints.*/ scheduleEvent, { method: "POST", headers: { "Content-Type": "application/x-www-form-urlencoded" }, body: requestBody });
	}
}

function validateScheduleAnEventFormInputs() {
	valid = true;

	if (!hasText(getById("schedule-form-name-input").value))
		valid = false;
	if (!hasText(getById("schedule-form-date-input").value))
		valid = false;
	if (!hasText(getById("schedule-form-time-input").value))
		valid = false;

	return valid;
}

function getRelativeName(date) {
	weekdays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
	today = new Date(Date.now());
	
	if (date.getFullYear() === today.getFullYear() &&
		date.getMonth() === today.getMonth()) {
		if (date.getDate() === today.getDate())
			return "Today";
		if (date.getDate() === today.getDate() + 1)
			return "Tomorrow";
		return weekdays[date.getDay()] + ", " + date.getDate();
	}

	return "On " + date.getDate() + "/" + (date.getMonth() + 1) + (date.getFullYear() !== today.getFullYear() ? "/" + date.getFullYear() : "");
}

function getFriendlyTimeHHMM(time) {
	hours = time.getHours();
	if (hours > 12) {
		hours -= 12;
		dayHalf = "PM";
	} else if (hours === 12) {
		dayHalf = "PM";
	} else {
		dayHalf = "AM";
	}

	minutes = time.getMinutes();
	if (time.getMinutes() < 10)
		minutes = "0" + minutes;
	
	return hours + ":" + minutes + dayHalf;
}

function hasText(textElement) {
	if (textElement !== null
		&& textElement !== undefined
		&& textElement !== "")
		return true;
	return false;
}

function getById(id) {
	return document.getElementById(id);
}








function addEvent() {
	newEvent = document.createElement("li");
	newEvent.className = "event-key-details";
	newEvent.ariaLabel = "Event";

	newEventName = document.createElement("h3");
	newEventName.innerHTML = getById("schedule-form-name-input").value;
	newEvent.appendChild(newEventName);

	newEventName = document.createElement("p");
	newEventName.innerHTML = getRelativeName(getById("schedule-form-date-input").value) + ", at " + getById("schedule-form-time-input").value + ".";
	newEvent.appendChild(newEventName);

	getById("events-happening-soon").appendChild(newEvent);
	enableScheduleAnEventForm(false);
}

function simulateInvite() {
	status = { pending: "pending", accepted: "accepted", declined: "declined" }

	invite = {
		event: {
			name: "Party",
			dateTime: new Date(2023, 4, 27, 23, 00, 00),
			place: "Daniel's house"
		},
		inviter: {
			name: "Daniel Doe"
		},
		status: status.pending
	}

	newInvite = document.createElement("li");
	newInvite.className = "event-all-details";
	newInvite.ariaLabel = "Invite to an event";

	newInviteDateTime = document.createElement("p");
	newInviteDateTime.innerHTML = invite.inviter.name + " has invited you to";
	newInvite.appendChild(newInviteDateTime);

	newInviteName = document.createElement("h3");
	newInviteName.innerHTML = invite.event.name;
	newInvite.appendChild(newInviteName);

	newInviteDateTime = document.createElement("p");
	newInviteDateTime.innerHTML = getRelativeName(invite.event.dateTime) + ", at " + getFriendlyTimeHHMM(invite.event.dateTime) + ".";
	newInvite.appendChild(newInviteDateTime);

	newInvitePlace = document.createElement("p");
	newInvitePlace.innerHTML = "At " + invite.event.place + ".";
	newInvite.appendChild(newInvitePlace);

	newInviteAcceptButton = document.createElement("button");
	newInviteAcceptButton.innerHTML = "Accept";
	newInviteAcceptButton.className = "primary-button";
	newInvite.appendChild(newInviteAcceptButton);

	newInviteRescheduleButton = document.createElement("button");
	newInviteRescheduleButton.innerHTML = "Ask for a reschedule";
	newInviteRescheduleButton.className = "secondary-button";
	newInvite.appendChild(newInviteRescheduleButton);

	newInviteDeclineButton = document.createElement("button");
	newInviteDeclineButton.innerHTML = "Decline";
	newInviteDeclineButton.className = "secondary-button";
	newInvite.appendChild(newInviteDeclineButton);

	/* <li class="invite-to-event-all-details"
		<p>Emily, Fred, Grant, and 15 others have received an invite.</p>
	</li>*/

	getById("no-invite-paragraph").style.display = "none";
	getById("invites").appendChild(newInvite);
}