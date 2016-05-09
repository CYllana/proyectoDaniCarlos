package es.danicarlos.proyecto;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Punto {
	private float x,y, radio;
	private Juego miJuego;
	private Sprite textura;

	
	
	public Punto(Sprite textura,Juego miJuego, float radio) {
		this.textura=textura;
		this.miJuego=miJuego;
		this.radio=radio;
		this.x=miJuego.getWidth()/2+aleatorio();
		this.y=miJuego.getHeight()/2+aleatorio();
	}

	public Punto(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	public static double distanciaPuntos(float x, float y, float centerX, float centerY){
		
		double respuest;
		double res1,res2;
		res1=centerX-x;  
		res2=centerY-y;
	    res1=Math.pow(res1, 2)+Math.pow(res2, 2);
	    respuest=Math.sqrt(res1);
	    return respuest;
	}
	public Vector2 nuevaPosicion(){
		double radioJuego= miJuego.getMiRueda().getRadio();
		float xCentral=miJuego.getMiRueda().getxCentro();
		float yCentral=miJuego.getMiRueda().getyCentro();
		
		return null;
		
		
	}

	
	private float aleatorio(){
		double radioJuego= miJuego.getMiRueda().getRadio();
		Random rnd=new Random();
		double valor=rnd.nextDouble()*radioJuego;
		float posneg=rnd.nextInt(2)+1;
		if(posneg==1){
			posneg=1;
		}else{
			posneg=-1;
		}
		return  (float) (posneg*valor);
	}
	public void draw(SpriteBatch batch){
		update();
		batch.draw(textura,x,y );
	}
	
	//Regular el tiempo con milisegundos para que tenga una velocidad fija para todas las maquinas
	public void update(){

		
		
	}
	

}
