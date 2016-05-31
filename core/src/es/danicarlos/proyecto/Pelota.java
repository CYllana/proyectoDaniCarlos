package es.danicarlos.proyecto;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
/**
 * Clase de nuestro objeto Pelota.
 * 
 * @author Carlos Yllana
 *
 */
public class Pelota {
	/**
	 * Speed es la velocidad de nuestra pelota
	 */
	private float speed;
	/**
	 * Sprite textura, es la forma en la que decidimos printar nuestra pelota, ya que un sprite se adapta mejor a una imagen en movimiento.
	 */
	private Sprite textura;
	/**
	 * Creamos un circulo que utilizaremos para las colisiones.
	 */
	private Circle bordes;
	/**
	 * Posiciones de nuestras pelotas, y posicion del ultimo rebote;
	 */
	private Vector2 posicion, antiguaPosicion;
	/**
	 * Radio de nuestra pelota
	 */
	private float radio;
	/**
	 * Clase de nuestro juego.
	 */
	private Juego miJuego;
	/**
	 * El valor xa es el incremento en el eje x que se hace en cada movimiento
	 */
	private float xa;
	/**
	 * El valor ya es el incremento en el eje y que se hace en cada movimiento
	 */
	private float ya;
	/**
	 * Booleana que utilizamos para saber cuando se da el primer choque con el borde de la rueda.
	 */
	private boolean primerChoque=true;
	/**
	 * La variable bt era una variable que utilizabamos anteriormente, 
	 * para asignar a cada pelota(boton), es decir las pelotas del menu principal, un tipo de movimiento.
	 * Actualmente todas tienen un movimiento aleatorio.
	 */
	private int bt;
	/**
	 * Variable que recoge las caráctericsticas de la rueda de juego.
	 */
	private Rueda miRueda;
	/**
	 * miVectorUnitario es un atributo de vector2 que utilizaba para calcular la dirección que haria un rebote normal.
	 */
	private Vector2 miVectorUni;
	/**
	 * Variable que utilizabamos con anterioridad para asignar los tipos de de rebote.
	 * 
	 */
	private int modoRebote;
	/**
	 * Variable para saber si la pelota pertenece al juego o al menu.
	 */
	private boolean menu;

	/**
	 * La variable angulo recoge el angulo con el cual la pelota choca con el borde.
	 */
	private double angulo;
	/**
	 * Constructor para las pelotas del menu.
	 * @param textura Imagen correspondiente a la pelota.
	 * @param x posición del eje x en el que se inicia.
	 * @param y posición del eje y en el que se inicia.
	 * @param miRueda rueda de juego.
	 * @param numero variable con la cual identificamos el tipo de bola de menu que és.
	 */
	public Pelota(Sprite textura,float x, float y, Rueda miRueda, int numero){
		this.textura= textura;
		this.miRueda=miRueda;
		posicion=new Vector2(x,y);
		//La velocidad de las pelotas del menu es constante, y en el caso del menu es mas lento.
		speed=10;
		bt=numero;
		this.miRueda=miRueda;
		radio=textura.getWidth()/2;
		bordes=new Circle(posicion.x,posicion.y, radio);
		antiguaPosicion=posicion;
		menu=true;
		inicioAleator();
		speed=(float) 1.5;
	}
	/**
	 * Constructor de pelota que utilizamos en el juego.
	 * @param textura Imagen correspondiente a la pelota
	 * @param x posición del eje x
	 * @param y posición del eje y
	 * @param miJuego
	 */

	public Pelota(Sprite textura,float x, float y, Juego miJuego){
		speed=4;
		this.textura= textura;
		posicion=new Vector2();
		posicion.x=x;
		posicion.y=y;
		radio=textura.getWidth()/2;
		this.miJuego=miJuego;
		miVectorUni= new Vector2();
		antiguaPosicion=posicion;
		bordes=new Circle(posicion.x,posicion.y, radio);
		menu=false;
		this.miRueda=miJuego.getMiRueda();
		inicioAleator();
	}

