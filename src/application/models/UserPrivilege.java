package application.models;

public enum UserPrivilege {
	ADMIN(1, "Admininistrador"),
	USER(2, "Utilizador comum");
	private String description;
	private int id;
	
	private UserPrivilege(int id, String description) {
		this.description = description;
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}
	
	public static UserPrivilege getPrivilege(int id) {
		return UserPrivilege.values()[id];
	}
}
