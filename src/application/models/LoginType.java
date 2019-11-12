package application.models;

public enum LoginType {

	ADMIN_LOGIN("Utilizador logou como Administrador"),
	USER_LOGIN("Utilizador logou como Administrador"),
	WRONG_USERNAME("Utilizador errou seu nome de usuario"),
	WRONG_PASSWORD("Utilizador errou sua senha");
	
	private String description;
	
	private LoginType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
