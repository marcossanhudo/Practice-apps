public class InvitationToEvent {

	private Long id;
	private Long eventId;
	private Long inviterId;
	private Long inviteeId;
	private InvitationStatus status;

	public InvitationtToEvent(Long id) {
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

	public InvitationStatus status(Invitation status) {
		this.status = status;
		return this;
	}

}