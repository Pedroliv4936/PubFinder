package application.models;

/**
 * Tipos de utilizadores existentes na aplicacao.
 * @author Franco Zalamena e Pedro Oliveira
 *
 */
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
	
	/**
	 * Metodo para saber o tipo de privilegio dado o seu ID.
	 * @param id id do user privilege
	 * @return o USERPRIVILEGE com o id do parametro
	 */
	public static UserPrivilege getPrivilege(int id) {
		return UserPrivilege.values()[id];
	}
}
