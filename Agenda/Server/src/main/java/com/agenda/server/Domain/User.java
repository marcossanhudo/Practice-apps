public class User {

	private Long id;
	private String name;

	public User(int id) {
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