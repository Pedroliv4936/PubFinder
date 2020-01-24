package application.models;

import application.models.DAO.DrinkDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * Classe que representa a Entidade Pub. Possui endereco, drinks, um rating, o preco de entrada,  tipo de pub, coordenadas e se esta pendente.
 * @author Franco Zalamena e Pedro Oliveira
 *
 */
public class Pub extends Entity {

	private String address;
	private ObservableList<DrinkForSale> drinks = FXCollections.observableArrayList();
	private ObservableList<Image> images;
	private double rating;
	private double price;
	private PubType type;
	private Coordinate coordinates;
	private String openTime, closeTime;
	private boolean pending;

	public static final String DISCOTECA = "Discoteca";
	public static final String BAR = "Bar";
	public static final String SALAO_DE_JOGOS = "Salao de jogos";
	
	/**
	 * Construtor sem bebidas pre definidas
	 * @param id id do pub
	 * @param name nome do pub
	 * @param type tipo de pub
	 * @param price preco de entrada do pub
	 * @param address endereco fisico do Pub
	 * @param coordinates coordenadas do pub no Mapa
	 * @param pending se esta pendente de aprovacao
	 */
	public Pub(int id, String name, PubType type, double price, String address, Coordinate coordinates, boolean pending) {
		super(id, name);
		this.price = price;
		this.type = type;
		this.address = address;
		this.coordinates = coordinates;
		this.pending = pending;
		this.rating = 0;
	}	
	/**
	 * Construtor com lista de bebidas
	 * @param drinks lista de bebidas deste Pub
	 * @param id id do pub
	 * @param name nome do pub
	 * @param type tipo de pub
	 * @param price preco de entrada do pub
	 * @param address endereco fisico do Pub
	 * @param coordinates coordenadas do pub no Mapa
	 * @param pending se esta pendente de aprovacao
	 */
	public Pub(int id, String name, PubType type, double price, String address, Coordinate coordinates, ObservableList<DrinkForSale> drinks, boolean pending) {
		this(id,name,type,price,address,coordinates,pending);
		double rating = 0;
		for(DrinkForSale drink: drinks) {
			rating += drink.getRating();
		}
		this.rating = rating/drinks.size();
	}	

	/**
	 * Metodo para receber toda a informacao do pub em uma string.
	 * @return String com toda informacao necessaria do Pub.
	 */
	public String getPubInfo() {
		return name + " (" + type + ")" + " preco: " + price;
	}

	public double getPrice() {
		return price;
	}
	
	/**
	 * Metodo para receber a distancia para outro pub.
	 * @param pub pub para calcular a distancia
	 * @return distancia entre os 2 pubs
	 */
	public double distance(Pub pub) {
		return coordinates.distanceFrom(pub.getCoordinates());
	}

	/**
	 * Metodo para receber a distancia para outra coordenada.
	 * @param loc localizacao para calcular a distancia
	 * @return distancia entre os 2 pubs
	 */
	public double distance(Coordinate loc) {
		return coordinates.distanceFrom(loc);
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public PubType getType() {
		return type;
	}

	public void setType(PubType type) {
		this.type = type;
	}

	/**
	 * Escreve a informacao do Pub no console
	 */
	public void showPubInfo() {
		System.out.println("Id: " + id);
		System.out.println("Name: " + name);
		System.out.println("Rating: " + rating);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ObservableList<DrinkForSale> getDrinks() {
		drinks = DrinkDAO.getDrinks(this);
		return drinks;
	}

	public void setDrinks(ObservableList<DrinkForSale> drinks) {
		this.drinks = drinks;
	}

	public ObservableList<Image> getImages() {
		return images;
	}

	public void setImages(ObservableList<Image> images) {
		this.images = images;
	}

	public Coordinate getCoordinates() {
		return coordinates;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	
	public double getRating() {
		return rating;
	}

	/**
	 * Define o raitng do pub como a media de todas as suas bebidas.
	 */
	public void setRating() {
		double rating = 0;
		for(DrinkForSale drink : getDrinks()) {
			rating+=drink.getRating();
		}
		this.rating = rating / drinks.size();
	}

	public boolean isPending() {
		return pending;
	}
	
	public void aprove() {
		this.pending = false;
	}
}
