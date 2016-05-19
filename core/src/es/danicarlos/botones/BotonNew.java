package es.danicarlos.botones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import es.danicarlos.ventanas.Screens;

public class BotonNew extends Boton {
	private String nomb;
	public BotonNew(float centroXPantalla, float centroYPantalla, String nomb) {
		super(centroXPantalla, centroYPantalla, nomb);
		this.nomb=nomb;
		if (nomb.equals("NEW")) {
			texture = new Texture(Gdx.files.internal("registrar.png"));

		} else if (nomb.equals("RECUPERAR")) {
			texture = new Texture(Gdx.files.internal("recuperar.png"));

		}
		
	}

	@Override
	protected void funcionamiento() {
		System.out.println("hola1");
		if (nomb.equals("NEW")) {
			System.out.println("hola");
			Screens.juego.setScreen(Screens.NEWSCREEN);

		} else if (nomb.equals("RECUPERAR")) {
			Screens.juego.setScreen(Screens.RECUPERARSCREEN);

		}
		
		
	}


}
