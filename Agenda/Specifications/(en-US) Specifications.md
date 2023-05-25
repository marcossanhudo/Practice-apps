# Specifications

## Description
A simple app that allows users to create events for their personal agendas, and to invite other users to those events.

## Features
The user should be able to ...
- add an event to his personal agenda.
- edit the details of an event in his personal agenda.
- reschedule an event in his personal agenda.
- delete/cancel an event from his personal agenda.
- invite another user to an event.

When a user is invited to an event, the user should be able to ...
- accept the invitation (always notifies the inviting user).
- "assertively" decline the invitation (decline and notify the inviting user).
- "discretly" decline the invitation (decline and not notify the inviting user).

## Data
Event: {
	(text, never null) a name;
	(text) a description;
	(date, never null) the date when it's happening;
	(time, never null) the time when it's happening;
	(text) the place where it's happening;
	(user, never null) user who created the event;
	(user[]) users to whom an invitation to the event has been sent;
	(user[]) users who have accepted an invitation to the event;
}

User: {
	(text, never null) a name;
}

## Features, in Gherkin

Feature: Adding an event to one's personal agenda
	When the user chooses to create an event,
	Then the app requires the user to specify the details which can never be null.

	Given the user has chosen to start creating an event,
	And the user has provided all the details that cannot be null,
	When the user tries to add the event to his agenda,
	Then the app adds the event to the user's agenda.

	Given the user has chosen to start creating an event,
	And the user has not provided all the details that cannot be null,
	When the user tries to add the event to his agenda,
	Then the app does not add the event to the user's agenda,
	And the app notifies the user about the missing details,
	And the app allows the user to add the missing details, or cancel the event addition.

Feature: Accepting an invitation
	Given the user has been invited to an event,
	When the user chooses to accept the invitation,
	Then the event is added to the user's agenda,
	And the inviting user is notified of the invite acceptance.

Feature: Assertively declining an invitation
	Given the user has been invited to an event,
	When the user chooses to assertively decline the invitation,
	Then the event is not added to the user's agenda,
	And the inviting user is notified of the invite refusal.

Feature: Discretly declining an invitation
	Given the user has been invited to an event,
	When the user chooses to discretly decline the invitation,
	Then the event is not added to the user's agenda,
	And the inviting user is not notified of the invite refusal.