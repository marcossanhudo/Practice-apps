package Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class User {
	
	@Id
	private Long id;

	@Column(name = "name")
	private String name;

	public User(Long id) {
		this.id = id;
	}

	public Long id() {
		return this.id;
	}

	public String name() {
		return this.name;
	}

	public User name(String name) {
		this.name = name;
		return this;
	}

}