package es.danicarlos.proyecto;

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
	private float height, width;
	public Boton(Texture text, float x, float y) {
		height=Gdx.graphics.getHeight();
		width=Gdx.graphics.getWidth();
		//Texture textura = new Texture(Gdx.files.internal("exit.jpg")); // Para poner el ancho y alto de los botones. Suponemos que todos serán igual
		bordes = new Rectangle(x, y,height/12,height/12); //textura.getWidth(), textura.getHeight());
		xMinima = bordes.x;
		yMaxima = Gdx.graphics.getHeight() - bordes.y;
		xMaxima = bordes.x + bordes.width;
		yMinima = Gdx.graphics.getHeight() - (bordes.y + bordes.height);
		// Permite asignar los bordes del botón para su correcto funcionamiento.
		
	}

	public void draw(SpriteBatch batch) {
		batch.draw(texture, bordes.x, bordes.y, bordes.width, bordes.height);
	}

	public void update() {
		
		if(sePulsaElBoton())
			funcionamiento();
	}
	protected abstract boolean sePulsaElBoton();

	protected abstract void funcionamiento(); // Método que implementarán las clases hijas y contendrá el comportamiento deseado

	// Getters and Setters ------------------------------------------------------------------------

	public Rectangle getBordes() {
		return bordes;
	}

	public void setBordes(Rectangle bordes) {
		this.bordes = bordes;
	}
}