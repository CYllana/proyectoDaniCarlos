package es.danicarlos.botones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import es.danicarlos.ventanas.Screens;

public class BotonValidar extends Boton {
	private String nomb;
	public BotonValidar(float centroXPantalla, float centroYPantalla,String nomb) {
		super(centroXPantalla, centroYPantalla,"Validar");
		this.nomb=nomb;
		//texture = new Texture(Gdx.files.internal("buttonValidar.png"));

		if (nomb.equals("VALIDAR")) {
			texture = new Texture(Gdx.files.internal("inicia.png"));

		} else if (nomb.equals("GUARDAR")) {
			texture = new Texture(Gdx.files.internal("guardar.png"));

		} else if (nomb.equals("ENVIAR")) {
			texture = new Texture(Gdx.files.internal("enviar.png"));

		}
	}

	@Override
	protected void funcionamiento() {
		// TODO Auto-generated method stub
		if (nomb.equals("VALIDAR")) {
			Screens.juego.setScreen(Screens.MAINSCREEN);

		} else if (nomb.equals("GUARDAR")) {
			Screens.juego.setScreen(Screens.LOGINSCREEN);

		} else if (nomb.equals("ENVIAR")) {
			Screens.juego.setScreen(Screens.LOGINSCREEN);

		}
		System.out.println("pasa");
		
	}

}
