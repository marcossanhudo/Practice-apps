var eventDetails = {};

enableEditDetailsForm(false);
enableRescheduleEventForm(false);
getById("cancel-event-warning").style.display = "none";
populatePage();
getById("edit-details-button").onclick = () => { enableEditDetailsForm(true) };
getById("reschedule-event-button").onclick = () => { enableRescheduleEventForm(true) };
getById("edit-form-cancel-edit-button").onclick = () => { enableEditDetailsForm(false) };
getById("reschedule-form-cancel-reschedule-button").onclick = () => { enableRescheduleEventForm(false) };
getById("cancel-event-button").onclick = () => { getById("cancel-event-warning").style.display = "block"; };
getById("cancel-event-cancel-event-button").onclick = () => { cancelEvent(eventDetails); };
getById("cancel-event-dont-cancel-button").onclick = () => { getById("cancel-event-warning").style.display = "none"; };

const linkToHome = "http://localhost:8000/home.html";
const editDetailsEndpoint = "http://localhost:8000/edit-event-details";
const rescheduleEventEndpoint = "http://localhost:8000/reschedule-event";
const cancelEventEndpoint = "http://localhost:8000/cancel-event/";

function enableEditDetailsForm (enable) {
	switch (enable) {
		case false:
			getById("edit-details-form").style.display = "none";
			break;
		default:
		case true:
			getById("edit-details-name-input").value = eventDetails.name;
			getById("edit-details-place-input").value = eventDetails.place;
			getById("edit-details-description-input").value = eventDetails.description;
			getById("edit-details-form").style.display = "block";
			break;
	}
}

function enableRescheduleEventForm (enable) {
	switch (enable) {
		case false:
			getById("reschedule-event-form").style.display = "none";
			break;
		default:
		case true:
			getById("reschedule-date-input").value = eventDetails.date;
			getById("reschedule-time-input").value = eventDetails.time;
			getById("reschedule-place-input").value = eventDetails.place;
			getById("reschedule-event-form").style.display = "block";
			break;
	}
}

getById("edit-details-form").addEventListener("submit", async function (event) {
	event.preventDefault();
	enableEditDetailsForm(false);
	await editDetails();
	setTimeout(function () {
		populatePage();
	}, 300);
});

getById("reschedule-event-form").addEventListener("submit", async function (event) {
	event.preventDefault();
	enableRescheduleEventForm(false);
	await rescheduleEvent();
	setTimeout(function () {
		populatePage();
	}, 300);
});

async function editDetails() {
	requestBody = "eventId=" + eventDetails.id +
		"&" + "name=" + getById("edit-details-name-input").value +
		"&" + "place=" + getById("edit-details-place-input").value +
		"&" + "description=" + getById("edit-details-description-input").value;

	await fetch(editDetailsEndpoint, { method: "PATCH", headers: { "Content-Type": "application/x-www-form-urlencoded" }, body: requestBody });
}

async function rescheduleEvent() {
	requestBody = "eventId=" + eventDetails.id +
		"&" + "date=" + getById("reschedule-date-input").value +
		"&" + "time=" + getById("reschedule-time-input").value +
		"&" + "place=" + getById("reschedule-place-input").value;

	await fetch(rescheduleEventEndpoint, { method: "PATCH", headers: { "Content-Type": "application/x-www-form-urlencoded" }, body: requestBody });
}

async function cancelEvent() {
	await fetch(cancelEventEndpoint + eventDetails.id, { method: "DELETE" });
	window.location.href = linkToHome;
}

async function populatePage() {
	URLParams = getURLParams();
	eventDetails = {};
	if (URLParams.length > 0) {
		eventDetails = await getEventDetails(URLParams);
		
		renderEventDetails(eventDetails);
	}
}

function renderEventDetails(eventDetails) {
	getById("event-name-header").innerHTML = eventDetails.name;
	getById("event-datetime-paragraph").innerHTML = eventDetails.date + ", at " + eventDetails.time + ".";

	if (hasText(eventDetails.place))
		getById("event-place-paragraph").innerHTML = "At " + eventDetails.place + ".";
	else
		getById("event-place-paragraph").innerHTML = "No place provided.";

	if (hasText(eventDetails.description))
		getById("event-description-paragraph").innerHTML = eventDetails.description;
	else
		getById("event-description-paragraph").innerHTML = "No description provided.";
}

async function getEventDetails(params) {
	id = "";
	params.forEach(param => {
		if (param.key === "id")
			id = param.value;
	})
	response = await fetch("http://localhost:8000/event/" + id);
	responseJSON = await response.json();
	return responseJSON;
}

function getURLParams() {
	URLString = window.location.href;
	try {
		paramStrings = URLString.split("?")[1].split("&");
		foundParams = [];
		paramStrings.forEach(paramString => {
			keyValuePair = paramString.split("=");
			foundParams.push({ key: keyValuePair[0], value: keyValuePair[1] });
		});
		return foundParams;
	} catch (e) {
		;
	}
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