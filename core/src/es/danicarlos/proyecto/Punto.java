package es.danicarlos.proyecto;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Punto {
	/**
	 * Vectir2, con la posición del punto
	 */
	private Vector2 posicion;
	/**
	 * Valor del radio de la estrella(imagen).
	 */
	private float radio;
	/**
	 * Variable que nos conecta con el Juego actual.
	 */
	private Juego miJuego;
	/**
	 * Sprite(imagen) asociado a nuestro punto
	 */
	private Sprite textura;
	/**
	 * Figura a la que asignaremos nuestra imagen, con ella cálcularemos la colisión.
	 */
	private Circle bordes;	
	
	private float 	tiempoActual;
	private boolean dibujar;
	
	/**
	 *Contstrucor al cual le pasamos la imagen asociada al punto y el juego actual.En este constructor
	 *se calcula una posición aleatoria para nuestro punto.
	 */
	public Punto(Sprite textura,Juego miJuego) {
		this.textura=textura;
		this.miJuego=miJuego;
		this.radio=textura.getHeight();
		textura.setCenterX(radio);
		posicion=new Vector2(((-textura.getWidth()+miJuego.getWidth())/2+aleatorioX()),(-textura.getWidth()+miJuego.getHeight())/2+aleatorioY());
		
		this.textura.setCenter(posicion.x, posicion.y);
		this.textura.setOriginCenter();
		bordes=new Circle(posicion.x,posicion.y,textura.getWidth()/2);
		dibujar=true;
		
	}


	
	
	public Circle getBordes() {
		return bordes;
	}

	public void setBordes(Circle bordes) {
		this.bordes = bordes;
	}

	/**
	 * Nos calcla un radio o posición en la cual se  encontrará nuestro punto. 
	 * @return
	 */
	
	private float radioAleatorio(){
		double radioJuego=miJuego.getMiRueda().getRadio()*0.8;
		Random rnd=new Random();
		double valor=rnd.nextDouble()*radioJuego-textura.getHeight();
	
		return (float)valor;
	}
	/**
	 * calcula valor entre 0 y 360
	 * @return ángulo aleatorio
	 */
	private float anguloAleatorio(){
		
		Random rnd=new Random();
		float valor=rnd.nextInt(360);
		return valor;
	}
	/**
	 * Método que utiliza los dos métodos vistos con anterioridad, primero nos cálcula una distancia aleatoria. 
	 * A continuación calcula un angulo aleatorio. Depsués trigometricamente lo multiplicamos y obtenemos una 
	 * posición aleatoria x
	 * @return posición aleatoria X
	 */ 
	private float aleatorioX(){
		float radioJuego=radioAleatorio();
		float angulo=anguloAleatorio();
		return radioJuego*MathUtils.sinDeg(angulo);
	}
	/**
	 * Método que utiliza los dos métodos vistos con anterioridad, primero nos cálcula una distancia aleatoria. 
	 * A continuación calcula un angulo aleatorio. Depsués trigometricamente lo multiplicamos y obtenemos una 
	 * posición aleatoria y
	 * @return posición aleatoria Y
	 * 
	 * @return
	 */
	
	private float aleatorioY(){
		float radioJuego=radioAleatorio();
		float angulo=anguloAleatorio();
		return radioJuego*MathUtils.cosDeg(angulo);
	}
	
	
	
	/**
	 * Método utilizado en libgdx para dibujar nuestro punto
	 * @param batch
	 */
	public void draw(SpriteBatch batch){
		if(dibujar)batch.draw(textura,posicion.x-textura.getHeight()/2,posicion.y-textura.getHeight()/2 );
	}
	
	/**
	 * Actualizamos la posición de nuestro punto dependiendo si la pelota ha colisionado con el.
	 * Si la pelota ha colisionado con el area(borde)  de nuestro punto, asignamos al juego que una estrella ha sido capturada,
	 * También le recompensamos por esta acción con 7 segundos.
	 * A continuación actualizamos a una nueva posición.
	 */
	public void update(float segundos){
		if(colision()){
			miJuego.setEstrellas(1);
			miJuego.setTotalTime(7);	
			tiempoActual=segundos;	
			dibujar=false;
			
		}

		//System.out.println((-segundos)+ "       "+(-tiempoActual));

		if(!dibujar&&((-segundos)>(-tiempoActual+2))){
			
			tiempoActual=0;
			posicion.x=miJuego.getWidth()/2+aleatorioX();
			posicion.y=miJuego.getHeight()/2+aleatorioY();
			bordes.setPosition(posicion.x, posicion.y);
			dibujar=true;
		}
		
	}
	/**
	 * Método utilizando facilidades de libgdx, que nos devuelve tru or false si los bordes de la estrella y
	 * la pelota chocan.
	 * @return
	 */
	public boolean colision(){
	
		return bordes.overlaps(miJuego.getMiPelota().getBordes());
		
	}
	

}