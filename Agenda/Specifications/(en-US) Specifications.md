# Specifications

## Description
A simple app that allows users to create events for their personal agendas, and to invite other users to those events.

## General features
The user should be able to ...
- review the events in his personal agenda.
- review the details of specific events in his personal agenda.
- add an event to his personal agenda.
- edit the details of an event in his personal agenda.
- reschedule an event in his personal agenda.
- delete/cancel an event from his personal agenda.
- invite another user to an event.
- receive from other users, invitations to events.
- change his acceptance situation ({ accepted, declined }) to events he's invited to.
- define his routine, and define times when he's definitely not available for events.
- define the important dates of his life.

When a user is invited to an event, the user should be able to ...
- accept the invitation (always notifies the inviting user).
- "assertively" decline the invitation (decline and notify the inviting user).
- "discretly" decline the invitation (decline and not notify the inviting user).

If the user starts to use a feature, and then changes his mind, a way to cancel the interaction should be prominent and accessible as soon as possible.

## Feature decision tree
```
Feature 1: User tries to add an event to his personal agenda.
	If (there's already at least one event at that date and time) {
		Notify the user about the potential conflict.
		Require the user to chose from one of the following options: {
			* Schedule the new event, and cancel the existing ones.		// pick events to keep or cancel?
			* Don't schedule the new event, and keep the existing ones.
			* Schedule the new event, and keep the existing ones.
		}
	} else {
		Add event to user's personal agenda.
		Communicate addition success.
	} end of feature 1.

Feature 2: User tries to edit the details of an event in his personal agenda.
	If (the event has invitees
		&& the invitees have !(declined the invitation)) {
			?
	} else {
		Save the detail changes.
	} end of feature 2.

Feature 3: User tries to reschedule an event in his personal agenda.
	If (there's already an event at that date and time) {
		Notify the user about the potential conflict.
		Require the user to chose from one of the following options: {
			* Schedule the new event, and cancel the existing ones.		// pick events to keep or cancel?
			* Don't schedule the new event, and keep the existing ones.
			* Schedule the new event, and keep the existing ones.
		}
	} else if (the event has invitees
		&& the invitees have accepted the invitation
		&& there's already an event at that date and time, in any of the event's invitees agendas) {
		?
	} else {
		Reschedule the event in the user's personal agenda.
		
		If (the event has invitees who have accepted the invitation) { // what if the user is stalling on accepting to wait for a better date/time
			Notify all invitees about the reschedule.
			Require all invitees to accept the invitation again.
		}
	} end of feature 3.

Feature 4: User tries to cancel an event in his personal agenda.
	If (the event has invitees
		&& the invitees have !(declined the invitation)) {
		Delete the event from the user's personal agenda.
		Notify all invitees who accepted the invitation.
		For each (invitee who accepted the invitation) {
			Give the invitee the option to keep the event in his own personal agenda.
		}
	} else {
		Delete the event from the user's personal agenda.
		Communicate deletion success.
	} end of feature 4.

Feature 5: User tries to send an invitation to event, to another user.
	Require the user to choose invitees.
	For each (invitee that the user chose) {
		Invoke feature 6.
	}
	Communicate success in sending invitations (in general, not for each invitee).
	end of feature 5.

Feature 6: User receives an invitation to an event.
	Require the user to choose from one of the following options: {
		* Accept the invitation.
		* Ask for a reschedule.
		* Assertively decline the invitation.
		* Discretly decline the invitation.
	}

	Feature 6.1: If (the user chooses to accept the invitation) {
		Notify the invitee.
		Save invitation as accepted.
		Add the event to the user's personal agenda.
	} end of feature 6.

	Feature 6.2: If (the user asks for a reschedule) {
		Require the user to propose a new date.
		Require the user to propose a new time.
		Allow the user to send the proposal.

		If (the user chooses to send the proposal) {
			If (the proposal is successfully sent) {
				Notify the invitee about the success.
				Notify the inviter about the proposal.
				Require the inviter to choose from one of the following options: {
					* Accept the proposal.
					* Decline the proposal.
				}
			} else {
				?
			}
		}
	} end of feature 6.

	Feature 6.3: If (the user chooses to assertively decline the invitation) {
		Notify the invitee.
		Save the invitation as refused.
	} end of feature 6.

	Feature 6.4: If (the user chooses to discretly decline the invitation) {
		Save the invitation as refused.
	} end of feature 6.
	
Feature 7: User tries to change his acceptance situation to an event.

Feature 8: User tries to define his routine.
	Feature 8.1: User adds a routine event.
	Feature 8.2: User changes an event's details.
	Feature 8.3: User deletes an event from his routine.
	
Feature 9: User tries to save an important, recurring date.
	Feature 8.1: User creates an event.
	Feature 8.2: User changes an event's details.
	Feature 8.3: User deletes an event from his important date agenda.
```

## Data
```
Event: {
	(text, never null) a name;
	(text) a description;
	(date, never null) the date when it's happening;
	(time, never null) the time when it's happening;
	(text) the place where it's happening;
	(user, never null) the user who created the event;
	(user[]) users to whom an invitation to the event has been sent;
	(user[]) users who have accepted an invitation to the event;
}

User: {
	(text, never null) a name;
}

Invitation: {
	(event, never null) event;
	(user, never null) inviter;
	(user, never null) invitee;
	(enum { pending, accepted, declined }, never null) status;
}

Reschedule request: {
	(invitation, never null) invitation;
	(date, never null) proposal for new date;
	(time, never null) proposal for new time;
	(enum { pending, accepted, declined }, never null) status;
}

Routine event, important date: {
	(text, never null) a name;
	(text) a description;
	(array of recurrences) the ways through which the event recurs;
	(time, never null) the time when it's happening;
	(text) the place where it's happening;
	(user, never null) the user who created the routine event;
}

Recurrence: {
	(array of numbers) when it happens each cycle;
		// when "scale of cycles" is:
		// - hour: the array is not used;
		// - day: the array is not used;
		// - week: the array uses numbers 0 through 6 to represent days Sunday through Saturday of each week;
		// - month: the array uses numbers 1 through 31 to represent days 1 through 31 of each month;
	(enum { hour, day, week, month }) scale of cycles;
	(number) number of cycles from event to event;
}
```

## Features, in Gherkin
```Gherkin
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
```
