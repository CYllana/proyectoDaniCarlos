package es.danicarlos.proyecto;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Pelota {
	private float SPEED=5;
	private Sprite textura;
	private Circle bordes;

	private float posicionX, posicionY, antiguaX, antiguaY;
	private float radio;
	private Juego miJuego;
	private float xa;
	private float ya;
	private Rueda miRueda;
	private Vector2 miVectorUni;
	private Juego juego;
	private int bt;
	private int modoRebote;
	private boolean menu;
	public enum miColor
	{
		YELLOW, GREEN, RED, BLACK,BLUE, ORANGE;
	}


	private double angulo;

	public Pelota(Sprite textura,float x, float y, Rueda miRueda, int numero){
		this.textura= textura;
		this.miRueda=miRueda;
		posicionX=x;
		SPEED=2;
		bt=numero;
		posicionY=y;
		this.miRueda=miRueda;
		radio=textura.getWidth()/2;
		bordes=new Circle(posicionX,posicionY, radio);
		miVectorUni= new Vector2();
		
		menu=true;
		float direccion=inicioAleatorio();
		this.xa =SPEED*direccion;
		if(direccion==0){
			ya=0;
			while(ya==0){
				direccion=inicioAleatorio();
				this.ya =SPEED*direccion;
			}

		}else{
			this.ya =SPEED*direccion;
		}


	}

	public Pelota(Sprite textura,float x, float y, Juego miJuego){
		this.textura= textura;
		posicionX=x;
		posicionY=y;
		radio=textura.getWidth()/2;
		this.miJuego=miJuego;
		miVectorUni= new Vector2();
		juego=miJuego;
		bordes=new Circle(posicionX,posicionY, radio);
		menu=false;

		this.miRueda=juego.getMiRueda();
		float direccion=inicioAleatorio();
		this.xa =SPEED*direccion;
		if(direccion==0){
			ya=0;
			while(ya==0){
				direccion=inicioAleatorio();
				this.ya =SPEED*direccion;
			}

		}else{
			this.ya =SPEED*direccion;
		}

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
	public boolean isMenu() {
		return menu;
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
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
		batch.draw(textura, posicionX-textura.getWidth()/2, posicionY-textura.getHeight()/2);
	}

	//Regular el tiempo con milisegundos para que tenga una velocidad fija para todas las maquinas
	public void update(){

		movimientoPelota();
		
	}
	
	public boolean choqueRueda(){
		double distancia=distanciaPuntos(posicionX,posicionY,miRueda.getxCentro(),miRueda.getyCentro());
		int retval = Double.compare(distancia,(double)miRueda.getRadio()-radio);	
		if(retval>0){
			System.out.println("Print");
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

		//YELLOW, GREEN, RED, BLACK,BLUE, ORANGE;
		
		if(choqueRueda()){
			
			if(!menu){
			int miColor=miRueda.bordercolor(getAngulo()).ordinal();	
			miJuego.setChoqueBola(true);
			//System.out.println(modoRebote);
			aleatorio();
			//reboteAleatorio();
				/*if(miColor==0){
					reboteAleatorio();
					System.out.println("Aleatorio");
				}else if(miColor==1){
					System.out.println("Transport");
					transporter();
					revoteReturn();
				}else if(miColor==2){
					System.out.println("Diagonal");
					//transporter();
					revoteDiagonal();
				}else if(miColor==3){
					System.out.println("Game Over");
					//reboteAleatorio();
					revoteReturn();
					//gameOver();
				}else if (miColor==4){
					System.out.println("Horizontal");
					//revoteHorizontal();
					revoteHorizontal();
	

				}else if(miColor==5){
					System.out.println("Vertical");
					revoteVertical();
					
				
					

				}else if(miColor==0){
					reboteAleatorio();


				}
				*/
				}else{
					aleatorio();
					ya=(float) (ya*0.5);
					xa=(float) (xa*0.5);
					
					
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

		}private float inicioAleatorio(){
			Random rnd=new Random();
			float posneg=rnd.nextInt(3)+1;
			if(posneg==3){
				posneg=0;
			}
			else if(posneg==1){
				posneg=1;
			}else{
				posneg=-1;
			}
			return posneg;
		}
		private int direccion(){
			Random rnd=new Random();
			float posneg=rnd.nextInt(2)+1;
			//System.out.println("---------->"+posneg);
			if(posneg==1){
				posneg=1;
			}else{
				posneg=-1;
			}
			return (int) posneg;
		}


		private float puntoAleatorio(){
			Random rnd=new Random();
			
			float valor=rnd.nextInt((int)SPEED+1);
			float posneg=rnd.nextInt(2)+1;
			if(posneg==1){
				posneg=1;
			}else{
				posneg=-1;
			}
			return posneg*valor;
	
		}
		private float aleator(){
			Random rnd=new Random();
			
			float valor=rnd.nextInt(5 - 3 + 1) + 3;
			/*float posneg=rnd.nextInt(2)+1;
			if(posneg==1){
				posneg=1;
			}else{
				posneg=-1;
			}*/
			//return posneg*valor;
			return valor;
		}
		public double getAngulo(){
			//System.out.println((-juego.getMiRueda().getxCentro()+this.posicionX)/(juego.getMiRueda().getRadio()));
			double rad=Math.acos((-miRueda.getxCentro()+this.posicionX)/(miRueda.getRadio()-radio));
			if(this.posicionY>miRueda.getyCentro()){
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
					xa=aleator();
					System.out.println("Papayaaa");
					if(xa==0)ya=5*direccion();
					if(xa==3)ya=4*direccion();
					if(xa==4)ya=3*direccion();
					if(xa==5)ya=0*direccion();
					xa=xa*direccion();
					System.out.println(xa+"  "+ya);

				}else if((Math.sqrt(Math.pow(xa,2)+Math.pow(ya,2))==SPEED)){
					valido=true;
				}
				while (Math.sqrt(Math.pow(xa,2)+Math.pow(ya,2))!=SPEED){
					ya=puntoAleatorio();
					xa=puntoAleatorio();
				}
			}
		}
		private void aleatorio(){
			while(!siguienteValido(xa,ya)){
				
				xa=aleator();
				
				if(xa==0)ya=5*direccion();
				if(xa==3)ya=4*direccion();
				if(xa==4)ya=3*direccion();
				if(xa==5)ya=0*direccion();
				xa=xa*direccion();
				System.out.println(xa+"  "+ya);
			}
			System.out.println("ha salido");
			
		}


		private void revoteReturn(){
			if(choqueRueda()){
				//reboteAleatorio();
				//System.out.println("PASO");
				xa=xa*-1;
				ya=ya*-1;
				System.out.println(xa+"  "+ya);

			}
			System.out.println("ha salido");


		}


		private void revoteNormal(){
			//++
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
			System.out.println("x-->"+xa+"  y-->"+ya);
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
			double cos=Math.cos(Math.toRadians(miRueda.getTransportX()));
			ya=0;
			posicionX=miRueda.getxCentro()+(float) ((miRueda.getRadio()-radio)*(cos));
			double sin =Math.sin(Math.toRadians(miJuego.getMiRueda().getTransportX()));
			//System.out.println();
			posicionY=miRueda.getyCentro()+(float) ((miRueda.getRadio()-radio)*(sin));
			System.out.println("sin="+sin+"     "+posicionX+" posiciones"+ posicionY +"angulo"+miRueda.getTransportX());
			reboteAleatorio();

		}
		private boolean siguienteValido(float xa, float ya){
			
			//System.out.println(miRueda.getxCentro()+"  Rueda"+ miRueda.getyCentro());
			double distancia=distanciaPuntos(posicionX+xa,posicionY+ya,miRueda.getxCentro(),miRueda.getyCentro());
			//System.out.println(radio);
			int retval = Double.compare(distancia,miRueda.getRadio()-radio);	
			//System.out.println("retval="+retval);
			if(retval>0){
				return false;
			}else{
				return true;
			}


		}



}