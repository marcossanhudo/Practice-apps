public class RescheduleRequest {

	private Long id;
	private Long invitationId;
	private LocalDate date;
	private LocalTime time;
	private InvitationStatus status;

	public RescheduleRequest(Long id) {
		this.id = id;
	}

	public Long id() {
		return this.id;
	}

	public Long invitationId() {
		return this.invitationId;
	}

	public RescheduleRequest invitationId(Long invitationId) {
		this.invitationId = invitationId;
		return this;
	}

	public LocalDate date() {
		return this.date;
	}

	public RescheduleRequest date(LocalDate date) {
		this.date = date;
		return this;
	}

	public LocalTime time() {
		return this.time;
	}

	public RescheduleRequest time(LocalDate time) {
		this.time = time;
		return this;
	}

	public InvitationStatus status() {
		return this.status;
	}

	public RescheduleRequest status(InvitationStatus status) {
		this.status = status;
		return this;
	}

}