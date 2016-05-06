package es.danicarlos.proyecto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class Pelota {
	private static final float SPEED=200;
	private Texture textura;
	private Circle bordes;
	
	public Pelota(Float x, float y){
		textura= new Texture(Gdx.files.internal("pelota.png"));
		//bordes= new Circle
	}

}
