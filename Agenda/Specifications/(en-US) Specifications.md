# Specifications

## Description
A simple app that allows users to create events for their personal agendas, and to invite other users to those events.

## General features
The user should be able to ...
- add an event to his personal agenda.
- edit the details of an event in his personal agenda.
- reschedule an event in his personal agenda.
- delete/cancel an event from his personal agenda.
- invite another user to an event.
- receive from other users, invitations to events.
- change his acceptance situation ({ accepted, declined }) to events he's invited to.

When a user is invited to an event, the user should be able to ...
- accept the invitation (always notifies the inviting user).
- "assertively" decline the invitation (decline and notify the inviting user).
- "discretly" decline the invitation (decline and not notify the inviting user).

## Feature decision tree
```
Feature 1: User tries to add an event to his personal agenda.
	If (there's already an event at that date and time) {
		?
	} else {
		Add event to user's personal agenda.
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
		?
	} else if (the event has invitees
		&& the invitees have accepted the invitation
		&& there's already an event at that date and time, in any of the event's invitees agendas) {
		?
	} else {
		Reschedule the event in the user's personal agenda.
		Notify all invitees about the reschedule.
		Require all invitees to accept the invitation again.
	} end of feature 3.

Feature 4: User tries to cancel an event in his personal agenda.
	If (the event has invitees
		&& the invitees have !(declined the invitation)) {
			?
	} else {
		Delete the event from the user's personal agenda.
	} end of feature 4.

Feature 5: User tries to send an invitation to event, to another user.
	Require the user to choose invitees.
	For each (invitee that the user chose) {
		Invoke feature 6.
	} end of feature 5.

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
```

## Data
```
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
