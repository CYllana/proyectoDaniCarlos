package es.danicarlos.proyecto;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Pelota {
	private static final float SPEED=3;
	private Sprite textura;
	private Circle bordes;

	private float posicionX, posicionY, antiguaX, antiguaY;
	private float radio;
	private Juego miJuego;
	private float xa =SPEED;
	private float ya =SPEED;
	
	private Vector2 miVectorUni;
	private Juego juego;
	private int modoRebote;
	public enum miColor
	{
	   YELLOW, GREEN, RED, BLACK,BLUE, ORANGE;
	}
	
	
	private double angulo;
	
	public Pelota(Sprite textura,float x, float y, Rueda miRueda){
		this.textura= textura;
		//bordes=new Circle(x,y, textura.getHeight());
		//bordes= new Circle
		posicionX=x;
		posicionY=y;
		radio=textura.getWidth()/2;
		//this.miJuego=miRuego
		miVectorUni= new Vector2();
		this.textura.setCenter(x, y);
		this.textura.setOriginCenter();
	}

	public Pelota(Sprite textura,float x, float y, Juego miJuego){
		this.textura= textura;
		//bordes=new Circle(x,y, textura.getHeight());
		//bordes= new Circle
		posicionX=x;
		posicionY=y;
		radio=textura.getWidth()/2;
		this.miJuego=miJuego;
		miVectorUni= new Vector2();
		juego=miJuego;
		bordes=new Circle(posicionX,posicionY, radio);

	}

	public int getModoRebote() {
		return modoRebote;
	}
	public void setModoRebote(int modoRebote) {
		this.modoRebote = modoRebote;
	}
	public Circle getBordes(){
		return bordes;
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

	public void setBordes(Circle bordes) {
		this.bordes = bordes;
	}
	public void setAngulo(double angulo) {
		this.angulo =this.angulo+ angulo;
	}
	
	public float getAntiguaX() {
		return antiguaX;
	}
	public void setAntiguaX(float antiguaX) {
		this.antiguaX = antiguaX;
	}
	public float getAntiguaY() {
		return antiguaY;
	}
	public void setAntiguaY(float antiguaY) {
		this.antiguaY = antiguaY;
	}
	
	
	
	//Metodo que printa
	public void draw(SpriteBatch batch){
		
		batch.draw(textura, posicionX-textura.getHeight()/2, posicionY-textura.getHeight()/2);
	}

	//Regular el tiempo con milisegundos para que tenga una velocidad fija para todas las maquinas
	public void update(){
		movimientoPelota();

	}
	public boolean choqueRueda(){
		double distancia=distanciaPuntos(posicionX,posicionY,miJuego.getMiRueda().getxCentro(),miJuego.getMiRueda().getyCentro());
	 	int retval = Double.compare(distancia,(double) miJuego.getMiRueda().getRadio()-radio);	
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
	private void movimientoPelota(){
		if(choqueRueda()){
			miJuego.setChoqueBola(true);
			int miColor=miJuego.getMiRueda().bordercolor(getAngulo()).ordinal();
			
			miJuego.setChoqueBola(true);
			//System.out.println(modoRebote);
			if(miColor==1){
				reboteAleatorio();
				System.out.println("Aleatorio");
			}else if(miColor==2){
				System.out.println("Return");
				//transporter();
				revoteReturn();
			}else if(miColor==3){
				System.out.println("Vertical");

				revoteVertical();
			}else if (miColor==4){
				System.out.println("Horizontal");
				//revoteHorizontal();
				reboteAleatorio();
			}else if(miColor==5){
				reboteAleatorio();

			}else if(miColor==6){
				revoteDiagonal();
			}
			//reboteAleatorio();
			//revoteReturn();
			//revoteHorizontal();
			//revoteVertical()
			//System.out.println("xa:::>"+xa);
			//revoteNormal();
			//System.out.println("PASO");
			setAntiguaX(posicionX);
			setAntiguaY(posicionY);
			posicionX=posicionX+xa;
			posicionY=posicionY+ya;
			bordes.setX(posicionX);
			bordes.setY(posicionY);
			

		}else{
			//miJuego.setChoqueBola(false);
			posicionX=posicionX+xa;
			posicionY=posicionY+ya;
			bordes.setX(posicionX);
			bordes.setY(posicionY);

		}

	}
	
	
	
	private float puntoAleatorio(){
		Random rnd=new Random();
		float valor=rnd.nextInt(4);
		float posneg=rnd.nextInt(2)+1;
		if(posneg==1){
			posneg=1;
		}else{
			posneg=-1;
		}
		return posneg*valor;
	}
public double getAngulo(){
		//System.out.println((-juego.getMiRueda().getxCentro()+this.posicionX)/(juego.getMiRueda().getRadio()));
		double rad=Math.acos((-juego.getMiRueda().getxCentro()+this.posicionX)/(juego.getMiRueda().getRadio()-radio));
		if(this.posicionY>juego.getMiRueda().getyCentro()){
			setAngulo(Math.toDegrees(rad));
			return (Math.toDegrees(rad));
		}
		setAngulo(360-Math.toDegrees(rad));
		return (360-Math.toDegrees(rad));

	}
	private void reboteAleatorio(){
		ya=puntoAleatorio();
		xa=puntoAleatorio();
		boolean valido=false;
		while(!valido){
			if(!siguienteValido(xa,ya)){
				ya=puntoAleatorio();
				xa=puntoAleatorio();

				while (Math.sqrt(Math.pow(xa,2)+Math.pow(ya,2))!=SPEED){
					ya=puntoAleatorio();
					xa=puntoAleatorio();
				}

			}else if((Math.sqrt(Math.pow(xa,2)+Math.pow(ya,2))==SPEED)){
				valido=true;
			}
			while (Math.sqrt(Math.pow(xa,2)+Math.pow(ya,2))!=SPEED){
				ya=puntoAleatorio();
				xa=puntoAleatorio();
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

	
	private void revoteNormal(){
		//++
		calcularAngulo();
		while(!siguienteValido(xa, ya)){

		if(xa>0 &&ya>0){
			xa=xa*-1;		
		}
		//-+
		else if(xa<0 && ya>0){
			ya=ya*-1;
		//--	
		}else if(xa<0&&ya<0){
			xa=xa*-1;
		//+-
		}else if(xa<0&&ya<0){
			ya=ya*-1;

		}
		}

	}
	private void revoteHorizontal(){
		xa=-SPEED;
		ya=0;
		if(!siguienteValido(xa,ya)){
			xa=+SPEED;

		}
	}
	private void revoteVertical(){
		xa=0;
		ya=-SPEED;
		if(!siguienteValido(xa,ya)){
			ya=+SPEED;

		}
	}
	private void revoteDiagonal(){
		xa=-SPEED;
		ya=-SPEED;
		if(!siguienteValido(xa,ya)){
			xa=SPEED;
			ya=SPEED;

		}
	}
	private void gameOver(){
		xa=0;
		ya=0;
	
	}
	private void transporter(){
		xa=0;
		ya=0;
		posicionX=miJuego.getMiRueda().getxCentro()+(float) ((miJuego.getMiRueda().getRadio()-radio)*(Math.cos(Math.toRadians(miJuego.getMiRueda().getTransportX()))));
		double sin =Math.sin(Math.toRadians(miJuego.getMiRueda().getTransportX()));
		System.out.println();
		posicionY=miJuego.getMiRueda().getyCentro()+(float) ((miJuego.getMiRueda().getRadio()-radio)* (sin));
		
		System.out.println("sin="+sin+"     "+posicionX+" posiciones"+ posicionY +"angulo"+miJuego.getMiRueda().getTransportX());
	
	
	}
	private boolean siguienteValido(float xa, float xy){
		double distancia=distanciaPuntos(posicionX+xa,posicionY+ya,miJuego.getMiRueda().getxCentro(),miJuego.getMiRueda().getyCentro());
		int retval = Double.compare(distancia, miJuego.getMiRueda().getRadio()-radio);	
		if(retval>0){
			return false;
		}else{
			return true;
		}
	
	
	}
	private float calcularAngulo(){
		
		
		return 3;
	}






}
