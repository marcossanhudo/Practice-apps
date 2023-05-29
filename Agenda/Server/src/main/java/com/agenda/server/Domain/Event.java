import java.util.LocalDate;
import java.util.LocalTime;
import java.util.ArrayList;

public class Event {

	private Long id;
	private String name;
	private String description;
	private LocalDate date;
	private LocalTime time;
	private String place;
	private Long creatorId;
	private ArrayList<Long> idsOfinvitees;
	privates ArrayList<Long> idsOfInviteesWhoAccepted;

	public Event(Long id) {
		this.id = id;
	}

	public Long id() {
		return this.id;
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

	public Event name(LocalTime time) {
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
		this.idOfInvitees = idsOfInvitees;
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
		this.idOfInviteesWhoAccepted = idsOfInviteesWhoAccepted;
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