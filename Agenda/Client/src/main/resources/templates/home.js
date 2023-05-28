enableScheduleAnEventForm(false);
getById("schedule-an-event-button").onclick = () => { enableScheduleAnEventForm(true); }
getById("schedule-form-cancel-scheduling-button").onclick = () => { enableScheduleAnEventForm(false); }
//populateAgenda();
//simulateInvite();

function enableScheduleAnEventForm(enable) {
	switch (enable) {
		case false:
			getById("schedule-an-event-button").style.display = "block";
			getById("schedule-an-event-form").style.display = "none";
			break;
		default:
		case true:
			getById("schedule-an-event-button").style.display = "none";
			getById("schedule-an-event-form").style.display = "block";
			break;
	}
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

function populateAgenda() {
	var events = [{
	name: "Meeting",
	dateTime: new Date(2023, 4, 26, 10, 30, 00),
	place: "Meeting room 5"
}, {
	name: "Party",
	dateTime: new Date(2023, 4, 26, 22, 30, 00),
	place: "Daniel's house"
}, {
	name: "Test",
	dateTime: new Date(2024, 6, 29, 19, 00, 00),
	place: "Room 404"
}]

	today = Date.now();
	events.forEach((event) => {
		newEvent = document.createElement("li");
		newEvent.className = "event-key-details";
		newEvent.ariaLabel = "Event";

		newEventName = document.createElement("h3");
		newEventName.innerHTML = event.name;
		newEvent.appendChild(newEventName);

		newEventName = document.createElement("p");
		newEventName.innerHTML = getRelativeName(event.dateTime) + ", at " + getFriendlyTimeHHMM(event.dateTime) + ".";
		newEvent.appendChild(newEventName);

		if (event.dateTime.getTime() - today.getTime() < (1000 * 60 * 60 * 24 * 7))
			getById("events-happening-soon").appendChild(newEvent);	
		else
			getById("events-happening-later").appendChild(newEvent);	
	})
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

function getRelativeName(date) {
	weekdays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
	today = new Date(Date.now());
		console.log(date, today)
	
	if (date.getDate() === today.getDate())
		return "Today";
	if (date.getDate() === today.getDate() + 1)
		return "Tomorrow";
	if (date.getMonth() === today.getMonth())
		return weekdays[date.getDay()] + ", " + date.getDate();
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

function getById(id) {
	return document.getElementById(id);
}