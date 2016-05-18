package es.danicarlos.botones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import es.danicarlos.ventanas.Screens;

public class BotonNew extends Boton {
	private String nomb;
	public BotonNew(float centroXPantalla, float centroYPantalla, String nomb) {
		super(centroXPantalla, centroYPantalla, nomb);
		this.nomb=nomb;
		switch(nomb){
		case "NEW":
			texture = new Texture(Gdx.files.internal("registrar.png"));
			break;
		case "RECUPERAR":
			texture = new Texture(Gdx.files.internal("recuperar.png"));
			break;
		}
		
	}

	@Override
	protected void funcionamiento() {
		System.out.println("hola1");
		switch(nomb){
		case "NEW":
			System.out.println("hola");
			Screens.juego.setScreen(Screens.NEWSCREEN);
			break;
		case "RECUPERAR":
			Screens.juego.setScreen(Screens.RECUPERARSCREEN);
			break;
		}
		
		
	}


}
