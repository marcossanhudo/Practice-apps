var eventDetails = {};

populatePage();
getById("edit-details-button").onclick = () => {  };
getById("reschedule-event-button").onclick = () => {  };
getById("cancel-event-button").onclick = () => { getById("cancel-event-warning").style.display = "block"; };
getById("cancel-event-cancel-event-button").onclick = () => { cancelEvent(eventDetails); };
getById("cancel-event-dont-cancel-button").onclick = () => { getById("cancel-event-warning").style.display = "none"; };

async function cancelEvent() {
	await fetch("http://localhost:8000/cancel-event/" + eventDetails.id, { method: "DELETE" });
	window.location.href = "http://localhost:8000/home.html";
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
		getById("event-place-paragraph").innerHTML = "At " + eventDetails.place;
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