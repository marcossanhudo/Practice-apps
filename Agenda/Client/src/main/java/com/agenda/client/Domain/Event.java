package com.agenda.client.Domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Event {

	@Id
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "time")
	private LocalTime time;

	@Column(name = "place")
	private String place;

	@Column(name = "creatorId")
	private Long creatorId;

	@Column(name = "idsOfInvitees")
	private ArrayList<Long> idsOfInvitees;

	@Column(name = "idsOfInviteesWhoAccepted")
	private ArrayList<Long> idsOfInviteesWhoAccepted;

	public Event() {}

	public Event(Long id) {
		this.id = id;
	}

	public Long id() {
		return this.id;
	}

	public HashMap<String, String> toJSON() {
		HashMap<String, String> json = new HashMap<>();
		json.put("id", this.id.toString());
		json.put("name", this.name);
		json.put("description", this.description);
		json.put("date", this.date.toString());
		json.put("time", this.time.toString());
		json.put("place", this.place);
		json.put("creatorId", this.creatorId.toString());
		return json;
	}

	public String name() {
		return this.name;
	}

	public Event name(String name) {
		this.name = name;
		return this;
	}

	public String description() {
		return this.description;
	}

	public Event description(String description) {
		this.description = description;
		return this;
	}

	public LocalDate date() {
		return this.date;
	}

	public Event date(LocalDate date) {
		this.date = date;
		return this;
	}

	public LocalTime time() {
		return this.time;
	}

	public Event time(LocalTime time) {
		this.time = time;
		return this;
	}

	public String place() {
		return this.place;
	}

	public Event place(String place) {
		this.place = place;
		return this;
	}

	public Long creatorId() {
		return this.creatorId;
	}

	public Event creatorId(Long creatorId) {
		this.creatorId = creatorId;
		return this;
	}

	public ArrayList<Long> idsOfInvitees() {
		return this.idsOfInvitees;
	}

	public Event idsOfInvitees(ArrayList<Long> idsOfInvitees) {
		this.idsOfInvitees = idsOfInvitees;
		return this;
	}

	public boolean addIdOfInvitee(Long idOfInvitee) {
		if (!this.idsOfInvitees.contains(idOfInvitee)) {
			this.idsOfInvitees.add(idOfInvitee);
			return true;
		}
		return false;
	}

	public boolean deleteIdOfInvitee(Long idOfInvitee) {
		if (this.idsOfInvitees.contains(idOfInvitee)) {
			this.idsOfInvitees.remove(idOfInvitee);
			return true;
		}
		return false;
	}

	public int countIdsOfInvitees() {
		return this.idsOfInvitees.size();
	}

	public ArrayList<Long> idsOfInviteesWhoAccepted() {
		return this.idsOfInviteesWhoAccepted;
	}

	public Event idsOfInviteesWhoAccepted(ArrayList<Long> idsOfInviteesWhoAccepted) {
		this.idsOfInviteesWhoAccepted = idsOfInviteesWhoAccepted;
		return this;
	}

	public boolean addIdOfInviteeWhoAccepted(Long idOfInviteeWhoAccepted) {
		if (!this.idsOfInviteesWhoAccepted.contains(idOfInviteeWhoAccepted)) {
			this.idsOfInviteesWhoAccepted.add(idOfInviteeWhoAccepted);
			return true;
		}
		return false;
	}

	public boolean deleteIdOfInviteeWhoAccepted(Long idOfInviteeWhoAccepted) {
		if (this.idsOfInviteesWhoAccepted.contains(idOfInviteeWhoAccepted)) {
			this.idsOfInviteesWhoAccepted.remove(idOfInviteeWhoAccepted);
			return true;
		}
		return false;
	}

	public int countIdsOfInviteesWhoAccepted() {
		return this.idsOfInviteesWhoAccepted.size();
	}

}