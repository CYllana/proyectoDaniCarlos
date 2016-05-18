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
		
		switch(nomb){
		case "VALIDAR":
			texture = new Texture(Gdx.files.internal("inicia.png"));
			break;
		case "GUARDAR":
			texture = new Texture(Gdx.files.internal("guardar.png"));
			break;
		case "ENVIAR":
			texture = new Texture(Gdx.files.internal("enviar.png"));
			break;
		}
	}

	@Override
	protected void funcionamiento() {
		// TODO Auto-generated method stub
		switch(nomb){
		case "VALIDAR":
			Screens.juego.setScreen(Screens.MAINSCREEN);
			break;
		case "GUARDAR":
			Screens.juego.setScreen(Screens.LOGINSCREEN);
			break;
		case "ENVIAR":
			Screens.juego.setScreen(Screens.LOGINSCREEN);
			break;
		}
		System.out.println("pasa");
		
	}

}
