package Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class InvitationToEvent {

	@Id
	private Long id;

	@Column(name = "eventId")
	private Long eventId;

	@Column(name = "inviterId")
	private Long inviterId;

	@Column(name = "inviteeId")
	private Long inviteeId;

	@Column(name = "status")
	private InvitationStatus status;

	public InvitationToEvent(Long id) {
		this.id = id;
	}

	public Long id() {
		return this.id;
	}

	public Long eventId() {
		return this.eventId;
	}

	public InvitationToEvent eventId(Long eventId) {
		this.eventId = eventId;
		return this;
	}

	public Long inviterId() {
		return this.inviterId;
	}

	public InvitationToEvent inviterId(Long inviterId) {
		this.inviterId = inviterId;
		return this;
	}

	public Long inviteeId() {
		return this.inviteeId;
	}

	public InvitationToEvent inviteeId(Long inviteeId) {
		this.inviteeId = inviteeId;
		return this;
	}

	public InvitationStatus status() {
		return this.status;
	}

	public InvitationToEvent status(InvitationStatus status) {
		this.status = status;
		return this;
	}

}