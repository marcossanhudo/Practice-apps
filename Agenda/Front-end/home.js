const userId = 1;

enableScheduleAnEventForm(false);
getById("schedule-an-event-button").onclick = () => { enableScheduleAnEventForm(true); }
getById("schedule-form-cancel-scheduling-button").onclick = async () => { await enableScheduleAnEventForm(false); }
getById("schedule-form-schedule-event-button").onclick = () => { enableScheduleAnEventForm(false); }
populateAgenda();
//simulateInvite();

getById("schedule-an-event-form").addEventListener("submit", function() {
	setTimeout(function () {
		populateAgenda();
	}, 500);
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

async function populateAgenda() {
	const events = await fetch("http://localhost:8000/user/" + userId + "/events");
	const json = await events.json();
	console.log(json);

	today = Date.now();
	json.forEach((event) => {
		renderEventKeyDetailListing(event);
	})
}

function renderEventKeyDetailListing(event) {
	newEvent = document.createElement("li");

	newEventLink = document.createElement("a");
	newEventLink.href = "/event.html?id=" + event.id;

	newEvent.className = "event-key-details";
	newEvent.ariaLabel = "Event";

	newEventName = document.createElement("h3");
	newEventName.className = "event-name-header";
	newEventName.innerHTML = event.name;
	newEvent.appendChild(newEventName);

	newEventDateTime = document.createElement("p");
	newEventDateTime.className = "event-datetime-paragraph";
	const dateTime = new Date(event.date + "T" + event.time);
	newEventDateTime.innerHTML = getRelativeName(dateTime) + ", at " + getFriendlyTimeHHMM(dateTime) + ".";
	newEvent.appendChild(newEventDateTime);

	newEventLink.appendChild(newEvent);

	if (dateTime.getTime() - today.getTime() < (1000 * 60 * 60 * 24 * 7)) {
		getById("no-events-happening-soon-paragraph").style.display = "none";
		getById("events-happening-soon").appendChild(newEventLink);
	} else {
		getById("no-events-happening-later-paragraph").style.display = "none";
		getById("events-happening-later").appendChild(newEventLink);
	}
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