	//Getters y setters
	public Vector2 getPosicion() {
		return posicion;
	}

	public void setPosicion(Vector2 posicion) {
		this.posicion = posicion;
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

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		if(speed<15){
			this.speed = this.speed+speed;
		}

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


	/**
	 * Método que nos printará la posición de la pelota.
	 * @param batch Libgdx utiliza SpriteBatch para dibujar por pantalla.
	 */
	public void draw(SpriteBatch batch){
		batch.draw(textura, posicion.x-textura.getWidth()/2, posicion.y-textura.getHeight()/2);
	}

	//Regular el tiempo con milisegundos para que tenga una velocidad fija para todas las maquinas
	/**
	 * El método update nos actualiza la posición de la pelota.
	 * Es el método encargado de mover la pelota. Dentro de el encontramos dos situaciónes.
	 * La primera si nuestra bola no choca con la rueda, suma a la posicion inicial un valor.
	 * Este valor va definido va definido por un punto válido (XA) y (YA) el cual cálculamos. 
	 * Este valor lo multiplicamos por delta, por lo tanto nuestra velocidad será regulada segun los ciclos
	 * que nuestro componente realizará. Por último lo multiplicamos por la velocidad deseada.
	 *  
	 * Por otra parte Si la rueda choca, identificamos si es una pelota de menu o una pelota de juego.
	 * La diferencia está en que si la pelota es de juego, en este asignamos el atributo choqueBola a true;
	 * @param delta valor que utilizamos para regular la posición de la pelota.
	 */
	public void update(float delta){

		if(choqueRueda()){
			if(!menu){
				if (primerChoque){
					speed=2;
					primerChoque=false;
				}
				miJuego.setChoqueBola(true);
				//revoteNormal();
				aleatorio2(delta);

			}else{
				aleatorio2(delta);
			}
			antiguaPosicion=posicion;
		}


			posicion.x=posicion.x+xa*delta*10*speed;
			posicion.y=posicion.y+ya*delta*10*speed;
			bordes.setX(posicion.x);
			bordes.setY(posicion.y);
			Vector2 centroRueda=new Vector2(miRueda.getxCentro(),miRueda.getyCentro());
			double distancia =posicion.dst(centroRueda);

			if(!saleRueda()){
				System.out.println("entra");
			}
	



		//System.out.println(posicion.x+"  y= "+posicion.y+"  d="+distancia+"  r"+ (-radio+miRueda.getRadio()));

	}
	public boolean saleRueda(){
		Vector2 centroRueda=new Vector2(miRueda.getxCentro(),miRueda.getyCentro());
		double distancia =posicion.dst(centroRueda);
		int retval=(Double.compare(distancia, (-radio+miRueda.getRadio())));
		//System.out.println(posicion.x+"  y= "+posicion.y+"  d="+distancia+"  r"+ (-radio+miRueda.getRadio()));
		if(retval>0){
			double angulo=getAngulo();
			System.out.println(MathUtils.cosDeg((float) angulo));
			posicion.y= (float) ((-radio+miRueda.getRadio())*MathUtils.cosDeg((float) angulo));
			posicion.x=(float) ((-radio+miRueda.getRadio())*MathUtils.sinDeg((float) angulo));
			System.out.println(retval+"se queda "+ posicion.x+"  "+posicion.y);
			return false;
		}else{
			return true;
		}	


	}

	/**
	 * ChoqueRueda nos cálcula la distancia que hay entre el centro de la rueda, a la posicón acutal de la pelota(contando su radio).
	 * Si esta distancia es menor que el radio interior de la rueda, devolvera false, en el momento que lo supere, devolverá true;
	 * @return true or false dependiendo de si la distancia es mayor o menor al radio;
	 */
	public boolean choqueRueda(){
		Vector2 centroRueda=new Vector2(miRueda.getxCentro(),miRueda.getyCentro());
		double distancia =posicion.dst(centroRueda);

		int retval = Double.compare(distancia,(double)miRueda.getRadio()-radio);	
		if(retval>0){
			return true;
		}else{
			return false;
		}	
	}
	/**
	 * Metodo que utilizaba con anterioridad para cálcular la distancia entre dos puntos.
	 * Ya no lo utilizo porque he descubierto Vector2.
	 * @param x posición actual x
	 * @param y posición actual y
	 * @param centerX punto X del centro de la rueda
	 * @param centerY punto Y del centro de la rueda
	 * @return Devuelve la distancia entre los dos puntos.
	 */
	public static double distanciaPuntos(float x, float y, float centerX, float centerY){
		double respuest;
		double res1,res2;
		res1=centerX-x;  
		res2=centerY-y;
		res1=Math.pow(res1, 2)+Math.pow(res2, 2);
		respuest=Math.sqrt(res1);
		return respuest;
	}

	/**
	 * Metodo que nos daba una direción ya se negativa positiva o 0
	 * @return retorna una dirección incluida el 0;
	 */
	private float inicioAleatorio(){
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
	/**
	 * Método como el anterior pero en este caso simplemente nos retorna o una dirección positiva
	 * o una dirección negativa.
	 * @return
	 */
	private int direccion(){
		Random rnd=new Random();
		float posneg=rnd.nextInt(2)+1;
		if(posneg==1){
			posneg=1;
		}else{
			posneg=-1;
		}
		return (int) posneg;
	}
	/**
	 * Metodo que nos calculo un punto aleatorio según nuestra velocidad(speeD)
	 * @return retorna un punto con una dirección
	 */
	private float puntoAleatorio(){
		Random rnd=new Random();
		float valor=rnd.nextInt((int)speed+1);
		float posneg=direccion();
		return posneg*valor;
	}
	/**
	 * getAngulo() es el encargado de calcularnos el angulo con el cual la pelota ha chocado con nuestra rueda.
	 * Utilizamos conceptos de trigonometria.
	 * Hay que fiajrse que tenemos que hacer una translación de nuestro puntosX.
	 * El cosinus solamente nos da un angulo de 180 grados, por lo tanto tenemos que fijrnos si nuestra posicion.Y
	 * Está por encima o por debajo de nuestro centro.
	 * @return angulo en Grados
	 */
	public double getAngulo(){
		double rad=Math.acos((-miRueda.getxCentro()+posicion.x)/(miRueda.getRadio()-radio));
		if(posicion.y>miRueda.getyCentro()){
			setAngulo(Math.toDegrees(rad));
			return (Math.toDegrees(rad));
		}else{
			setAngulo(360-Math.toDegrees(rad));
		}

		return (360-Math.toDegrees(rad));

	}
	/**
	 * Aleators es un método que acualmente no utilizamos ya que pertenece a un método de una versión antigua.
	 * Anteriormente teniamos una velocidad fija.
	 * @return float entre dos numeros.
	 */
	private float aleator(){
		Random rnd=new Random();
		float valor=rnd.nextInt(5 - 3 + 1) + 3;
		return valor;
	}
	/**
	 * Primera versión de mi reboteAleatorio
	 * Actualmente no se utiliza para nada,fue la primera versión y actualmente no funciona.
	 */
	private void reboteAleatorio(){
		ya=puntoAleatorio();
		xa=puntoAleatorio();
		boolean valido=false;
		while(!valido){
			if(!siguienteValido(xa,ya)){
				xa=aleator();
				if(xa==0)ya=5*direccion();
				if(xa==3)ya=4*direccion();
				if(xa==4)ya=3*direccion();
				if(xa==5)ya=0*direccion();
				xa=xa*direccion();

			}else if((Math.sqrt(Math.pow(xa,2)+Math.pow(ya,2))==speed)){
				valido=true;
			}
			while (Math.sqrt(Math.pow(xa,2)+Math.pow(ya,2))!=speed){
				ya=puntoAleatorio();
				xa=puntoAleatorio();
			}
		}
	}
	/**
	 * Segunda versión del rebote Aleatorio.
	 * En este caso comprobabamos si incrementando la xa y ya con los valores actuales, es correcto.
	 * En el caso que no calculabamos una xa con el método visto con anterioridad.
	 * Según el valor de xa asignabamos una ya. 
	 * A continuación les dabamos una dirección a ambas.
	 * Esté método funciona bien. El problema es que siempre mantiene la misma velocidad. Si se aumenta puede fallar.
	 */
	private void aleatorio(){
		while(!siguienteValido(xa,ya)){
			xa=aleator();

			if(xa==0)ya=5*direccion();
			if(xa==3)ya=4*direccion();
			if(xa==4)ya=3*direccion();
			if(xa==5)ya=0*direccion();
			xa=xa*direccion();

		}

	}
	/***
	 * Método que utilizamos actualmente para calcular un punto aleatorio. 
	 * Éste punto estará comprendido entre la velocidad actual y el minimo(0)
	 * @return Valor aleatorio
	 */
	private float aleator2(){
		Random rnd=new Random();
		float valor=rnd.nextFloat()* (speed - 0) + 0;
		return valor;
	}
	/**
	 * Como el aleatorio de versión uno,  comprueba si el punto que vamos a cálcular es válido.
	 * Como vaoms a trabajar con la velocidad de la pelota, necesitamos el párametro delta.
	 * Cálculamos un valor de xa aleatorio. A continuacón con la formula del módulo de un vector(valor)
	 * despejamos el valor que deberia tener ya. Está formula es |speed|^2=(xa^2)+(ya)^2.
	 * Una vez calculado el valor le damos una dirección aleatoria.
	 * @param delta
	 */
	private void aleatorio2(float delta){
		while(!siguienteValido(xa, ya)){
			xa=aleator2()*direccion()/(delta*10);
			ya=(float) Math.sqrt(Math.pow(speed, 2)-Math.pow(xa*delta*10, 2))*direccion()/(delta*10);
			//System.out.println(speed+"   "+xa+"  "+ya);


		}

	}
	private double aleator3(){
		Random rnd=new Random();
		double valor=rnd.nextDouble()* (1 - 0) + 0;
		return valor;
	}
	/**
	 * Como el aleatorio de versión uno,  comprueba si el punto que vamos a cálcular es válido.
	 * Como vaoms a trabajar con la velocidad de la pelota, necesitamos el párametro delta.
	 * Cálculamos un valor de xa aleatorio. A continuacón con la formula del módulo de un vector(valor)
	 * despejamos el valor que deberia tener ya. Está formula es |speed|^2=(xa^2)+(ya)^2.
	 * Una vez calculado el valor le damos una dirección aleatoria.
	 * @param delta
	 */
	private void aleatorio3(){
		while(!siguienteValido(xa, ya)){
			xa=(float) aleator3();
			ya=(float) Math.sqrt(1-Math.pow(xa, 2))*direccion();




		}

	}
	//En este apartado encontramos los métodos que ya no utilizabamos y que hacian que cada rebote fuese diferente.

	/**
	 * Rebote return nos devolvia la pelota del sitio del que venia,
	 * por lo tanto simplemente teniamos que multiplicar los incrementos por -1.
	 */
	private void reboteReturn(){

		xa=xa*-1;
		ya=ya*-1;
	}
	/**
	 * Mi mayor fracaso, no he conseguido que me salga el rebote normal por inercia.
	 * http://www.3dkingdoms.com/weekly/weekly.php?a=2
	 * 
	 */
	private void revoteNormal(){

		Vector2 vectorV=new Vector2(posicion.x-antiguaPosicion.x,posicion.y-antiguaPosicion.y);
		Vector2 vectorNormal=new Vector2(posicion.x-miRueda.getxCentro(), posicion.y-miRueda.getyCentro());

		Vector2 vectorReultante= vectorNormal.mulAdd(vectorNormal, -2*(vectorV.dot(vectorNormal))).add(vectorV);
		xa=vectorReultante.x;
		ya=vectorReultante.y;
	}
	/**
	 * Rebote Horizontal, hacía que la pelota se moviese en el eje X. Para ello la posición actual
	 * de Y no la debíamos variar, por lo tanto el incremento debía ser 0. 
	 */
	private void revoteHorizontal(){
		while(!siguienteValido(xa,ya)){
			xa=speed*-1*xa/xa;
			ya=0;
		}

	}
	/**
	 * Rebote Vertical, hacía que la pelota se moviese en el eje Y. Para ello la posición actual
	 * de X no la debíamos variar, por lo tanto el incremento debía ser 0. 
	 */
	private void reboteVertical(){
		while(!siguienteValido(xa,ya)){
			xa=0;
			ya= speed*-1*ya/ya;
		}
	}

	/**
	 * Rebote Diagonal efectuaba que la pelota fuese de forma diagonal aleatoriamente.
	 */
	private void revoteDiagonal(){

		if(!siguienteValido(xa,ya)){
			xa=speed*direccion();
			ya=speed*direccion();

		}
	}
	/**
	 * Método asignado al color Negro.Paraba completamente la pelota.
	 */
	private void gameOver(){
		xa=0;
		ya=0;

	}

	/**
	 * Método que funcionaba correctamente. Este método hacia que cuando la pelota tocase un color,
	 * saliese por el otro sector con su color con direcicón aleatoria. Actualmente en la versión actual
	 * no funcionaria porque faltan variables.
	 * En rueda, recogiamos las posiciones del angulo correspondiente al color asignado. Con este angulo haciamos una translación
	 * de la posición de la pelota actual al angulo solicitado.A continuación ejecutabamos el rebote aleatorio
	 */
	private void transporter(){
		xa=0;
		double cos=Math.cos(Math.toRadians(miRueda.getTransportX()));
		ya=0;
		posicion.x=miRueda.getxCentro()+(float) ((miRueda.getRadio()-radio)*(cos));
		double sin =Math.sin(Math.toRadians(miJuego.getMiRueda().getTransportX()));
		//System.out.println();
		posicion.y=miRueda.getyCentro()+(float) ((miRueda.getRadio()-radio)*(sin));
		System.out.println("sin="+sin+"     "+posicion.x+" posiciones"+ posicion.y +"angulo"+miRueda.getTransportX());
		reboteAleatorio();

	}
	/**
	 * Método que nos comprueba si los incrementos que vamos a hacer son válidos o no. Para ello simulamos 
	 * si incrementando el valor de la posicion, chocaria o no con la pared de la rueda.
	 * @param xa incremento de x
	 * @param ya incremento de y
	 * @return false or true dependiendo si el valor es válido o no.
	 */
	private boolean siguienteValido(float xa, float ya){
		double distancia=distanciaPuntos(posicion.x+xa,posicion.y+ya,miRueda.getxCentro(),miRueda.getyCentro());
		//Vector2 centro=new Vector2(miRueda.getxCentro(), miRueda.getyCentro());
		//double distancia=posicion.dst(centro);

		int retval = Double.compare(distancia,(miRueda.getRadio()-radio));	
		//System.out.println(distancia+"    "+(miRueda.getRadio()-radio)+"   "+retval);
		//System.out.println(speed+" x=  "+(posicion.x+xa)+"y=  "+(posicion.y+ya));
		if(retval>0){
			return false;
		}else{
			return true;
		}


	}
	/**
	 * Método que nos inicia aleatoriamente con un valor de xa y ya random.
	 */
	private void inicioAleator(){
		float direccion=inicioAleatorio();
		this.xa =speed*direccion;
		if(direccion==0){
			ya=0;
			while(ya==0){
				direccion=inicioAleatorio();
				this.ya =speed*direccion;
			}
		}else{
			this.ya =speed*direccion;
		}

	}



}