package es.danicarlos.proyecto;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Punto {
	private float x,y, radio;
	private Juego miJuego;
	private Sprite textura;
	private Circle bordes;	
	

	
	
	public Punto(Sprite textura,Juego miJuego) {
		this.textura=textura;
		this.miJuego=miJuego;
		this.radio=textura.getHeight();
		textura.setCenterX(radio);
		this.x=(+textura.getWidth()+miJuego.getWidth())/2+aleatorioX();
		
		this.y=(+textura.getWidth()+miJuego.getHeight())/2+aleatorioY();
		this.textura.setCenter(x, y);
		this.textura.setOriginCenter();
		bordes=new Circle(x,y,textura.getWidth()/2);
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
	
	
	
	public Circle getBordes() {
		return bordes;
	}

	public void setBordes(Circle bordes) {
		this.bordes = bordes;
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

	
	private float radioAleatorio(){
		double radioJuego=miJuego.getMiRueda().getRadio();
		Random rnd=new Random();
		double valor=rnd.nextDouble()*radioJuego-textura.getHeight();
	
		return (float)valor;
	}
	private float anguloAleatorio(){
		
		Random rnd=new Random();
		float valor=rnd.nextInt(360);
		return valor;
	}
	private float aleatorioX(){
		float radioJuego=radioAleatorio();
		float angulo=anguloAleatorio();
		return radioJuego*MathUtils.sinDeg(angulo);
	}
	private float aleatorioY(){
		float radioJuego=radioAleatorio();
		float angulo=anguloAleatorio();
		return radioJuego*MathUtils.cosDeg(angulo);
	}
	
	
	
	
	public void draw(SpriteBatch batch){
		
		batch.draw(textura,x-textura.getHeight()/2,y-textura.getHeight()/2 );
	}
	
	//Regular el tiempo con milisegundos para que tenga una velocidad fija para todas las maquinas
	public void update(){
		if(colision()){
			miJuego.setEstrellas(1);
			
			this.x=miJuego.getWidth()/2+aleatorioX();
			this.y=miJuego.getHeight()/2+aleatorioY();
			bordes.setPosition(x, y);
			
		}
		
		
	}
	public boolean colision(){
	
		return bordes.overlaps(miJuego.getMiPelota().getBordes());
		
	}
	

}
