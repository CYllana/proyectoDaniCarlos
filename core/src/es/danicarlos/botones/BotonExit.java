package es.danicarlos.botones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class BotonExit extends Boton { // Botón que permitirá salir del juego

	public BotonExit(float centroXPantalla, float centroYPantalla) {
		super(centroXPantalla, centroYPantalla);
		texture = new Texture(Gdx.files.internal("close.png")); // Se asigna textura. Muy importante!!
	}

	@Override
	protected void funcionamiento() {
		Gdx.app.exit(); // Cierra la aplicación
	}
}