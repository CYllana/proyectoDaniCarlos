package es.danicarlos.proyecto;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Pelota {
	private static final float SPEED=300;
	private Sprite textura;
	private Circle bordes;
	private float posicionX, posicionY;
	private float radio;
	private Rueda miJuego;
	private float xa =3;
	private float ya =6;
	private Vector2 miVectorUni;
	private Juego juego;
	
	public Pelota(Sprite textura,float x, float y, Juego miJuego){
		this.textura= textura;
		//bordes=new Circle(x,y, textura.getHeight());
		//bordes= new Circle
		posicionX=x;
		posicionY=y;
		radio=textura.getWidth()/2;
		this.miJuego=miJuego.getMiRueda();
		miVectorUni= new Vector2();
		juego=miJuego;
		bordes=new Circle(posicionX,posicionY, radio);
		
	}
	public void draw(SpriteBatch batch){
		update();
		batch.draw(textura, posicionX-textura.getHeight()/2, posicionY-textura.getHeight()/2);
	}
	
	//Regular el tiempo con milisegundos para que tenga una velocidad fija para todas las maquinas
	public void update(){
		movimientoPelota();
		
		
		
		
	}
	public Circle getBordes(){
		return bordes;
	}
	private boolean choqueRueda(){
		 	double distancia=distanciaPuntos(posicionX,posicionY,miJuego.getxCentro(),miJuego.getyCentro());
		 	int retval = Double.compare(distancia,(double) miJuego.getRadio()-radio);	
			if(retval>0){
				return true;
			}
			return false;
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
	public Sprite getTextura() {
		return textura;
	}
	public void setTextura(Sprite textura) {
		this.textura = textura;
	}
	public float getPosicionX() {
		return posicionX;
	}
	public void setPosicionX(float posicionX) {
		this.posicionX = posicionX;
	}
	public float getPosicionY() {
		return posicionY;
	}
	public void setPosicionY(float posicionY) {
		this.posicionY = posicionY;
	}
	public float getRadio() {
		return radio;
	}
	public void setRadio(float radio) {
		this.radio = radio;
	}
	public Rueda getMiJuego() {
		return miJuego;
	}
	public void setMiJuego(Rueda miJuego) {
		this.miJuego = miJuego;
	}
	public void setBordes(Circle bordes) {
		this.bordes = bordes;
	}
	private float aleatorio(){
		Random rnd=new Random();
		float valor=rnd.nextInt(5);
		float posneg=rnd.nextInt(2)+1;
		if(posneg==1){
			posneg=1;
		}else{
			posneg=-1;
		}
		return posneg*valor;
	}
	private double getAngulo(){
		//System.out.println((-juego.getMiRueda().getxCentro()+this.posicionX)/(juego.getMiRueda().getRadio()));
		double rad=Math.acos((-juego.getMiRueda().getxCentro()+this.posicionX)/(juego.getMiRueda().getRadio()));
		if(this.posicionY>juego.getMiRueda().getyCentro()){
			return (Math.toDegrees(rad));
		}
			return (360-Math.toDegrees(rad));
		
	}
	private void reboteAleatorio(){
		ya=aleatorio();
		xa=aleatorio();
		boolean valido=false;
		while(!valido){
			double distancia=distanciaPuntos(posicionX+xa,posicionY+ya,miJuego.getxCentro(),miJuego.getyCentro());
			int retval = Double.compare(distancia, miJuego.getRadio()-radio);	
			if(retval>0){
				ya=aleatorio();
				xa=aleatorio();
				
				while (Math.sqrt(Math.pow(xa,2)+Math.pow(ya,2))!=5){
					ya=aleatorio();
					xa=aleatorio();
				}
				
			}else if((Math.sqrt(Math.pow(xa,2)+Math.pow(ya,2))==5)){
						valido=true;
			}
			while (Math.sqrt(Math.pow(xa,2)+Math.pow(ya,2))!=5){
				ya=aleatorio();
				xa=aleatorio();
			}
		}
	}
		private void revoteReturn(){
			if(choqueRueda()){
				//reboteAleatorio();
				//System.out.println("PASO");
				xa=xa*-1;
				ya=ya*-1;
				
			}
			
			
		}
		
	private void movimientoPelota(){
		if(choqueRueda()){
			reboteAleatorio();
			System.out.println("xa:::>"+xa);
			//revoteNormal();
			//System.out.println("PASO");
			posicionX=posicionX+xa;
			posicionY=posicionY+ya;
			bordes.setX(posicionX);
			bordes.setY(posicionY);
			
		}else{
			posicionX=posicionX+xa;
			posicionY=posicionY+ya;
			bordes.setX(posicionX);
			bordes.setY(posicionY);
			
		}
		
	}
	private void revoteNormal(){
		
		if(xa>0 &&ya>0){
			xa=xa*-1;	
			ya=ya*-1;
		}
		if(xa>0 && ya<0){
			ya=ya*-1;	
		}if(ya>0&&xa<0){
			xa=xa*-1;
		}if(xa<0&&ya<0){
			xa=xa*-1;	
			ya=ya*-1;
		}if(xa>0&&ya>0){
			xa=xa*-1;
			ya=ya*-1;
			
		}
		
			
			
		
		
		
	}

	
}
