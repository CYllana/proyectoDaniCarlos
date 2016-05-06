package es.danicarlos.proyecto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class BotonGirar extends Boton{

	public BotonGirar(Texture texture, float x, float y) {
		super(texture, x,y);
		this.texture = texture; // Se asigna textura. Muy importante!!
	}

	@Override
	protected void funcionamiento() {
		System.out.println("hola");
		//Screens.juego.setScreen(Screens.GAMESCREEN); // Se asigna la pantalla de juego
	}

	@Override
	protected boolean sePulsaElBoton() { // Esta función privada sirve para comprobar si se pulsa el botón.
		return Gdx.input.isTouched() && Gdx.input.getX() >= xMinima && Gdx.input.getX() <= xMaxima && // Devuelve true si se pulsa dentro de los límites
				Gdx.input.getY() >= yMinima && Gdx.input.getY() <= yMaxima;
	}
	
	
}