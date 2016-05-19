package es.danicarlos.botones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Boton {
	protected Texture texture;
	protected Rectangle bordes;
	protected float xMinima;
	protected float yMinima;
	protected float xMaxima;
	protected float yMaxima;
	public Boton(float centroXPantalla, float centroYPantalla) {
		bordes = new Rectangle(centroXPantalla, centroYPantalla,75,75); //textura.getWidth(), textura.getHeight());	
		// Permite asignar los bordes del botón para su correcto funcionamiento.
		xMinima = bordes.x;
		yMaxima = Gdx.graphics.getHeight() - bordes.y;
		xMaxima = bordes.x + bordes.width;
		yMinima = Gdx.graphics.getHeight() - (bordes.y + bordes.height);
	}
	public Boton(float centroXPantalla, float centroYPantalla,String nomb) {
		if (nomb.equals("Validar")) {
		} else if (nomb.equals("NewRec")) {
		}
			
		bordes = new Rectangle(centroXPantalla-Gdx.graphics.getWidth()/6, centroYPantalla-Gdx.graphics.getHeight()/13,Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/7); 	
		// Permite asignar los bordes del botón para su correcto funcionamiento.
		xMinima = bordes.x;
		yMaxima = Gdx.graphics.getHeight() - bordes.y;
		xMaxima = bordes.x + bordes.width;
		yMinima = Gdx.graphics.getHeight() - (bordes.y + bordes.height);
	}

	public void draw(SpriteBatch batch) {
		batch.draw(texture, bordes.x, bordes.y, bordes.width, bordes.height);
	}

	public void update() {
		if(sePulsaElBoton())
			funcionamiento();
	}
	private boolean sePulsaElBoton() { // Esta función privada sirve para comprobar si se pulsa el botón.
		return Gdx.input.isTouched() && Gdx.input.getX() >= xMinima && Gdx.input.getX() <= xMaxima && // Devuelve true si se pulsa dentro de los límites
				Gdx.input.getY() >= yMinima && Gdx.input.getY() <= yMaxima;
	}

	protected abstract void funcionamiento(); // Método que implementarán las clases hijas y contendrá el comportamiento deseado

	// Getters and Setters ------------------------------------------------------------------------

	public Rectangle getBordes() {
		return bordes;
	}

	public void setBordes(Rectangle bordes) {
		this.bordes = bordes;
	}
	public void setX(float x) {
		this.bordes.x = x;
	}
	public void setY(float y) {
		this.bordes.y = y;
	}
	public float  getX() {
		return bordes.x;
	}
	public float getY() {
		return bordes.y;
	}

}