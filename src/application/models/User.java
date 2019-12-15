package application.models;

import java.sql.Date;

import javafx.collections.ObservableList;

public class User {

	private int id;
	private String name;
	private String username;
	private String password;
	private String email;
	private Date birthday;
	private int cellphone;
	private ObservableList<Drink> favoriteDrinks;
	private UserPrivilege privilege;

	/**
	 * Construtor para criar qualquer tipo de utilizador
	 * @param id id do user
	 * @param name nome do user
	 * @param username username do user
	 * @param password password do user
	 * @param email email do user
	 * @param birthday data de nascimento do user
	 * @param cellphone telemovel do user
	 * @param privilege qual o tipo de privilegio do utilizador.
	 */
	public User(int id, String name, String username, String password, String email, Date birthday, int cellphone,
			UserPrivilege privilege) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.birthday = birthday;
		this.cellphone = cellphone;
		this.setPrivilege(privilege);
	}

	/**
	 * Contrutor para criar um utilizador com permissoes basicas de USER
	 * @param id id do user
	 * @param name nome do user
	 * @param username username do user
	 * @param password password do user
	 * @param email email do user
	 * @param birthday data de nascimento do user
	 * @param cellphone telemovel do user
	 */
	public User(int id, String name, String username, String password, String email, Date birthday, int cellphone) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.birthday = birthday;
		this.cellphone = cellphone;
		this.setPrivilege(UserPrivilege.USER);
	}

	public ObservableList<Drink> getFavoriteDrinks() {
		return favoriteDrinks;
	}

	public void addFavoriteDrink(Drink newDrink) {
		favoriteDrinks.add(newDrink);
	}

	public int getCellphone() {
		return cellphone;
	}

	public void setCellphone(int cellphone) {
		this.cellphone = cellphone;
	}

	public void removeFavoriteDrink(Drink newDrink) {
		favoriteDrinks.remove(newDrink);
	}

	public void setFavoriteDrinks(ObservableList<Drink> favoriteDrinks) {
		this.favoriteDrinks = favoriteDrinks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public UserPrivilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(UserPrivilege privilege) {
		this.privilege = privilege;
	}
}
