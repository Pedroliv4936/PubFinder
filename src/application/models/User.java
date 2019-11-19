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
	private ObservableList<Drink> favoriteDrinks;
	private boolean admin;

	protected User(int id, String name, String username, String password, String email, Date birthday,
			ObservableList<Drink> favoriteDrinks, boolean admin) {
		this.favoriteDrinks = favoriteDrinks;
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.birthday = birthday;
		this.admin = admin;
	}

	public User(int id, String name, String username, String password, String email, Date birthday,
			ObservableList<Drink> favoriteDrinks) {
		this.favoriteDrinks = favoriteDrinks;
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.birthday = birthday;
		this.admin = false;
	}

	public boolean isAdmin() {
		return admin;
	}

	public ObservableList<Drink> getFavoriteDrinks() {
		return favoriteDrinks;
	}

	public void addFavoriteDrink(Drink newDrink) {
		favoriteDrinks.add(newDrink);
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
}
