package es.danicarlos.botones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BotonGirar extends Botonc{

	public BotonGirar(Texture texture, float x, float y) {
		super(x,y);
		this.texture = texture; // Se asigna textura. Muy importante!!
	}

	@Override
	protected void funcionamiento() {
		
		//Screens.juego.setScreen(Screens.GAMESCREEN); // Se asigna la pantalla de juego
	}

	public boolean sePulsaElBoton() { // Esta función privada sirve para comprobar si se pulsa el botón.
		return Gdx.input.isTouched() && Gdx.input.getX() >= xMinima && Gdx.input.getX() <= xMaxima && // Devuelve true si se pulsa dentro de los límites
				Gdx.input.getY() >= yMinima && Gdx.input.getY() <= yMaxima;
	}
	
	
}