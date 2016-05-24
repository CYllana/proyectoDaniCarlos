package es.danicarlos.botones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

import es.danicarlos.ventanas.Screens;

public class BotonExit extends Boton { // Botón que permitirá salir del juego
	private String nomb;
	public BotonExit(float centroXPantalla, float centroYPantalla,String nomb) {
		super(centroXPantalla, centroYPantalla);
		this.nomb=nomb;
		if (nomb.equals("EXIT")){
			texture = new Texture(Gdx.files.internal("close.png")); // Se asigna textura. Muy importante!!
		}else{
			texture = new Texture(Gdx.files.internal("back2.png")); // Se asigna textura. Muy importante!!
		}
	}

	@Override
	protected void funcionamiento() {
		if (nomb.equals("EXIT")){
			Gdx.app.exit(); // Cierra la aplicación
		}else if (nomb.equals("LOGIN")){
			Screens.juego.setScreen(Screens.LOGINSCREEN);
		}else{
			Screens.juego.setScreen(Screens.MAINSCREEN);
		}
	}
}