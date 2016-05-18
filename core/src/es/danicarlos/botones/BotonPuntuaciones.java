package es.danicarlos.botones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import es.danicarlos.ventanas.Screens;

public class BotonPuntuaciones extends Boton{

	public BotonPuntuaciones(float centroXPantalla, float centroYPantalla) {
		super(centroXPantalla, centroYPantalla);
		texture = new Texture(Gdx.files.internal("score.png")); // Se asigna textura. Muy importante!!
	}

	@Override
	protected void funcionamiento() {
		System.out.println("score");
		Screens.juego.setScreen(Screens.SCORESCREEN); // Se asigna la pantalla de puntuaciones
	}
}
