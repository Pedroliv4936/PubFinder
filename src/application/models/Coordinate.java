package application.models;

/**
 * Serve para armazenar e utilizar coordenadas X e Y.
 * @author Franco Zalamena e Pedro Oliveira
 */
public class Coordinate {
	private double x, y;

	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Recebe uma outra coordenada e devolve a distancia entre esta e a fornecida.
	 * @param other outra coordenada para se fazer a distância
	 * @return distancia medida
	 */
	public double distanceFrom(Coordinate other) {
		return Math.sqrt(Math.pow(other.getX() - getX(), 2) + Math.pow(other.getY() + getY(), 2));
	}
}

