package es.danicarlos.botones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

import es.danicarlos.proyecto.Juego;
import es.danicarlos.ventanas.Screens;

public class BotonPlay extends Boton{

	public BotonPlay(float centroXPantalla, float centroYPantalla) {
		super(centroXPantalla, centroYPantalla);
		texture = new Texture(Gdx.files.internal("play.png")); // Se asigna textura. Muy importante!!
	}

	@Override
	protected void funcionamiento() {
		System.out.println("play");
		 // Se asigna la pantalla de juego
		//Screens.juego.setScreen(Screens.GAMESCREEN);
		
		
	}


}
