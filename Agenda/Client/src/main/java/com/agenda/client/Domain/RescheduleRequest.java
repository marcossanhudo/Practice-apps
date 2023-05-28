package Domain;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class RescheduleRequest {

	@Id
	private Long id;

	@Column(name = "invitationId")
	private Long invitationId;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "time")
	private LocalTime time;

	@Column(name = "status")
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

	public RescheduleRequest time(LocalTime time) {
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