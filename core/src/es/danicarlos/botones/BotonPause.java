package es.danicarlos.botones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BotonPause extends Botonc{

	public BotonPause(float centroXPantalla, float centroYPantalla) {
		super(centroXPantalla, centroYPantalla);
		texture = new Texture(Gdx.files.internal("pause.png")); // Se asigna textura. Muy importante!!
	}

	@Override
	protected void funcionamiento() {
		System.out.println("pausee");
		//Screens.juego.setScreen(Screens.GAMESCREEN);
		 // Se asigna la pantalla de juego
		//Screens.juego.setScreen(Screens.GAMESCREEN);
		
		
	}
	public boolean sePulsaElBoton() { // Esta función privada sirve para comprobar si se pulsa el botón.
		return Gdx.input.isTouched() && Gdx.input.getX() >= xMinima && Gdx.input.getX() <= xMaxima && // Devuelve true si se pulsa dentro de los límites
				Gdx.input.getY() >= yMinima && Gdx.input.getY() <= yMaxima;
	}


}