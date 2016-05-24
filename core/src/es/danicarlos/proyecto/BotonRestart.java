package es.danicarlos.proyecto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BotonRestart extends Boton{

	public BotonRestart(float centroXPantalla, float centroYPantalla) {
		super(centroXPantalla, centroYPantalla);
		texture = new Texture(Gdx.files.internal("playJuego.png")); // Se asigna textura. Muy importante!!
	}

	@Override
	protected void funcionamiento() {
		System.out.println("restart");
		//Screens.juego.setScreen(Screens.GAMESCREEN);
		 // Se asigna la pantalla de juego
		//Screens.juego.setScreen(Screens.GAMESCREEN);
		
		
	}
	protected boolean sePulsaElBoton() { // Esta función privada sirve para comprobar si se pulsa el botón.
		return Gdx.input.isTouched() && Gdx.input.getX() >= xMinima && Gdx.input.getX() <= xMaxima && // Devuelve true si se pulsa dentro de los límites
				Gdx.input.getY() >= yMinima && Gdx.input.getY() <= yMaxima;
	}


}